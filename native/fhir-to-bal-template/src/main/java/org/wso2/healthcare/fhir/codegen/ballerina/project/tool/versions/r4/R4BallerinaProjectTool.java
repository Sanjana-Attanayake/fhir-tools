/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.healthcare.fhir.codegen.ballerina.project.tool.versions.r4;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.SearchParameter;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.wso2.healthcare.codegen.tool.framework.fhir.core.model.FHIRImplementationGuide;
import org.wso2.healthcare.codegen.tool.framework.fhir.core.model.FHIRResourceDef;
import org.wso2.healthcare.codegen.tool.framework.fhir.core.model.FHIRSearchParamDef;
import org.wso2.healthcare.codegen.tool.framework.fhir.core.versions.r4.model.FHIRR4ResourceDef;
import org.wso2.healthcare.fhir.codegen.ballerina.project.tool.BallerinaProjectConstants;
import org.wso2.healthcare.fhir.codegen.ballerina.project.tool.AbstractBallerinaProjectTool;
import org.wso2.healthcare.fhir.codegen.ballerina.project.tool.model.BallerinaService;
import org.wso2.healthcare.fhir.codegen.ballerina.project.tool.model.versions.r4.R4FHIRProfile;
import org.wso2.healthcare.fhir.codegen.ballerina.project.tool.model.SearchParam;
import org.wso2.healthcare.fhir.codegen.ballerina.project.tool.model.versions.r4.R4SearchParam;
import org.wso2.healthcare.fhir.codegen.ballerina.project.tool.versions.r5.R5BallerinaProjectTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main class for Ballerina Project Generator tool (FHIR R4).
 */
public class R4BallerinaProjectTool extends AbstractBallerinaProjectTool {

    private static final Log LOG = LogFactory.getLog(R4BallerinaProjectTool.class);

    /**
     * Populate Ballerina Service model according to configured IGs and Profiles.
     */
    @Override
    protected void populateBalService() {
        for (Map.Entry<String, FHIRImplementationGuide> entry : getIgMap().entrySet()) {
            String igName = entry.getKey();

            // extract structure definitions of resource types
            Map<String, FHIRResourceDef> resourceDefMap = new HashMap<>();

            for (Map.Entry<String, FHIRResourceDef> resourceEntry : entry.getValue().getResources().entrySet()) {
                String key = resourceEntry.getKey();
                FHIRR4ResourceDef resourceDef = (FHIRR4ResourceDef) resourceEntry.getValue();
                String resourceName = resourceDef.getDefinition().getName();
                String resourceKind = resourceDef.getDefinition().getKind().toCode();

                if (!getExcludedFHIRApis().contains(resourceName) && resourceKind.equalsIgnoreCase("RESOURCE")) {
                    resourceDefMap.put(key, resourceDef);
                }
            }

            // filter structure definitions based on included/excluded
            List<StructureDefinition> structureDefinitions = retrieveStructureDef(igName, resourceDefMap);
            structureDefinitions.forEach(definition -> {
                addResourceProfile(definition, definition.getType(), definition.getName(), definition.getUrl(), igName);
            });

            //adding Search parameters
            for (Map.Entry<String, FHIRSearchParamDef> parameter : entry.getValue().getSearchParameters().entrySet()) {
                SearchParameter searchParameter = (SearchParameter) parameter.getValue().getSearchParameter();
                List<CodeType> baseResources = searchParameter.getBase();

                for (CodeType baseType : baseResources) {
                    String apiName = baseType.getCode();
                    if (!getServiceMap().containsKey(apiName)) {
                        continue;
                    }
                    SearchParam param = getSearchParam(parameter, apiName);
                    getServiceMap().get(apiName).addSearchParam(param);
                }
            }
        }
    }

    @Override
    protected SearchParam getSearchParam(Map.Entry<String, FHIRSearchParamDef> parameter, String apiName) {
        SearchParameter searchParameter = (SearchParameter) parameter.getValue().getSearchParameter();
        R4SearchParam param = new R4SearchParam(searchParameter.getName(), searchParameter.getCode());

        param.setSearchParamDef(searchParameter);
        param.setDescription(searchParameter.getDescription().replace("\"", ""));
        param.setDocumentation(searchParameter.getUrl());
        param.setTargetResource(apiName);

        if (getBallerinaProjectToolConfig().getSearchParamConfigs().contains(param.getSearchParamDef().getCode())) {
            param.setBuiltIn(true);
        }
        return param;
    }

