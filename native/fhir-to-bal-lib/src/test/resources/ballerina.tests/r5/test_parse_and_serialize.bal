import ballerina/test;
import ballerinax/health.fhir.r5;
import ballerinax/health.fhir.r5.parser;

@test:Config{}
function testParse() returns error? {
    json patientPayload = {
        "resourceType": "Patient",
        "id": "HL7ATCorePatientExample01",
        "meta": {
            "profile": [
                "http://hl7.at/fhir/HL7ATCoreProfiles/5.0.0/StructureDefinition/at-core-patient"
            ]
        },
        "text": {
            "status": "generated",
            "div": "<div xmlns=\"http://www.w3.org/1999/xhtml\"><p class=\"res-header-id\"><b>Generated Narrative: Patient HL7ATCorePatientExample01</b></p><a name=\"HL7ATCorePatientExample01\"> </a><a name=\"hcHL7ATCorePatientExample01\"> </a><a name=\"HL7ATCorePatientExample01-en-US\"> </a><p style=\"border: 1px #661aff solid; background-color: #e6e6ff; padding: 10px;\">Max Mustermann  Male, DoB: 1900-01-01 ( Social Security number: 1234010100)</p><hr/><table class=\"grid\"><tr><td style=\"background-color: #f3f5da\" title=\"Other Ids (see the one above)\">Other Ids:</td><td colspan=\"3\"><ul><li>National unique individual identifier/GH:oeLdSEb0l+8kSdJWjOYyYmnYki0=</li><li>Patient internal identifier/0815</li></ul></td></tr><tr><td style=\"background-color: #f3f5da\" title=\"Ways to contact the Patient\">Contact Detail</td><td colspan=\"3\"><ul><li><a href=\"mailto:office@hl7.at\">office@hl7.at</a></li><li><a href=\"tel:+436501234567890\">+436501234567890</a></li><li>Landstrasse 1 Stock 9 Tür 42 Linz Oberösterreich 4020 AUT (home)</li></ul></td></tr><tr><td style=\"background-color: #f3f5da\" title=\"HL7® Austria FHIR® Core Extension for the religion (registered in Austria) of a patient.\r\nThe extension is used to encode the religious confession of a patient (only confessions registered in Austria). Furthermore, it uses the official [HL7 AT CodeSystem](https://termpub.gesundheit.gv.at:443/TermBrowser/gui/main/main.zul?loadType=CodeSystem&amp;loadName=HL7 AT ReligionAustria) for religion
        },
        "extension" : [
            {
            "extension" : [
                {
                "url" : "code",
                "valueCodeableConcept" : {
                    "coding" : [
                    {
                        "system" : "https://termgit.elga.gv.at/CodeSystem/hl7-at-religionaustria",
                        "code" : "162",
                        "display" : "Pastafarianismus"
                    }
                    ]
                }
                }
            ],
            "url" : "http://hl7.at/fhir/HL7ATCoreProfiles/5.0.0/StructureDefinition/at-core-ext-patient-religion"
            },
            {
            "extension" : [
                {
                "url" : "code",
                "valueCodeableConcept" : {
                    "coding" : [
                    {
                        "system" : "https://termgit.elga.gv.at/CodeSystem/iso-3166-1-alpha-3",
                        "code" : "AUT",
                        "display" : "Österreich"
                    }
                    ]
                }
                }
            ],
            "url" : "http://hl7.org/fhir/StructureDefinition/patient-citizenship"
            }
        ],
        "identifier" : [
            {
            "type" : {
                "coding" : [
                {
                    "system" : "http://terminology.hl7.org/CodeSystem/v2-0203",
                    "code" : "SS",
                    "display" : "Social Security number"
                }
                ]
            },
            "system" : "urn:oid:1.2.40.0.10.1.4.3.1",
            "value" : "1234010100",
            "assigner" : {
                "display" : "Dachverband der österreichischen Sozialversicherungsträger"
            }
            },
            {
            "type" : {
                "coding" : [
                {
                    "system" : "http://terminology.hl7.org/CodeSystem/v2-0203",
                    "code" : "NI",
                    "display" : "National unique individual identifier"
                }
                ]
            },
            "system" : "urn:oid:1.2.40.0.10.2.1.1.149",
            "value" : "GH:oeLdSEb0l+8kSdJWjOYyYmnYki0=",
            "assigner" : {
                "display" : "Bundesministerium für Inneres"
            }
            },
            {
            "type" : {
                "coding" : [
                {
                    "system" : "http://terminology.hl7.org/CodeSystem/v2-0203",
                    "code" : "PI",
                    "display" : "Patient internal identifier"
                }
                ]
            },
            "system" : "urn:oid:1.2.3.4.5",
            "value" : "0815",
            "assigner" : {
                "display" : "Ein GDA in Österreich"
            }
            }
        ],
        "name" : [
            {
            "family" : "Mustermann",
            "given" : [
                "Max"
            ],
            "prefix" : [
                "DI"
            ]
            }
        ],
        "telecom" : [
            {
            "system" : "email",
            "value" : "office@hl7.at",
            "use" : "work"
            },
            {
            "system" : "phone",
            "value" : "+436501234567890",
            "use" : "home"
            }
        ],
        "gender" : "male",
        "birthDate" : "1900-01-01",
        "address" : [
            {
            "use" : "home",
            "type" : "both",
            "line" : [
                "Landstrasse 1 Stock 9 Tür 42"
            ],
            "_line" : [
                {
                "extension" : [
                    {
                    "url" : "http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-streetName",
                    "valueString" : "Landstrasse"
                    },
                    {
                    "url" : "http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-houseNumber",
                    "valueString" : "1"
                    },
                    {
                    "url" : "http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-additionalLocator",
                    "valueString" : "Stock 9 Tür 42"
                    },
                    {
                    "url" : "http://hl7.at/fhir/HL7ATCoreProfiles/5.0.0/StructureDefinition/at-core-ext-address-additionalInformation",
                    "valueString" : "Lift vorhanden"
                    }
                ]
                }
            ],
            "city" : "Linz",
            "state" : "Oberösterreich",
            "postalCode" : "4020",
            "country" : "AUT"
            }
        ]
    };

    r5:HumanName[] name = [
        {
            family: "Solo",
            given: ["Jaina"],
            use: r5:official
        }
    ];

    anydata parsedResult = check parser:parse(patientPayload, AtCorePaitientProfile);
    AtCorePaitientProfile patientModel = check parsedResult.ensureType();
    assertEquals(patientModel.name, name, "Patient name is not equal");
}

@test:Config{}
function testSerialize() {
    AtCorePaitientProfile patient = {
        meta: {
            profile: [PROFILE_BASE_HL7ATCOREPATIENT]
        },
        active: true,
        name: [{
            family: "Mustermann",
            given: ["Max"],
            use: r5:official,
            prefix: ["DI"]
        }],
        address: [{
            line: ["Landstrasse 1 Stock 9 Tür 42"],
            city: "Linz",
            country: "AUT",
            postalCode: "4020"
        }],
        identifier: [
            system: "http://acme.org/mrns",
            value: "12345"
        ],
        gender: "unknown"
        communication : [{
            language : {"en-GB" : "English (United Kingdom)"},
            preferred : true
        }]
    }

    // In R5: Patient.communication.language has Change binding strength from preferred to required
    r5:FHIRResourceEntity fhirEntity = new(patient);
    json|r5:FHIRSerializerError jsonResult = fhirEntity.toJson();
    if(jsonResult is json){
        test:assertEquals(true, patient:communication:[0].preferred);
    }
    else{
        test:assertFail("Error occurred while serializing the patient model");
    }
}