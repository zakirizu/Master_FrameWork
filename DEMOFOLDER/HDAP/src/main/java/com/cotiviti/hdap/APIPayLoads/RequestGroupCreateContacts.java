package com.cotiviti.hdap.APIPayLoads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;

public class RequestGroupCreateContacts extends apiCommonLib {

//	public String requestGroupCreateContactsPayload(ConcurrentHashMap<String, String> objMap) {
//
//		// Create final JSON structure
//		JSONObject obj = null;
//		try {
//			// Contact details extracted from objMap
//			String ContactType = objMap.get("ContactType");
//			String Department = objMap.get("Department");
//			String Email = objMap.get("Email");
//			String Fax = objMap.get("Fax");
//			String Name = objMap.get("Name");
//			String Phone = objMap.get("Phone");
//			String PreferredMethod = objMap.get("PreferredMethod");
//			String timeZone = objMap.get("TimeZone");
//
//			// Prepare preferences
//			List<Preference> preferences = new ArrayList<>();
//			Set<String> keys = objMap.keySet();
//			String StartTime;
//			String EndTime;
//			for (String key : keys) {
//				if (key.contains("Preference")) {
//					String[] parts = key.split("Preference");
//					String day = parts[0];
//
//					String timeString = objMap.get(key);
//					if (timeString != null && !timeString.isEmpty()) {
//						String[] times = timeString.split("\\|");
//						if (times.length == 2) {
//							StartTime = times[0].trim();
//							EndTime = times[1].trim();
//							preferences.add(new Preference(day, StartTime, EndTime));
//						}
//					} else {
//						preferences.add(new Preference(day, null, null));
//					}
//				}
//			}
//
//			// Create JSONArray for preferences
//			JSONArray preferencesArray = new JSONArray();
//			for (Preference pref : preferences) {
//				preferencesArray.put(pref.toJSONObject());
//			}
//
//			obj = new JSONObject();
//			obj.put("ContactType", ContactType);
//			obj.put("Department", Department);
//			obj.put("Email", Email);
//			obj.put("Fax", Fax);
//			obj.put("Name", Name);
//			obj.put("Phone", Phone);
//			obj.put("Ext", "1");
//			obj.put("PreferredMethod", PreferredMethod);
//			obj.put("Role", "Dev");
////			obj.put("IsPrimary", "cc");
//
//			// SameAsRequestGroupPreferences
//			JSONObject sameAsRequestGroupPreferences = new JSONObject();
//			sameAsRequestGroupPreferences.put("TimeZone", timeZone);
//			sameAsRequestGroupPreferences.put("Value", true);
//			sameAsRequestGroupPreferences.put("Preferences", preferencesArray);
//			sameAsRequestGroupPreferences.put("IsPrimary", "false");
//
//			obj.put("SameAsRequestGroupPreferences", sameAsRequestGroupPreferences);
//		} catch (JSONException e) {
//			assertReportFailure(e);
//		}
//		return obj.toString();
//	}

	
	
	public String requestGroupCreateContactsPayload(ConcurrentHashMap<String, String> objMap) {

	    JSONObject obj = null;
	    try {
	        // Extract from objMap
	        String Department = objMap.get("Department");
	        String Email = objMap.get("Email");
	        String Fax = objMap.get("Fax");
	        String Name = objMap.get("Name");
	        String Phone = objMap.get("Phone");
	        String PreferredMethod = objMap.get("PreferredMethod");
	        String timeZone = objMap.get("TimeZone");

	        // Days of week
	        List<String> daysOfWeek = Arrays.asList(
	            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
	        );

	        List<Preference> preferences = new ArrayList<>();
	        for (String day : daysOfWeek) {
	            String key = day + "Preference";
	            String timeString = objMap.get(key);

	            String StartTime = null;
	            String EndTime = null;

	            if (timeString != null && !timeString.isEmpty()) {
	                String[] times = timeString.split("\\|");
	                if (times.length == 2) {
	                    StartTime = times[0].trim();
	                    EndTime = times[1].trim();
	                }
	            }

	            preferences.add(new Preference(day, StartTime, EndTime));
	        }

	        // JSONArray for preferences
	        JSONArray preferencesArray = new JSONArray();
	        for (Preference pref : preferences) {
	            preferencesArray.put(pref.toJSONObject());
	        }

	        // Build final JSON object
	        obj = new JSONObject();

	        // Wrap values with { "Value": ..., "IsValidated": true }
	        obj.put("IsPrimary", new JSONObject()
	            .put("Value", true)
	            .put("IsValidated", true));

	        obj.put("Department", new JSONObject()
	            .put("Value", Department)
	            .put("IsValidated", true));

	        obj.put("Email", new JSONObject()
	            .put("Value", Email)
	            .put("IsValidated", true));

	        obj.put("Fax", new JSONObject()
	            .put("Value", Fax)
	            .put("IsValidated", true));

	        obj.put("Name", new JSONObject()
	            .put("Value", Name)
	            .put("IsValidated", true));

	        // Phone special case with PhoneExt
	        obj.put("Phone", new JSONObject()
	            .put("Value", Phone)
	            .put("PhoneExt", "1")
	            .put("IsValidated", true));

	        obj.put("PreferredMethod", new JSONObject()
	            .put("Value", PreferredMethod)
	            .put("IsValidated", true));

	        obj.put("Role", new JSONObject()
	            .put("Value", "Dev")
	            .put("IsValidated", true));

	        obj.put("IsSameAsRequestGroupPreferences", new JSONObject()
	            .put("Value", false)
	            .put("IsValidated", true));

	        // ContactPreferences block (same as before)
	        JSONObject contactPreferences = new JSONObject();
	        contactPreferences.put("TimeZone", timeZone);
	        contactPreferences.put("Preferences", preferencesArray);

	        obj.put("ContactPreferences", contactPreferences);

	    } catch (JSONException e) {
	        assertReportFailure(e);
	    }
	    return obj.toString();
	}

	
	
	public class Preference {
		private String dayOfWeek;
		private String startTime;
		private String endTime;

		// Constructor
		public Preference(String dayOfWeek, String startTime, String endTime) {
			this.dayOfWeek = dayOfWeek;
			this.startTime = startTime;
			this.endTime = endTime;
		}

		// Method to convert Preference object to JSONObject
		public JSONObject toJSONObject() {
			JSONObject json = new JSONObject();
			json.put("DayOfWeek", dayOfWeek);
			json.put("StartTime", startTime);
			json.put("EndTime", endTime);
			return json;
		}
	}

}