    private List<StructureDefinition> retrieveStructureDef(String igName, Map<String, FHIRResourceDef> resourceDefMap) {
        List<StructureDefinition> structureDefinitions = new ArrayList<>();
        List<String> includedProfiles =
                getBallerinaProjectToolConfig().getIncludedIGConfigs().get(igName).getIncludedProfiles();
        List<String> excludedProfiles =
                getBallerinaProjectToolConfig().getIncludedIGConfigs().get(igName).getExcludedProfiles();
        if (!includedProfiles.isEmpty()) {
            for (String profile : includedProfiles) {
                if (resourceDefMap.containsKey(profile)) {
                    structureDefinitions.add((StructureDefinition) resourceDefMap.get(profile).getDefinition());
                } else {
                    // invalid url
                    LOG.debug(BallerinaProjectConstants.PrintStrings.INVALID_PROFILE + profile);
                }
            }
            if (structureDefinitions.isEmpty()) {
                // nothing included
                // generate template for all the profiles
                LOG.info(BallerinaProjectConstants.PrintStrings.TEMPLATES_FOR_ALL_PROFILES);
                resourceDefMap.forEach((k, resourceDef) -> {
                    structureDefinitions.add((StructureDefinition) resourceDef.getDefinition());
                });
            }
            return structureDefinitions;
        }
        if (!excludedProfiles.isEmpty()) {
            Map<String, FHIRResourceDef> resourceDefMapCopy = new HashMap<>(resourceDefMap);
            for (String profile : excludedProfiles) {
                if (resourceDefMapCopy.containsKey(profile)) {
                    resourceDefMapCopy.remove(profile);
                } else {
                    // invalid url
                    LOG.debug(BallerinaProjectConstants.PrintStrings.INVALID_PROFILE + profile);
                }
            }
            resourceDefMapCopy.forEach((k, resourceDef) -> {
                structureDefinitions.add((StructureDefinition) resourceDef.getDefinition());
            });
            if (resourceDefMap.size() == resourceDefMapCopy.size()) {
                LOG.info(BallerinaProjectConstants.PrintStrings.TEMPLATES_FOR_ALL_PROFILES);
            }
            return structureDefinitions;
        }
        // nothing included or excluded
        // generate templates for all the profiles
        LOG.info(BallerinaProjectConstants.PrintStrings.TEMPLATES_FOR_ALL_PROFILES);
        resourceDefMap.forEach((k, v) -> {
            structureDefinitions.add((StructureDefinition) v.getDefinition());
        });
        return structureDefinitions;
    }

    /**
     * Adding Ballerina service model to a common map.
     *
     * @param structureDefinition FHIR StructureDefinition
     * @param resourceType        FHIR resource type
     * @param profile             FHIR profile
     * @param url                 FHIR profile url
     */
    private void addResourceProfile(StructureDefinition structureDefinition, String resourceType, String profile,
                                    String url, String igName) {

        if (getServiceMap().containsKey(resourceType)) {
            if (structureDefinition.getAbstract()) {
                //profiled resource added before
                R4FHIRProfile fhirProfile = new R4FHIRProfile(structureDefinition, url, igName, resourceType);
                fhirProfile.setFhirVersion(getBallerinaProjectToolConfig().getFhirVersion());
                fhirProfile.setPackagePrefix(getBallerinaProjectToolConfig());
                fhirProfile.setAbstract();
                fhirProfile.addImport(getBallerinaProjectToolConfig().getIncludedIGConfigs().get(igName).getImportStatement());
                getServiceMap().get(resourceType).addFhirProfile(fhirProfile);
                getServiceMap().get(resourceType).addProfile(url);
            } else {
                //check for profiles
                if (!getServiceMap().get(resourceType).getProfiles().contains(profile)) {
                    R4FHIRProfile fhirProfile = new R4FHIRProfile(structureDefinition, url, igName, resourceType);
                    fhirProfile.addImport(getBallerinaProjectToolConfig().getIncludedIGConfigs().get(igName).getImportStatement());
                    fhirProfile.setFhirVersion(getBallerinaProjectToolConfig().getFhirVersion());
                    fhirProfile.setPackagePrefix(getBallerinaProjectToolConfig());
                    getServiceMap().get(resourceType).addFhirProfile(fhirProfile);
                    getServiceMap().get(resourceType).addProfile(url);
                }
            }
        } else {
            BallerinaService ballerinaService = new BallerinaService(resourceType, getBallerinaProjectToolConfig().getFhirVersion());
            R4FHIRProfile fhirProfile = new R4FHIRProfile(structureDefinition, url, igName, resourceType);
            fhirProfile.addImport(getBallerinaProjectToolConfig().getIncludedIGConfigs().get(igName).getImportStatement());
            fhirProfile.setFhirVersion(getBallerinaProjectToolConfig().getFhirVersion());
            fhirProfile.setPackagePrefix(getBallerinaProjectToolConfig());
            ballerinaService.addFhirProfile(fhirProfile);
            ballerinaService.addProfile(url);
            ballerinaService.addIg(igName);
            getServiceMap().put(structureDefinition.getType(), ballerinaService);
        }
    }
}
