package DyqanClasses;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherExtracter {
	private Weather[] weatherList;
	
	public WeatherExtracter()
	{
		this.weatherList = null;
	}
	
	public WeatherExtracter(Weather[] _weatherList)
	{
		this.weatherList = _weatherList;
	}
	
	
	
	public Weather[] extractWeather(RawJsonGetter rawJsonGetter)
	{
		String rawJson = rawJsonGetter.getRawJSON();
		Weather[] weather = null;
		try
		{
			this.weatherList = new Weather[5];
			JSONObject json = new JSONObject(rawJson.toString());
			JSONArray obj = json.getJSONArray("list");
			int j = 0;
			for (int i=0;i<obj.length();i=i+8)
			{
				JSONObject inner = obj.getJSONObject(i);
				JSONArray array = inner.getJSONArray("weather");
				JSONObject object = array.getJSONObject(0);
				String motiM = object.getString("main");
				String dataM = inner.getString("dt_txt");
				String data = dataM.substring(0, Math.min(dataM.length(), 10));
				Weather motipersot = new Weather(data,motiM);
				this.weatherList[j] = motipersot;
				j++;
			}
			
		}
		catch(JSONException ex)
		{
			//System.out.println(ex.getMessage());
			this.weatherList = null;
		}
		
		return this.weatherList;
		
	}

}
