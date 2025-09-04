package com.cotiviti.hdap.APIPayLoads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;

public class ChaseRequestPOJO extends apiCommonLib {
	public static Map<String, Object> header = new HashMap<>();
	public static Map<String, Object> claim = new HashMap<>();
	public static Map<String, Object> details = new HashMap<>();
	static Map<String, Object> member1Details = new HashMap<>();
	static Map<String, Object> member2Details = new HashMap<>();
	static Map<String, Object> providerDetails = new HashMap<>();
	static Map<String, Object> providerDetails2 = new HashMap<>();
	public static Map<String, Object> chasePayload = new HashMap<>();
	public static Map<String, Object> chaseRequest = new HashMap<>();
	public static List<Map<String, Object>> members = new ArrayList<>();
	public static List<Map<String, Object>> providers = new ArrayList<>();

	public static String chasereqpayload;

	public String cotiviticlaimnumber = cotivitiClaim();

	public void buildChaseRequest() throws JsonProcessingException {

		// ChaseRequestHeader

		header.put("IntendedUse", "MRA");
		header.put("AuditType", "DRG");
		header.put("ChartType", "Medical");
		header.put("RelatedChaseID", "911");
		header.put("ExternalChaseNumber", "894986377");
		header.put("GlobalWorkforceApproved", false);
		header.put("ExpirationDate", "2025-12-09");
		header.put("MRAvailableInClientSystem", false);
		header.put("MRClientSystemDCN", "8478579337");
		header.put("ProspectiveClaimFlag", false);
		header.put("ClientID", 13);
		header.put("AccountID", "Z610");
		header.put("SubAccountId", "D888");
		header.put("PortalRequestCode", "46378");
		header.put("PlanName", "6");
		header.put("PrimaryChaseRequestProviderRole", "RenderingProvider");
		header.put("ExplicitRequestGroupId", null);

		// ClaimDetails

		claim.put("CotivitiClaimNumber", cotiviticlaimnumber);
		claim.put("ClaimType", "S");
		claim.put("InNetwork", true);
		claim.put("ClientClaimNumber", "5106326342");
		claim.put("DateOfServiceStart", "2019-01-01");

		claim.put("DateOfServiceEnd", "2025-01-01");
		claim.put("TotalClaimAmt", 1666.09);
		claim.put("ProviderSpecialty", "test");
		claim.put("PatientMemberIdx", 2);
		claim.put("SubscriberMemberIdx", 1);
		claim.put("BillingProviderIdx", 1);
		claim.put("RenderingProviderIdx", 2);

		// ChaseDetails

		details.put("DOSChartRangeStart1", "2022-09-26");
		details.put("DOSChartRangeEnd1", "2022-09-26");
		details.put("DOSChartRangeStart2", "2022-09-26");
		details.put("DOSChartRangeEnd2", "2022-09-26");
		details.put("DOSChartRangeStart3", "2022-09-26");
		details.put("DOSChartRangeEnd3", "2022-09-26");
		details.put("DOSChartRangeStart4", "2022-09-26");
		details.put("DOSChartRangeEnd4", "2022-09-26");
		details.put("DeliveryDate", "2022-09-26");
		details.put("RetrievalRank", 8);
		details.put("HedisMeasureID", "HEDIS26");
		details.put("MeasurementGap", "Measure2");
		details.put("SubMeasurementGap", "SubMeasure5");
		details.put("IsOversampleFlag", true);
		details.put("ChasePriority", "High");
		details.put("RADVEnrolleeID", "RADV3354");
		details.put("MedicareAdvantagecontractNumber", "MA353");

		// Members

		member1Details.put("LastName", "John");
		member1Details.put("FirstName", "Doe");
		member1Details.put("Address1", "048 Hinton Loop Apt. 872");
		member1Details.put("Address2", "Apt. 736");
		member1Details.put("City", "Kathleenside");
		member1Details.put("State", "KY");
		member1Details.put("Zip", "62417");
		member1Details.put("DOB", "1983-04-18");
		member1Details.put("Gender", "M");
		member1Details.put("SSN", "495-92-8935");
		member1Details.put("PatientId", "4145");
		member1Details.put("HICN", "716351468");
		member1Details.put("ControlNumber", "9679");
		member1Details.put("MBI", "98716");
		member1Details.put("RecordNumber", "87618");
		member1Details.put("ClientMemberID", "89225");
		Map<String, Object> member1Wrapper = new HashMap<>();
		member1Wrapper.put("Member", member1Details);

		// Add wrapper to the list
		members.add(member1Wrapper);
		// Second member

		member2Details.put("LastName", "Evan");
		member2Details.put("FirstName", "Thomas");
		member2Details.put("Address1", "644 Rachel Ridge Apt. 945");
		member2Details.put("Address2", "Suite 660");
		member2Details.put("City", "East Victoriaborough");
		member2Details.put("State", "MS");
		member2Details.put("Zip", "62417");
		member2Details.put("DOB", "1983-04-18");
		member2Details.put("Gender", "M");
		member2Details.put("SSN", "699-92-8935");
		member2Details.put("PatientId", "2145");
		member2Details.put("HICN", "716321468");
		member2Details.put("ControlNumber", "9679");
		member2Details.put("MBI", "98716");
		member2Details.put("RecordNumber", "87628");
		member2Details.put("ClientMemberID", "77340");
		Map<String, Object> member2Wrapper = new HashMap<>();
		member2Wrapper.put("Member", member2Details);
		members.add(member2Wrapper);

		// Final JSON structure
		Map<String, Object> finalJsonmem = new HashMap<>();
		finalJsonmem.put("Members", members);

		// System.out.println(new
		// com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(finalJsonmem));

		// Providers1

		// First provider

		providerDetails.put("Name", "Leblanc Chiropractic");
		providerDetails.put("LastName", "");
		providerDetails.put("FirstName", "");
		providerDetails.put("Address1", "33259 Anthony Streets Suite 145");
		providerDetails.put("Address2", "Suite 358");
		providerDetails.put("City", "West Jenna");
		providerDetails.put("State", "LA");
		providerDetails.put("Zip", "60923");
		providerDetails.put("NPI", "1699078592");
		providerDetails.put("TIN", "628766168");
		providerDetails.put("Phone1", "147-764-4955");
		providerDetails.put("Phone2", "746-655-2864");
		providerDetails.put("Phone3", "586-128-4316");
		providerDetails.put("Fax", "498-946-3184");
		providerDetails.put("ClientProviderId", "P628385031024");

		Map<String, Object> providerWrapper = new HashMap<>();
		providerWrapper.put("Provider", providerDetails);
		providers.add(providerWrapper);

		// Second provider
		// Map<String, Object> providerDetails2 = new HashMap<>();
		providerDetails2.put("Name", "Roberts Evan");
		providerDetails2.put("LastName", "");
		providerDetails2.put("FirstName", "");
		providerDetails2.put("Address1", "644 Rachel Ridge Apt. 945");
		providerDetails2.put("Address2", "Suite 660");
		providerDetails2.put("City", "East Victoriaborough");
		providerDetails2.put("State", "MS");
		providerDetails2.put("Zip", "24232");
		providerDetails2.put("NPI", "1619313491");
		providerDetails2.put("TIN", "161931349");
		providerDetails2.put("Phone1", "998-877-6655");
		providerDetails2.put("Phone2", "746-655-2864");
		providerDetails2.put("Phone3", "586-128-4316");
		providerDetails2.put("Fax", "498-946-3184");
		providerDetails2.put("ClientProviderId", "P628385031024");

		Map<String, Object> providerWrapper2 = new HashMap<>();
		providerWrapper2.put("Provider", providerDetails2);
		providers.add(providerWrapper2);

		// Serialize

		// Final ChaseRequest map

	}
}
