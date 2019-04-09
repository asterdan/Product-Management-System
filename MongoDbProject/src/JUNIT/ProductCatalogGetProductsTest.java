package JUNIT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import DyqanClasses.MongoDbProductClient;
import DyqanClasses.Price;
import DyqanClasses.ProductsCatalog;
import DyqanClasses.Umbrella;

class ProductCatalogGetProductsTest {

	@Before
	List<Umbrella> getList()
	{
		List<Umbrella> products = new ArrayList<Umbrella>();
		Price price = new Price("2012-02-20",500);
		ArrayList<Price> priceHistory = new ArrayList<Price>();
		priceHistory.add(price);
		Umbrella umb = new Umbrella("NameTest","DescTest","TypeTest",300,priceHistory);
		products.add(umb);
		return products;
	}


	@Test
	void getProductsTest()
	{
		MongoDbProductClient client = Mockito.mock(MongoDbProductClient.class);
		Mockito.when(client.get()).thenReturn(getList());
		Umbrella product = Mockito.mock(Umbrella.class);
		Mockito.when(product.toString()).thenReturn("OK");
		List<Umbrella> products = Mockito.mock(List.class);
		Mockito.when(products.isEmpty()).thenReturn(false);
		ProductsCatalog pc = new ProductsCatalog(products);
		assertEquals(true,pc.getProducts(client));		
	}
	
	
}
