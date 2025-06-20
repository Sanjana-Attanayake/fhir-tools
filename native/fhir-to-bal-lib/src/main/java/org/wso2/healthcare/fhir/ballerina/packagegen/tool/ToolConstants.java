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

package org.wso2.healthcare.fhir.ballerina.packagegen.tool;

import java.time.LocalDate;

public class ToolConstants {
    public static final String CONFIG_ENABLE = "enable";
    public static final String CONFIG_PROFILE_IG = "implementationGuide";
    public static final String CONFIG_PROFILE_IG_TOML = "implementation_guide";
    public static final String CONFIG_PROFILE_IG_BASE = "baseIGPackage";
    public static final String CONFIG_PROFILE_IG_BASE_TOML = "base_ig_package";
    public static final String CONFIG_PACKAGE = "packageConfigs";
    public static final String CONFIG_PACKAGE_TOML = "tools.config.package";
    public static final String CONFIG_PACKAGE_ORG = "org";
    public static final String CONFIG_PACKAGE_ORG_TOML = "tools.config.package.org";
    public static final String CONFIG_PACKAGE_NAME = "name";
    public static final String CONFIG_PACKAGE_NAME_TOML = "tools.config.package.name";
    public static final String CONFIG_PACKAGE_VERSION = "version";
    public static final String CONFIG_PACKAGE_VERSION_TOML = "tools.config.package.version";
    public static final String CONFIG_PACKAGE_DISTRIBUTION = "ballerinaDistribution";
    public static final String CONFIG_PACKAGE_DISTRIBUTION_TOML = "tools.config.package.ballerina_distribution";
    public static final String CONFIG_PACKAGE_AUTHORS = "authors";
    public static final String CONFIG_PACKAGE_AUTHORS_TOML = "tools.config.package.authors";
    public static final String CONFIG_PACKAGE_FHIR_VERSION = "fhirVersion";
    public static final String CONFIG_PACKAGE_FHIR_VERSION_TOML = "tools.config.packageConfig.fhirVersion";

    public static final String CONFIG_VERSION_CONFIGS = "versionConfigs";
    public static final String CONFIG_PACKAGE_REPOSITORY = "repository";
    public static final String CONFIG_BASE_PACKAGE = "basePackage";

    ///  to-do: check the breakability of elements like above when initiating the tool from
    /// a tool-config.toml file
    public static final String CONFIG_R4_PACKAGE_REPOSITORY_TOML = "tools.config.package.version_configs.r4.repository";
    public static final String CONFIG_R5_PACKAGE_REPOSITORY_TOML = "tools.config.package.version_configs.r5.repository";
    public static final String CONFIG_R4_BASE_PACKAGE_TOML = "tools.config.package.base_package";
    public static final String CONFIG_R5_BASE_PACKAGE_TOML = "tools.config.package.version_configs.r5.base_package";

    public static final String CONFIG_PACKAGE_DEPENDENCY = "dependencies";
    public static final String CONFIG_PACKAGE_DEPENDENCY_TOML = "tools.config.package.dependency";
    public static final String CONFIG_PACKAGE_DEPENDENCY_ORG = "org";
    public static final String CONFIG_PACKAGE_DEPENDENCY_ORG_TOML = "tools.config.package.dependency.org";
    public static final String CONFIG_PACKAGE_DEPENDENCY_NAME = "name";
    public static final String CONFIG_PACKAGE_DEPENDENCY_NAME_TOML = "tools.config.package.dependency.name";
    public static final String CONFIG_PACKAGE_DEPENDENCY_VERSION = "version";
    public static final String CONFIG_PACKAGE_DEPENDENCY_VERSION_TOML = "tools.config.package.dependency.version";
    public static final String CONFIG_PACKAGE_DEPENDENCY_REPOSITORY = "repository";
    public static final String CONFIG_PACKAGE_DEPENDENCY_REPOSITORY_TOML = "tools.config.package.dependency.repository";
    public static final String CONFIG_INCLUDED_IGS = "includedIGs";
    public static final String CONFIG_INCLUDED_IGS_TOML = "tools.config.included_igs";
    public static final String CONFIG_DATA_TYPE_MAPPINGS = "dataTypeMappings";
    public static final String CONFIG_DATA_TYPE_MAPPING_TOML = "tools.config.data_type_mappings";
    public static final String CONFIG_DATA_TYPE_FHIR = "fhirType";
    public static final String CONFIG_DATA_TYPE_FHIR_TOML = "tools.config.data_type_mappings.fhir_type";
    public static final String CONFIG_DATA_TYPE_BALLERINA = "ballerinaType";
    public static final String CONFIG_DATA_TYPE_BALLERINA_TOML = "tools.config.data_type_mappings.ballerina_type";
    public static final String CONFIG_BALLERINA_KEYWORD = "ballerinaKeywords";
    public static final String CONFIG_BALLERINA_KEYWORD_TOML = "tools.config.ballerina_keyword";
    public static final String CONFIG_BALLERINA_KEYWORD_KEYWORD = "keyword";
    public static final String CONFIG_BALLERINA_KEYWORD_KEYWORD_TOML = "tools.config.ballerina_keyword.keyword";
    public static final String CONFIG_BALLERINA_KEYWORD_REPLACE = "replace";
    public static final String CONFIG_BALLERINA_KEYWORD_REPLACE_TOML = "tools.config.ballerina_keyword.replace";
    public static final String TEMPLATE_PATH = "templates";
    public static final String TEMPLATE_VERSION_PATH = "versions";
    public static final String BAL_EXTENSION = ".bal";
    public static final String TOML_EXTENSION = ".toml";
    public static final String MD_EXTENSION = ".md";
    public static final String CONSTRAINTS_LIB_IMPORT = "ballerina/constraint";
    public static final String ELEMENT = "Element";
    public static final String RESOURCE_PATH_SEPERATOR = "/";
    public static final String EQUAL_SIGN = "=";
    // This regex will validate the profile dependentIg pattern <profile_url>=<Ballerina_org_name>/<Ballerina_package_name>
    public static final String REGEX_FOR_DEPENDENT_IG_AND_PACKAGE = "^https?://(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}(?:/[^\\s]*)?=[a-zA-Z0-9-]+/[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$";

    public enum TokenPosition {
        BEGIN,
        MIDDLE,
        END
    }
    public static final String FHIR_R4_DEFINITION_URL = "https://hl7.org/fhir/R4/structuredefinition.html";
    public static final String FHIR_R5_DEFINITION_URL = "https://hl7.org/fhir/R5/structuredefinition.html";
    public static final String LICENSE_YEAR = String.valueOf(LocalDate.now().getYear());
    public static final String DATA_TYPE_EXTENSION = "Extension";

    public class PrintStrings {
        public static final String OVERWRITING_EXISTING_PACKAGE = "[INFO] Overwriting the existing package.";
        public static final String INVALID_INPUT = "[ERROR] Invalid input. Exiting the tool.";
    }
}
