package com.cotiviti.hdap.APIPayLoads;

import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;

public class ChaseRequestWithProviderPayLoad extends apiCommonLib {

    public static String chaseRequestPayLoad(String intendedUse, String auditType, String chartType,
            String cotivitiClaimNumber, String clientClaimNumber, String dateOfServiceStart, String dateOfServiceEnd,
            String lastName, String firstName, String dob, String facilityName, String fAddress1, String fCity,
            String fState, String fZip, String fNPI, String fTIN, String fPhone, String pLastName, String pFirstName,
            String pAddress1, String pCity, String pState, String pZip, String pNPI, String pTIN, String AccountID,
            String SubAccountID, String providerType,String ProspectiveClaimFlag) {



        int RenderingProviderIdx = 0;
        int BillingProviderIdx = 0;

        if (providerType.equals("Facility")) {
            BillingProviderIdx = 2;
            RenderingProviderIdx = 1;
        } else if (providerType.equals("Practitioner")) {
            BillingProviderIdx = 1;
            RenderingProviderIdx = 2;
        }


        boolean prospectiveClaimFlagValue = Boolean.parseBoolean(
             ProspectiveClaimFlag != null ? ProspectiveClaimFlag.trim() : "true"
         );

        
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getMethodName().equals("createChaseRequestFacility1")) {
                prospectiveClaimFlagValue = false;
                break;
            }
        }

        // Now use prospectiveClaimFlagValue wherever needed
        if (prospectiveClaimFlagValue) {
            System.out.println("Prospective claim is TRUE");
        } else {
            System.out.println("Prospective claim is FALSE");
        }
        
     
    
        
        for (StackTraceElement element : stackTrace) {
            if (element.getMethodName().equals("createChaseRequestFacility1")) {
                prospectiveClaimFlagValue = false;
                break;
            }
        }

        String payLoad = "{\r\n" +
            "  \"ChaseRequest\": {\r\n" +
            "    \"ChaseRequestHeader\": {\r\n" +
            "      \"IntendedUse\": \"" + intendedUse + "\",\r\n" +
            "      \"AuditType\": \"" + auditType + "\",\r\n" +
            "      \"ChartType\": \"" + chartType + "\",\r\n" +
            "      \"RelatedChaseID\": \"911\",\r\n" +
            "      \"ExternalChaseNumber\": \"894986377\",\r\n" +
            "      \"GlobalWorkforceApproved\": false,\r\n" +
            "      \"ExpirationDate\": \"2026-02-09\",\r\n" +
            "      \"MRAvailableInClientSystem\": false,\r\n" +
            "      \"MRClientSystemDCN\": \"8478579337\",\r\n" +
            "      \"ProspectiveClaimFlag\": " + prospectiveClaimFlagValue + ",\r\n" +
            "      \"ClientID\": 13,\r\n" +
            "      \"AccountID\": \"" + AccountID + "\",\r\n" +
            "      \"SubAccountId\": \"" + SubAccountID + "\",\r\n" +
            "      \"PortalRequestCode\": \"46378\",\r\n" +
            "      \"PlanName\": \"6\",\r\n" +
            "      \"PrimaryChaseRequestProviderRole\": \"RenderingProvider\",\r\n" +
            "      \"ExplicitRequestGroupId\": null\r\n" +
            "    },\r\n" +
            "    \"ClaimDetails\": {\r\n" +
            "      \"CotivitiClaimNumber\": \"" + cotivitiClaimNumber + "\",\r\n" +
            "      \"ClaimType\": \"S\",\r\n" +
            "      \"InNetwork\": true,\r\n" +
            "      \"ClientClaimNumber\": \"" + clientClaimNumber + "\",\r\n" +
            "      \"DateOfServiceStart\": \"" + dateOfServiceStart + "\",\r\n" +
            "      \"DateOfServiceEnd\": \"" + dateOfServiceEnd + "\",\r\n" +
            "      \"TotalClaimAmt\": 1666.09,\r\n" +
            "      \"ProviderSpecialty\": \"test\",\r\n" +
            "      \"PatientMemberIdx\": 2,\r\n" +
            "      \"SubscriberMemberIdx\": 1,\r\n" +
            "      \"BillingProviderIdx\": " + BillingProviderIdx + ",\r\n" +
            "      \"RenderingProviderIdx\": " + RenderingProviderIdx + "\r\n" +
            "    },\r\n" +
            "    \"ChaseDetails\": {\r\n" +
            "      \"DOSChartRangeStart1\": \"2022-09-26\",\r\n" +
            "      \"DOSChartRangeEnd1\": \"2022-09-26\",\r\n" +
            "      \"DOSChartRangeStart2\": \"2022-09-26\",\r\n" +
            "      \"DOSChartRangeEnd2\": \"2022-09-26\",\r\n" +
            "      \"DOSChartRangeStart3\": \"2022-09-26\",\r\n" +
            "      \"DOSChartRangeEnd3\": \"2022-09-26\",\r\n" +
            "      \"DOSChartRangeStart4\": \"2022-09-26\",\r\n" +
            "      \"DOSChartRangeEnd4\": \"2022-09-26\",\r\n" +
            "      \"DeliveryDate\": \"2022-09-26\",\r\n" +
            "      \"RetrievalRank\": 8,\r\n" +
            "      \"HedisMeasureID\": \"HEDIS26\",\r\n" +
            "      \"MeasurementGap\": \"Measure2\",\r\n" +
            "      \"SubMeasurementGap\": \"SubMeasure5\",\r\n" +
            "      \"IsOversampleFlag\": true,\r\n" +
            "      \"ChasePriority\": \"High\",\r\n" +
            "      \"RADVEnrolleeID\": \"RADV3354\",\r\n" +
            "      \"MedicareAdvantagecontractNumber\": \"MA353\"\r\n" +
            "    },\r\n" +
            "    \"Members\": [\r\n" +
            "      {\r\n" +
            "        \"Member\": {\r\n" +
            "          \"LastName\": \"Paul\",\r\n" +
            "          \"FirstName\": \"Paker\",\r\n" +
            "          \"Address1\": \"048 Hinton Loop Apt. 872\",\r\n" +
            "          \"Address2\": \"Apt. 736\",\r\n" +
            "          \"City\": \"Kathleenside\",\r\n" +
            "          \"State\": \"KY\",\r\n" +
            "          \"Zip\": \"62417\",\r\n" +
            "          \"DOB\": \"1982-04-18\",\r\n" +
            "          \"Gender\": \"M\",\r\n" +
            "          \"SSN\": \"495-92-8935\",\r\n" +
            "          \"PatientId\": \"4145\",\r\n" +
            "          \"HICN\": \"716351468\",\r\n" +
            "          \"ControlNumber\": \"9679\",\r\n" +
            "          \"MBI\": \"98716\",\r\n" +
            "          \"RecordNumber\": \"87618\",\r\n" +
            "          \"ClientMemberID\": \"89225\"\r\n" +
            "        }\r\n" +
            "      },\r\n" +
            "      {\r\n" +
            "        \"Member\": {\r\n" +
            "          \"LastName\": \"" + lastName + "\",\r\n" +
            "          \"FirstName\": \"" + firstName + "\",\r\n" +
            "          \"Address1\": \"644 Rachel Ridge Apt. 945\",\r\n" +
            "          \"Address2\": \"Suite 660\",\r\n" +
            "          \"City\": \"East Victoriaborough\",\r\n" +
            "          \"State\": \"MS\",\r\n" +
            "          \"Zip\": \"14075\",\r\n" +
            "          \"DOB\": \"" + dob + "\",\r\n" +
            "          \"Gender\": \"F\",\r\n" +
            "          \"SSN\": \"790-57-4891\",\r\n" +
            "          \"PatientId\": \"4906\",\r\n" +
            "          \"HICN\": \"949290986\",\r\n" +
            "          \"ControlNumber\": \"1092\",\r\n" +
            "          \"MBI\": \"77340\",\r\n" +
            "          \"RecordNumber\": \"26970\",\r\n" +
            "          \"ClientMemberID\": \"57303\"\r\n" +
            "        }\r\n" +
            "      }\r\n" +
            "    ],\r\n" +
            "    \"Providers\": [\r\n" +
            "      {\r\n" +
            "        \"Provider\": {\r\n" +
            "          \"Name\": \"" + facilityName + "\",\r\n" +
            "          \"LastName\": \"\",\r\n" +
            "          \"FirstName\": \"\",\r\n" +
            "          \"Address1\": \"" + fAddress1 + "\",\r\n" +
            "          \"Address2\": \"\",\r\n" +
            "          \"City\": \"" + fCity + "\",\r\n" +
            "          \"State\": \"" + fState + "\",\r\n" +
            "          \"Zip\": \"" + fZip + "\",\r\n" +
            "          \"NPI\": \"" + fNPI + "\",\r\n" +
            "          \"TIN\": \"" + fTIN + "\",\r\n" +
            "          \"Phone1\": \"" + fPhone + "\",\r\n" +
            "          \"Phone2\": \"746-655-2864\",\r\n" +
            "          \"Phone3\": \"586-128-4316\",\r\n" +
            "          \"Fax\": \"498-946-3184\",\r\n" +
            "          \"ClientProviderId\": \"3435\"\r\n" +
            "        }\r\n" +
            "      },\r\n" +
            "      {\r\n" +
            "        \"Provider\": {\r\n" +
            "          \"Name\": \"\",\r\n" +
            "          \"LastName\": \"Robinson\",\r\n" +
            "          \"FirstName\": \"Holly\",\r\n" +
            "          \"Address1\": \"3399 Kevin ExtensionApt. 770\",\r\n" +
            "          \"Address2\": \"\",\r\n" +
            "          \"City\": \"Harringtonbury\",\r\n" +
            "          \"State\": \"HI\",\r\n" +
            "          \"Zip\": \"36756\",\r\n" +
            "          \"NPI\": \"1073138939\",\r\n" +
            "          \"TIN\": \"499249204\",\r\n" +
            "          \"Phone1\": \"672-418-1811\",\r\n" +
            "          \"Phone2\": \"242-244-8446\",\r\n" +
            "          \"Phone3\": \"366-449-9188\",\r\n" +
            "          \"Fax\": \"168-994-3569\",\r\n" +
            "          \"ClientProviderId\": \"619\"\r\n" +
            "        }\r\n" +
            "      }\r\n" +
            "    ]\r\n" +
            "  }\r\n" +
            "}";

        return payLoad;
    }
}

 