package DyqanClasses;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.json.JSONException;


//Klase qe ndryshon cmimet sipas parashikimit te motit
public class PriceChanger 
{
	//Members
	private ProductsCatalog productsCatalog;
	private WeatherCatalog weatherCatalog;
	private Price newPrice;
	private List<Umbrella> products;
	
	private Weather[] weatherList;
	
	//Constructor
	public PriceChanger()
	{
		this.newPrice = new Price();
		this.productsCatalog = new ProductsCatalog();
		this.weatherCatalog = new WeatherCatalog();
		this.weatherList = new Weather[5];
	}
	
	public PriceChanger(ProductsCatalog _productsCatalog)
	{
		this.newPrice = new Price();
		this.productsCatalog = _productsCatalog;
		this.weatherList = new Weather[5];
	}
	
	public PriceChanger(ProductsCatalog _productsCatalog,WeatherCatalog _weatherCatalog)
	{
		this.newPrice = new Price();
		this.productsCatalog = _productsCatalog;
		this.weatherCatalog = _weatherCatalog;
		this.weatherList = new Weather[5];
	}
	
	//Getters/Setters
	public void setProductsCatalog(ProductsCatalog _productsCatalog)
	{
		this.productsCatalog = _productsCatalog;
	}
	
	public ProductsCatalog getProductsCatalog()
	{
		return this.productsCatalog;
	}
	
	public void setWeatherCatalog(WeatherCatalog _weatherCatalog)
	{
		this.weatherCatalog = _weatherCatalog;
	}
	
	public WeatherCatalog getWeatherCatalog()
	{
		return this.weatherCatalog;
	}
	
	public void setPrice(boolean flag)
	{
		this.newPrice = new Price(flag);
	}
	
	public void setProducts(List<Umbrella> _products)
	{
		this.products = _products;
	}
	
	public List<Umbrella> getProducts()
	{
		return this.products;
	}
	
	//Methods
		
	public boolean changePrices(OpenWeatherMapWrapper wrapper,WeatherExtracter extractor,RawJsonGetter jsongetter) //Merr array me motin per 5 ditet e ardhshme
	{         
	
		//Nepermjet nje algoritmi ndryshon cmimet
		boolean flag = false;
		int counter = 0;
		weatherList =  wrapper.getForecast(extractor,jsongetter);
		this.products = this.productsCatalog.getAllProducts();
		for(int i=0;i<weatherList.length;i++)
		{
			if (Objects.equals(weatherList[i].getMoti(),"Rain"))
			{
				counter++;
			}
		}
		
		if (counter == 0)
		{
			for (Umbrella umb : this.products)
			{
				if (this.productsCatalog.getCurrentPrice(umb.getName())<=300)
				{
					this.productsCatalog.setProduct(umb);
					this.productsCatalog.setPrice(new Price(weatherList[0].getDate(),300));
					this.productsCatalog.updateProduct_();
					flag = true;
				}
				else
				{
					this.productsCatalog.setProduct(umb);
					this.productsCatalog.setPrice(new Price(weatherList[0].getDate(),-200));
					this.productsCatalog.updateProduct();
					flag = true;
				}
			}
		}
		else if (counter == 1 || counter == 2)
		{
			for (Umbrella umb : this.products)
			{
				if (this.productsCatalog.getCurrentPrice(umb.getName())>=1000)
				{
					this.productsCatalog.setProduct(umb);
					this.productsCatalog.setPrice(new Price(weatherList[0].getDate(),1000));
					this.productsCatalog.updateProduct_();
					flag = true;
				}
				else
				{
					this.productsCatalog.setProduct(umb);
					this.productsCatalog.setPrice(new Price(weatherList[0].getDate(),200));
					this.productsCatalog.updateProduct();
					flag = true;
				}
			}
		}
		else if (counter  > 2)
		{
			
			for (Umbrella umb : this.products)
			{
				if (this.productsCatalog.getCurrentPrice(umb.getName())>=1000)
				{
					this.productsCatalog.setProduct(umb);
					this.productsCatalog.setPrice(new Price(weatherList[0].getDate(),1000));
					this.productsCatalog.updateProduct_();

					flag = true;
				}
				else
				{
					this.productsCatalog.setProduct(umb);
					this.productsCatalog.setPrice(new Price(weatherList[0].getDate(),300));
					this.productsCatalog.updateProduct();
					flag = true;
				}
			}
		}
			
			
	    System.out.println("Success!");
		return flag;
	}
		
}
