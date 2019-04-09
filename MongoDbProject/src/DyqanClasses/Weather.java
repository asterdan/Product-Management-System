package DyqanClasses;


//Klase per motin
public class Weather
{
	//Members
	private String date;
	private String moti;
	
	//Constructors
	public Weather()
	{
		
	}
	
	//Constructors

	public Weather(String _date,String _moti)
	{
		this.date = _date;
		this.moti = _moti;
	}
	
	//Getters and Setters
	
	public void setDate(String _date)
	{
		this.date = _date;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
	public void setMoti(String _moti)
	{
		this.moti = _moti;
	}
	
	public String getMoti()
	{
		return this.moti;
	}
	
	//Methods
	
	@Override
	public String toString()
	{
		String weatherString = "Date: " + this.date.toString() + " " + "Moti: " + this.moti;
		return weatherString;
	}
	
	public MongoDbWeatherClient getWeatherClient() //Marrrja e klientit te mongos
	{
		MongoDbWeatherClient client = new MongoDbWeatherClient(this);
		return client;
	}
	

}
