package DyqanClasses;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;


//Klase qe ruan katalog te produkteve
public class ProductsCatalog {
	//Members
	private Price price;
	private Umbrella product;
	private List<Umbrella> products;
	
	//Constructors
	
	public ProductsCatalog()
	{
		this.products = new ArrayList<>();
		this.product = new Umbrella();
	}
	
	
	public ProductsCatalog(Umbrella _umb)
	{
		this.product = _umb;
	}
	
	public ProductsCatalog(Price _price)
	{
		this.price = _price;
	}
	
	public ProductsCatalog(Umbrella umb,Price _price)
	{
		this.price = _price;
		this.product = umb;
	}
	
	
	public ProductsCatalog(List<Umbrella> _products)
	{
		this.products = _products;
	}
		
	//Getters Setters
	
	public void setPrice(Price p)
	{
		this.price = p;
	}
	
	public Price getPrice()
	{
		return this.price;
	}
	
	public void setProduct(Umbrella _p)
	{
		this.product = _p;
	}
	
	public Umbrella getProduct()
	{
		return this.product;
	}
	
	public void setProducts(List<Umbrella> p)
	{
		this.products = p;
	}
	
	public List<Umbrella> getProductsList()
	{
		return this.products;
	}
	//Methods
	
	public boolean addProduct(MongoDbProductClient client)  //Shton product ne database nepermjet mongodbclient.insert()
	{
		boolean flag = false;
		char c = '0';
		int counter = 0;
		while (c != 'q')
		{		
			System.out.println("You will now enter a new user!");
			this.product = client.enterNewProduct();
			System.out.println("User entered!");
			client.setUmbrella(this.product);
			System.out.println("Product get!");
			System.out.println(this.product.toString());
			client.insert();
			System.out.println("Inserted!");
			System.out.println("Do you want to add another user?(Type y=yes/q=quit)");
			Scanner s = new Scanner(System.in);
			c = s.next().charAt(0);
			counter++;
		}
		
		if (counter == 0)
		{
			flag = false;
		}
		else
		{
			flag = true;
		}
		
		return flag;

	}
	
	public boolean findProductByName(String name,MongoDbProductClient client) // Kthen produkte ne baze te emrit
	{
		boolean flag = false;
		this.products = client.get();
		for (Umbrella object : products)
		{
			if (Objects.equals(name, object.getName()))
			{
				flag = true;
				System.out.println(object.toString());
			}
		}
		if (flag==false)
		{
			System.out.println("Product not found!");
		}
		return flag;
	}
	
	public void findProductsByPrice(int price) // Kerkon per produktet ne base te cmimit
	{
		boolean flag = false;
		this.products = this.getMongoDbClient().get();
		for (Umbrella object : this.products)
		{
			if (Objects.equals(object.getPrice(), price))
			{
				flag = true;
				System.out.println(object.toString());
			}
		}
		if (flag==false)
		{
			System.out.println("Product not found!");
		}
	}
	
	public boolean getProducts(MongoDbProductClient client)  //Shfaq te gjitha cadrat
	{
		boolean flag = false;
		this.products = client.get();
		if (this.products.isEmpty()==true)
		{
			flag = false;
			System.out.println("There are no products!");
		}
		for (Umbrella object : this.products)
		{
			flag = true;
			System.out.println(object.toString());
		}   
		return flag;
	}
	
	public List<Umbrella> getAllProducts() //Kthen liste me te gjitha cadrat
	{
		return this.products = this.getMongoDbClient().get();
		
	}
	
	public int getCurrentPrice(String name) //Kthen cmimin aktual te nje cadre sipas emrit
	{
		return this.getMongoDbClient().getPrice(name);
	}
	
	public List<Umbrella> returnProductsList()
	{
		this.products = this.getMongoDbClient().get();
		return this.products;
	}

	
	public void updateProduct() //Update-on cmimin e nje produkti te dhene ne databaze
	{

		this.getMongoDbClient(this.product,this.price).update();		

	}
	
	public void updateProduct_()
	{

		this.getMongoDbClient(this.product,this.price).update_();		

	}

	
	
	public MongoDbProductClient getMongoDbClient()
	{
		MongoDbProductClient client = new MongoDbProductClient();
		return client;
	}
	
	public MongoDbProductClient getMongoDbClient(Umbrella umb)
	{
		MongoDbProductClient client = new MongoDbProductClient(umb);
		return client;
	}
	
	public MongoDbProductClient getMongoDbClient(Umbrella umb,Price price,boolean flag)
	{
		MongoDbProductClient client = new MongoDbProductClient(umb,price,flag);
		return client;
	}
	
	public MongoDbProductClient getMongoDbClient(Umbrella umb,Price price)
	{
		MongoDbProductClient client = new MongoDbProductClient(umb,price);
		return client;
	}

}
