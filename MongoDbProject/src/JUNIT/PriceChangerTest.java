package JUNIT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import DyqanClasses.OpenWeatherMapWrapper;
import DyqanClasses.Price;
import DyqanClasses.PriceChanger;
import DyqanClasses.ProductsCatalog;
import DyqanClasses.RawJsonGetter;
import DyqanClasses.Umbrella;
import DyqanClasses.Weather;
import DyqanClasses.WeatherExtracter;

class PriceChangerTest {
	private List<Umbrella> testDb;
	private static int counter = 0;
	
	@Before
	List<Umbrella> getTestProducts()
	{
		List<Umbrella> products = new ArrayList<Umbrella>();
		ArrayList<Price> prcl = new ArrayList<Price>();
		Price prc = new Price("2018-02-06",400);
		prcl.add(prc);
		Price prc2 = new Price("2018-02-20",600);	
		prcl.add(prc2);
		Price prc3 = new Price("2018-02-20",600);	
		prcl.add(prc3);
		Umbrella umb = new Umbrella("ProductTest1","ProdDesc","ProdType",300,prcl);			
		Umbrella umb2 = new Umbrella("ProductTest2","ProdDesc","ProdType",300,prcl);	
		Umbrella umb3 = new Umbrella("ProductTest2","ProdDesc","ProdType",300,prcl);	
		products.add(umb);
		return products;
	}
	
	@Before
	Weather[] getWeatherTest()
	{
		Weather[] weatherArray = new Weather[5];
		Weather wth = new Weather("2018-09-10","Sunny");
		weatherArray[0] = wth;
		Weather wth1 = new Weather("2018-09-09","Rain");
		weatherArray[1] = wth1;
		Weather wth2 = new Weather("2018-09-09","Rain");
		weatherArray[2] = wth2;
		Weather wth3 = new Weather("2018-09-09","Rain");
		weatherArray[3] = wth3;
		Weather wth4 = new Weather("2018-09-09","Rain");
		weatherArray[4] = wth4;
		return weatherArray;
	}

	@Test
	void changePricesTest()
	{
		this.testDb = getTestProducts();
		System.out.println("Before test!");
		for(int i=0;i<testDb.size();i++)
		{
			System.out.println(testDb.get(i).toString());
		}
		WeatherExtracter extracterMock = Mockito.mock(WeatherExtracter.class);
		OpenWeatherMapWrapper mockWrapper = Mockito.mock(OpenWeatherMapWrapper.class);
		Weather weatherMock = Mockito.mock(Weather.class);
		Mockito.when(mockWrapper.getForecast(Mockito.any(WeatherExtracter.class),Mockito.any(RawJsonGetter.class))).thenReturn(getWeatherTest());
		
		Mockito.when(weatherMock.getMoti()).thenReturn("Rain");
		
		List<Umbrella> products = Mockito.mock(List.class);
		
		Umbrella umb = Mockito.mock(Umbrella.class);

		ProductsCatalog productsCatalog = Mockito.mock(ProductsCatalog.class);
		Mockito.when(productsCatalog.getAllProducts()).thenReturn(this.testDb);
	
		Mockito.doNothing().when(productsCatalog).setProduct(Mockito.any(Umbrella.class));
		Mockito.doNothing().when(productsCatalog).setPrice(Mockito.any(Price.class));
        
		Mockito.doAnswer( new Answer<Void>(){

			public Void answer(InvocationOnMock invocation) throws Throwable {
					testDb.get(counter).getPriceChanges().add(new Price("case1",600));
					testDb.get(counter).setPrice(600);
					counter++;
				return null;
			}

		}).when(productsCatalog).updateProduct();
		Mockito.doAnswer( new Answer<Void>(){

			public Void answer(InvocationOnMock invocation) throws Throwable {
			        testDb.get(counter).getPriceChanges().add(new Price("case2",300));
			        testDb.get(counter).setPrice(300);
					counter++;
				return null;
			}

		}).when(productsCatalog).updateProduct_();
        
		Mockito.when(productsCatalog.getCurrentPrice(umb.getName())).thenReturn(500);
		RawJsonGetter jsonGetter = Mockito.mock(RawJsonGetter.class);
		Mockito.when(jsonGetter.getRawJSON()).thenReturn("1");
		PriceChanger pc = new PriceChanger(productsCatalog);
		
		assertEquals(true,pc.changePrices(mockWrapper,extracterMock,jsonGetter));
		System.out.println("After test!");
		for(int i=0;i<testDb.size();i++)
		{
			System.out.println(testDb.get(i).toString());
		}
        
        
	}


}
