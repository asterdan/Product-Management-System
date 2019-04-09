package DyqanClasses;
import java.util.List;
import java.util.Objects;

/********************************************************************************************
*           Nuk perdoret ne projekt mund te perdoret ne testim me vone                      *
*********************************************************************************************
*/
public class WeatherCatalog 
{
	//Members
	private Weather weather;
	private List<Weather> weatherHistory;
	
	//Constructors
	public WeatherCatalog()
	{
		MongoDbWeatherClient client = new MongoDbWeatherClient();
	}
	
	public WeatherCatalog(Weather _weather)
	{
		this.weather = _weather;
	}
	public WeatherCatalog(List<Weather> _weatherHistory)
	{
		this.weatherHistory = _weatherHistory;
	}
	
	//Get/Set
	
	public void setWeather(Weather _weather)
	{
		this.weather = _weather;
	}
	
	public Weather getWeather()
	{
		return this.weather;
	}
	
	public void setWeatherHistoryList(List<Weather> _weatherHistory)
	{
		this.weatherHistory = _weatherHistory;
	}
	
	public List<Weather> getWeatherHistoryList()
	{
		return this.weatherHistory;
	}
	
	//Methods
	public void getWeatherHistory()
	{
		this.weatherHistory = getWeatherClient().getRainHistory();
		for (Weather weather : weatherHistory)
		{
			System.out.println(weather.toString());
		}
	}
	
	public void findWeatherByDate(String date)
	{
		boolean flag = false;
		for (Weather weather : this.getWeatherClient().getRainHistory())
		{
			if (Objects.equals(weather.getDate(),date))
			{
				flag = true;
				weather.toString();
			}
		}
		if (flag == false)
		{
			System.out.println("No weather records found for this date!");
		}
	}
	
	public void addWeatherInstance()
	{
		this.getWeatherClient(this.weatherHistory.get(0)).updateRainHistory();
	}
	
	public Weather GetLatestWeather()
	{		
		this.weatherHistory = this.getWeatherClient().getRainHistory();
		int index = this.weatherHistory.size()-1;
		Weather weather = this.weatherHistory.get(index);
		return weather;		
	}
	
	public MongoDbWeatherClient getWeatherClient()
	{
		MongoDbWeatherClient weatherClient = new MongoDbWeatherClient();
		return weatherClient;
	}
	
	public MongoDbWeatherClient getWeatherClient(Weather weather)
	{
		MongoDbWeatherClient weatherClient = new MongoDbWeatherClient(weather);
		return weatherClient;
	}

}
