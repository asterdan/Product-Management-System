package DyqanClasses;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONException;

//Klasa menu perdoret per shfaqjen e guit
public class Menu 
{
	
	//Members

	public Umbrella product;
	public ProductsCatalog pc = new ProductsCatalog();
	public WeatherCatalog wc = new WeatherCatalog();
	public PriceChanger priceChanger;
	public OpenWeatherMapWrapper wrapper;
	public Scanner sc = new Scanner(System.in);
	public MongoDbProductClient client;
	public WeatherExtracter extractor;
	public RawJsonGetter getter;
	
	//Constructors
	public Menu()
	{
		this.extractor = new WeatherExtracter();
		this.product = new Umbrella();
		this.pc = new ProductsCatalog(this.product);
		this.priceChanger = new PriceChanger(pc,wc);
		this.wrapper = new OpenWeatherMapWrapper();
		Scanner sc = new Scanner(System.in);
	    this.client = new MongoDbProductClient();
	    this.getter = new RawJsonGetter();
	}
	
	//Methods
	public void ShowMenu() 
	{
		System.out.println("*****************************************");
		System.out.println("*                                       *");
		System.out.println("*       Welcome To ProductCatalog       *");
		System.out.println("*              Made By:G2               *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");
		System.out.println("*****************************************");
		System.out.println("*                                       *");
		System.out.println("*      Enter from 1 to 5 to choose      *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");
		System.out.println("*****************************************");
		System.out.println("*                                       *");
		System.out.println("*            Type q to quit             *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");

		char c = '0';
		while (c != 'q')
		{
			
			
			System.out.println("*****************************************");
			System.out.println("*        1.Add product                  *");
			System.out.println("*        2.Find products by name        *");
			System.out.println("*        3.Find products by price       *");
			System.out.println("*        4.Show all products            *");
			System.out.println("*        5.Update products              *");
			System.out.println("*        Q.Quit                         *");
			System.out.println("*****************************************");
			
			c = sc.next().charAt(0);
			
			switch(c)
			{
			case '1':
                this.AddProduct();
				break;
			case '2':
                this.FindProductByName();
				break;
			case '3':
				this.FindProductByPrice();
				break;
			case '4':
                this.ShowAllProducts();
				break;
			case '5':
                this.ChangePrices();
				break;
			case 'q':
				break;
		    default:
				System.out.println("Please enter from 1 to 5 to choose action or q to quit!");
				break;
			}
			
		}
		System.out.println("GoodBye!");
		System.exit(0);
	}
	
	public void AddProduct() 
	{
		System.out.println("***************************************");
		System.out.println("*            Add product              *");
		System.out.println("****************************************");
		pc.addProduct(client);
		System.out.println("****************************************");
		System.out.println("*     Products added successfully      *");
		System.out.println("****************************************");
	}
	
	public void FindProductByName()
	{
		System.out.println("****************************************");
		System.out.println("*                                      *");
		System.out.println("*            Find product              *");
		System.out.println("*                                      *");
		System.out.println("****************************************");
		System.out.println("Enter the name of the product you want to find:");
		pc.findProductByName(sc.next(),client);
		
	}
	
	public void FindProductByPrice()
	{
		System.out.println("****************************************");
		System.out.println("*                                      *");
		System.out.println("*            Find product              *");
		System.out.println("*                                      *");
		System.out.println("****************************************");
		System.out.println("Enter the price of the product you want to find:");
		pc.findProductsByPrice(sc.nextInt());
	}
	
	public void ShowAllProducts()
	{
		System.out.println("****************************************");
		System.out.println("*                                      *");
		System.out.println("*          Showing products            *");
		System.out.println("*                                      *");
		System.out.println("****************************************");
		pc.getProducts(client);
	}
	
	public void ChangePrices()
	{
		System.out.println("****************************************");
		System.out.println("*                                      *");
		System.out.println("*          Updating prices             *");
		System.out.println("*                                      *");
		System.out.println("****************************************");
		
		priceChanger.changePrices(wrapper,extractor,getter);
	}
	

}
