package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	
	public String getDataFronJsonFile(String key) throws IOException, ParseException
	{
		FileReader fir=new FileReader("./configAppData/AppData.json");
		JSONParser par=new JSONParser();
		Object obj = par.parse(fir);
		JSONObject map=(JSONObject)obj;
		System.out.println(map.get("url"));
		
		String data=(String) map.get(key);
		return data;
		
	}

}
