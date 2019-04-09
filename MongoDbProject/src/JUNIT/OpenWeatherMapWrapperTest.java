package JUNIT;

import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import DyqanClasses.OpenWeatherMapWrapper;
import DyqanClasses.RawJsonGetter;
import DyqanClasses.Weather;
import DyqanClasses.WeatherExtracter;

class OpenWeatherMapWrapperTest {

	private Weather[] weatherList;
	
	@Before
	public Weather[] getWeatherTestList()
	{
		weatherList = new Weather[5];
		for (int i=0;i<5;i++)
		{
			weatherList[i] = new Weather("TestDate","TestWeather");
		}
		
		return this.weatherList;
	}
	
	
	@Test
    public void getForeCastTest()
    {	
		this.weatherList = null;
		System.out.println("Before Test");
		print();
		WeatherExtracter extractorMock = Mockito.mock(WeatherExtracter.class);
		RawJsonGetter jsonGetter = Mockito.mock(RawJsonGetter.class);
		Mockito.when(jsonGetter.getRawJSON()).thenReturn("1");
		Mockito.when(extractorMock.extractWeather(Mockito.any(RawJsonGetter.class))).thenReturn(getWeatherTestList());
		OpenWeatherMapWrapper wrapperTest = new OpenWeatherMapWrapper();
		this.weatherList = wrapperTest.getForecast(extractorMock,jsonGetter);
		assertEquals(getWeatherTestList(), this.weatherList);
		System.out.println("After Test");
		print();
		
    }
	
	public void print()
	{
		boolean flag = false;
		if (weatherList == null)
		{
			System.out.println("No weather extracted!");
			flag = false;
		}
		else
		{
			System.out.println("Extracted Weather:");
			for (int i=0;i<weatherList.length;i++)
			{
				System.out.println(weatherList[i].getDate() + " " + weatherList[i].getMoti());
			}	    
			flag = true;
		}
		if (flag)
		{
			System.out.println("TestSucceded!");
		}
	}
}
