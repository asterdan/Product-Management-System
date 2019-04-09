package DyqanClasses;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Klase per te ruajtur daten dhe cmimin
public class Price {
	//Members
	private String date;
	private int price;
	
	//Constructors
	public Price()
	{
		
	}
	
	
	public Price (int price)
	{
		DateTimeFormatter dtf;
	    dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate;
		localDate = LocalDate.now();
		this.date = dtf.format(localDate);
	    int Price;
	    String weather;
		
		this.price += price;
	}
	
	public Price(String _date,int _price)
	{
		this.date = _date;
		this.price = _price;
	}
	
	public Price(boolean flag)
	{
	    DateTimeFormatter dtf;
	    dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate;
		localDate = LocalDate.now();
		this.date = dtf.format(localDate);
	    int Price;
	    String weather;
		
		if (flag)
			this.price += 200;
		else
			this.price -= 200;
	}
	
	//Getters Setters
	
	public void setDate(String _date)
	{
		this.date = _date;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
	public void setPrice(int _price)
	{
		this.price = _price;
	}
	
	public int getPrice()
	{
		return this.price;
	}
	
	//Methods
	
	@Override
	public String toString()
	{
		String price = "Date: " + this.date +  "Price: " + this.price;
		return price;
	}
	
	
	
	

}
