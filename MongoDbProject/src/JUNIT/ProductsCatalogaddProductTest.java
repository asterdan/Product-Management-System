package JUNIT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.*;

import DyqanClasses.*;

class ProductsCatalogaddProductTest {
	private List<Umbrella> testDb;
	private static int counter = 0;

	@Before
	Umbrella returnProductTest(int i)
	{
		Price prc = new Price("2012-02-20",500);
		ArrayList<Price> prcList = new ArrayList<Price>();
		prcList.add(prc);
		Umbrella product = new Umbrella("TestProduct"+i,"TestDesc","TestType",500,prcList);
		return product;
	}

	@Test
	void addProductTest()
	{
		this.testDb = new ArrayList<Umbrella>();
		System.out.println("Before test!");
		if (this.testDb.isEmpty())
		{
			System.out.println("No records in db!");
		}
		else
		{
			for (int i=0;i<this.testDb.size();i++)
			{
				System.out.println(this.testDb.get(i).toString());
			}
		}
		MongoDbProductClient client = Mockito.mock(MongoDbProductClient.class);
		Mockito.when(client.enterNewProduct()).thenReturn(new Umbrella("Adding umbrella!"));
		Mockito.doNothing().when(client).setUmbrella(Mockito.any(Umbrella.class));
		Mockito.doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
		        testDb.add(returnProductTest(counter));
				counter++;
			return null;
		}
		}).when(client).insert();
		
		ProductsCatalog pc = new ProductsCatalog();
		assertEquals(true,pc.addProduct(client));
		System.out.println("After test!");
		if (this.testDb.isEmpty())
		{
			System.out.println("No records in db!");
			System.out.println("Test Failed!");
		}
		else
		{
			for (int i=0;i<this.testDb.size();i++)
			{
				System.out.println(this.testDb.get(i).toString());
			}
			System.out.println("Db populated! Test succedes!");
		}
	}
}
