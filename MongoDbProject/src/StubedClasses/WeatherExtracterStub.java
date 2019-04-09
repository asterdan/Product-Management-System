package StubedClasses;
import DyqanClasses.RawJsonGetter;
import DyqanClasses.Weather;
import DyqanClasses.WeatherExtracter;

public class WeatherExtracterStub extends WeatherExtracter
{
	@Override
	public Weather[] extractWeather(RawJsonGetter getter)
	{
		Weather[] weatherToReturn = new Weather[5];
		weatherToReturn[0] = new Weather("2018-12-14","Rainy");
		weatherToReturn[1] = new Weather("2018-12-15","Sunny");
		weatherToReturn[2] = new Weather("2018-12-16","Sunny");
		weatherToReturn[3] = new Weather("2018-12-17","Rainy");
		weatherToReturn[4] = new Weather("2018-12-18","Sunny");
		
		return weatherToReturn;
	}
	
	
}
