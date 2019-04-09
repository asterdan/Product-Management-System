package DyqanClasses;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.jar.JarException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import net.aksingh.owmjapis.AbstractForecast.Forecast;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import org.apache.cxf.jaxrs.json.basic.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.mongodb.util.JSON;

import java.lang.Object;

//Klasa qe merr te dhenat nga api
public class OpenWeatherMapWrapper { 
	//Members
	private Weather weather;
	private WeatherCatalog weatherCatalog;
	private String date;
	private OpenWeatherMap owm;		 
	private CurrentWeather cwd;
	private String location = "Tirana";
	private String appId = "0a9a5ffbdd741513b8ca9939cbcf5c88";
	private Weather[] weatherList;
	private StringBuffer sb;

    //Constructors
	public OpenWeatherMapWrapper()
	{
		this.sb = new StringBuffer();
		this.weatherList = new Weather[5];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		this.date = dtf.format(localDate);
		this.weatherCatalog = new WeatherCatalog();
		this.owm = new OpenWeatherMap("d0c4849020f191275564bacf2ebc6d24");
		this.weather = new Weather();
	    try {
			this.cwd = owm.currentWeatherByCityName("Tirana,ALB");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public OpenWeatherMapWrapper(WeatherCatalog _weatherCatalog)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		this.date = dtf.format(localDate);
		this.weatherCatalog = _weatherCatalog;
	}
	
	public OpenWeatherMapWrapper(WeatherCatalog _weatherCatalog,String _date)
	{
		this.weatherCatalog = _weatherCatalog;
		this.date = _date;
	}
	
	public OpenWeatherMapWrapper(WeatherCatalog _weatherCatalog,String _date,OpenWeatherMap _owm,CurrentWeather _cwd,Weather _weather)
	{
		this.weatherCatalog = _weatherCatalog;
		this.date = _date;
		this.owm = _owm;
		this.cwd = _cwd;
		this.weather = _weather;
	}

	
    //Methods
	
	
	
	public Weather[] getForecast(WeatherExtracter extractor,RawJsonGetter jsonGetter)
	{
		this.weatherList = extractor.extractWeather(jsonGetter);
        return this.weatherList;
	}
	
	
	public Weather returnWeather() //Kthen motin aktual
	{
		return this.weather;
	}



	

}
