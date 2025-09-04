package com.cotiviti.hdap.APIPayLoads;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;

public class RequestGroupCreateContacts2 extends apiCommonLib {

	String ContactType;
	String Department;
	String Email;
	String Fax;
	String Name;
	String Phone;
	String PreferredMethod;

	public String requestGroupCreateContactsPayload(ConcurrentHashMap<String, String> objMap) {

		ContactType = objMap.get("ContactType");
		Department = objMap.get("Department");
		Email = objMap.get("Email");
		Fax = objMap.get("Fax");
		Name = objMap.get("Name");
		Phone = objMap.get("Phone");
		PreferredMethod = objMap.get("PreferredMethod");

		List<Preference> preferences = new ArrayList<>();
		Set<String> keys = objMap.keySet();
		String StartTime;
		String EndTime;
		for (String key : keys) {
			if (key.contains("Preference")) { // Only process preference-related keys
				// Split key to get day and preference
				String[] parts = key.split("Preference");
				String day = parts[0]; // "Monday", "Tuesday", etc.

				// Extract start and end times from objMap (assuming times are stored as
				// "Start|End")
				String timeString = objMap.get(key);
				if (timeString != null && !timeString.isEmpty()) {
					String[] times = timeString.split("\\|");
					if (times.length == 2) {
						StartTime = times[0].trim();
						EndTime = times[1].trim();
						preferences.add(new Preference(day, StartTime, EndTime));
					}
				} else {
					preferences.add(new Preference(day, null, null)); // In case there's no time, just day.
				}
			}
		}
		JSONArray preferencesArray = new JSONArray();
		for (Preference pref : preferences) {
			preferencesArray.put(pref.toString());
		}

		JSONObject obj = new JSONObject(createContactsPayload);

		JSONArray jsonArray = obj.getJSONObject("SameAsRequestGroupPreferences").getJSONArray("Preferences");

		System.err.println("********");
		System.err.println(obj.toString());
		return obj.toString();

	}

	class Preference {
		String dayOfWeek;
		String startTime;
		String endTime;

		public Preference(String dayOfWeek, String startTime, String endTime) {
			this.dayOfWeek = dayOfWeek;
			this.startTime = startTime;
			this.endTime = endTime;
		}
		
		   // Getters
	    public String getDayOfWeek() {
	        return dayOfWeek;
	    }

	    public String getStartTime() {
	        return startTime;
	    }

	    public String getEndTime() {
	        return endTime;
	    }
	}

	String createContactsPayload = "{\r\n" + "    \"ContactType\": \"" + ContactType + "\",\r\n"
			+ "    \"Department\": \"" + Department + "\",\r\n" + "    \"Email\": \"" + Email + "\",\r\n"
			+ "    \"Fax\": \"" + Fax + "\",\r\n" + "    \"Name\": \"" + Name + "\",\r\n" + "    \"Phone\": \"" + Phone
			+ "\",\r\n" + "    \"Ext\": \"1\",\r\n" + "    \"PreferredMethod\": \"" + PreferredMethod + "\",\r\n"
			+ "    \"Role\": \"Dev\",\r\n" + "    \"SameAsRequestGroupPreferences\": {\r\n"
			+ "        \"TimeZone\": \"US/Alaska\",\r\n" + "        \"Value\": true,\r\n"
			+ "        \"Preferences\": [\r\n" + "            {\r\n" + "                \"DayOfWeek\": \"Monday\",\r\n"
			+ "                \"EndTime\": \"5:30 AM\",\r\n" + "                \"StartTime\": \"5:28 AM\"\r\n"
			+ "            },\r\n" + "            {\r\n" + "                \"DayOfWeek\": \"Tuesday\",\r\n"
			+ "                \"EndTime\": \"5:30 AM\",\r\n" + "                \"StartTime\": \"5:28 AM\"\r\n"
			+ "            },\r\n" + "            {\r\n" + "                \"DayOfWeek\": \"Wednesday\"\r\n"
			+ "            },\r\n" + "            {\r\n" + "                \"DayOfWeek\": \"Thursday\"\r\n"
			+ "            },\r\n" + "            {\r\n" + "                \"DayOfWeek\": \"Friday\"\r\n"
			+ "            },\r\n" + "            {\r\n" + "                \"DayOfWeek\": \"Saturday\"\r\n"
			+ "            },\r\n" + "            {\r\n" + "                \"DayOfWeek\": \"Sunday\"\r\n"
			+ "            }\r\n" + "        ]\r\n" + "    }\r\n" + "}";

}
