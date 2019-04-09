package DyqanClasses;

/********************************************************************************************
*           Nuk perdoret ne projekt mund te perdoret ne testim me vone                      *
*********************************************************************************************
*/
public class WeatherUpdater 
{
	//Members
	private WeatherCatalog weatherCatalog;
	
	//Constructors
	public WeatherUpdater()
	{
		
	}
	
	//Get/Set
	public WeatherUpdater(WeatherCatalog _weatherCatalog)
	{
		this.weatherCatalog = _weatherCatalog;
	}
	
	public void setWeatherCatalog(WeatherCatalog _weatherCatalog)
	{
		this.weatherCatalog = _weatherCatalog;
	}
	
	public WeatherCatalog getWeatherCatalog()
	{
		return this.weatherCatalog;
	}
	
	//Methods
	/*
	public void updateWeather(OpenWeatherMapWrapper wrapper)
	{
		wrapper.getForecast();
		if(wrapper.compareWeatherAndDate())
		{
			this.weatherCatalog.setWeather(wrapper.returnWeather());
			this.weatherCatalog.setWeather(wrapper.returnWeather());;
			this.weatherCatalog.addWeatherInstance();
			System.out.println("Rain history updated!");
		}
		{
			System.out.println("The weather is the same or the date is the same!");
		}
	}
	*/

}
