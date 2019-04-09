package IntegrationTesting;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import DyqanClasses.OpenWeatherMapWrapper;
import DyqanClasses.RawJsonGetter;
import DyqanClasses.Weather;
import DyqanClasses.WeatherExtracter;
import StubedClasses.RawJsonGetterStub;
import StubedClasses.RawJsonGetterStub2;
import StubedClasses.WeatherExtracterStub;

class IntegrationTesting 
{
	
	@Before
	Weather[] getWeatherList()
	{
		Weather[] weatherToReturn = new Weather[5];
		weatherToReturn[0] = new Weather("2018-12-14","Rainy");
		weatherToReturn[1] = new Weather("2018-12-15","Sunny");
		weatherToReturn[2] = new Weather("2018-12-16","Sunny");
		weatherToReturn[3] = new Weather("2018-12-17","Rainy");
		weatherToReturn[4] = new Weather("2018-12-18","Sunny");		
		return weatherToReturn;
	}
	
	@Before
	String printStubList(Weather[] list)
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<list.length;i++)
		{
			sb.append(list[i].getDate());
			sb.append(":");
			sb.append(list[i].getMoti());
			sb.append(";");
		}
		
		return sb.toString();
	}
	
	
	@Test
	void testCommunicationOpenWeatherWrapperWithWeatherExtractor1_2() //Testojme komunikimin e wrapper me weatherextraktor
	                                                                  // me rawjsongetter stub
	{
		//Case when works
		WeatherExtracter extractor = new WeatherExtracter();
		RawJsonGetterStub jsongetterStub =  new RawJsonGetterStub();		
		OpenWeatherMapWrapper wrapper = new OpenWeatherMapWrapper();
		Assert.assertNotNull(wrapper.getForecast(extractor, jsongetterStub));
			
		//Case when doesn't work
		RawJsonGetterStub2 jsongetterStub2 =  new RawJsonGetterStub2();	
		Assert.assertArrayEquals(null, wrapper.getForecast(extractor, jsongetterStub2));
		Assert.assertTrue(Arrays.equals(null, wrapper.getForecast(extractor, jsongetterStub2)));
		
	}
	
	
	@Test
	void testCommunicationExtractorWithJsonGetter2_3() //testojme komunikimin  e 2 me 3 dhe shikojme nqs testi pare ndikohet
	{
		WeatherExtracter extractor = new WeatherExtracterStub();
		RawJsonGetter jsonGetter = new RawJsonGetter();
		Weather[] returnedWeather = extractor.extractWeather(jsonGetter);
		int counter = returnedWeather.length;
		testCommunicationOpenWeatherWrapperWithWeatherExtractor1_2();
		assertEquals(5,counter);	
		
	}
	
	
	
	
	@Test
	void testAllCommunications1_2_3()  //Testojme komunikimin te tre njesive dhe shikojme nqs testi 2-3 u ndikua
	{
		WeatherExtracter extractor = new WeatherExtracter();
		RawJsonGetter jsonGetter = new RawJsonGetter();
		OpenWeatherMapWrapper wrapper = new OpenWeatherMapWrapper();
		Weather[] returnedWeather = wrapper.getForecast(extractor, jsonGetter);
		int counter = returnedWeather.length;
		testCommunicationExtractorWithJsonGetter2_3();
		assertEquals(5,counter);
	}
	
	
}
