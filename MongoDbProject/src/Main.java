
import DyqanClasses.*;

public class Main {
	public static void main(String[] args) {

		OpenWeatherMapWrapper wrapper = new OpenWeatherMapWrapper();
		WeatherExtracter extractor = new WeatherExtracter();
		RawJsonGetter jsonGetter = new RawJsonGetter();
		Weather[] weather = wrapper.getForecast(extractor, jsonGetter);
		for (int i=0;i<weather.length;i++)
		{
			System.out.println(weather[i].getMoti());
		}

	}


}
