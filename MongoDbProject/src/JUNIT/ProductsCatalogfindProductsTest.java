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

class ProductsCatalogfindProductsTest {
	private List<Umbrella> testDb;

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
	void findProductsByName()
	{
		testDb = getList();
		MongoDbProductClient client = Mockito.mock(MongoDbProductClient.class);
		Mockito.when(client.get()).thenReturn(testDb);
		ProductsCatalog pc = new ProductsCatalog();
		assertEquals(true,pc.findProductByName("NameTest", client));
		
	}
}
