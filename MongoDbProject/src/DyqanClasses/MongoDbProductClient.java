package DyqanClasses;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.PrintConversionEvent;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriter;
import org.json.JSONWriter;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;


//Klase qe sherben si ndihmes per operacione me databasen per objektin cader
public class MongoDbProductClient {
	
    //Members
	private MongoConnection conn;
    private Umbrella umbrella;
    private Price price;
    
    //Methods
    public MongoDbProductClient()
    {
    	this.conn = new MongoConnection();
    }
    
    
    public MongoDbProductClient(Umbrella _umbrella)
    {
    	this.conn = new MongoConnection();
    	this.umbrella = _umbrella;
    }
    
    public MongoDbProductClient(Umbrella _umbrella,Price _price,boolean _flag)
    {
    	this.conn = new MongoConnection();
    	this.umbrella = _umbrella;
    	this.price = new Price(_flag);
    }
    
    public MongoDbProductClient(Umbrella _umbrella,Price _price)
    {
    	this.conn = new MongoConnection();
    	this.umbrella = _umbrella;
    	this.price = _price;
    }
    public MongoDbProductClient(Price _price,boolean _flag)
    {
    	this.conn = new MongoConnection();
    	this.price = new Price(_flag);
    }
    
    //Getters/Setters
    public MongoConnection getMongoConnection()
    {
    	return this.conn;
    }
    public void setMongoConnection(MongoConnection _conn)
    {
    	this.conn = _conn;
    }
    
    public void setUmbrella(Umbrella _umbrella)
    {
    	this.umbrella = _umbrella;
    }
    public Umbrella getUmbrella()
    {
    	return this.umbrella;
    }
    
    public void setPrice(Price _price)
    {
    	this.price = _price;
    }
    
    public Price getPrice()
    {
    	return this.price;
    }
   

    public void insert() //Shtojme nje dokument ne koleksionin cader ne mongo db dmth shtojme cader ne db
    {
    	try
    	{	     
		      List<BasicDBObject> priceHistory = new ArrayList<>(); 
		      for (Price price : this.umbrella.getPriceChanges())
		      {
		    	  priceHistory.add(new BasicDBObject(price.getDate(), price.getPrice()));
		    	  
		      }
		      
		      
		      BasicDBObject doc = new BasicDBObject("Name", this.umbrella.getName()).
	                   append("Descritpion",this.umbrella.getDescription()).
	                   append("Type",this.umbrella.getType()).
	                   append("Price",this.umbrella.getPrice());
	                   
		      doc.put("Price_Changes", priceHistory);
		      conn.collection.insert(doc);
		      System.out.println("Document inserted succesfully!");
	           
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex);
    	}

    }

	
    public void update() // Updatojme rekord te perzgjedhur sipas nje filtri ne tabelen cader ne mongodb
    {                    //Shtojme rekord ne price_changes dhe ndryshojme price
    	               
    	this.conn.cursor = conn.mongoCollection.find().iterator();

    	int  currentprice = getPrice(this.umbrella.getName());
    	int nextPrice = currentprice + this.price.getPrice();
    	//BasicDBObject newDocument = new BasicDBObject();
    	this.conn.object.append("$push", new BasicDBObject("Price_Changes",new BasicDBObject(this.price.getDate(),nextPrice)));				  
    	this.conn.searchQuery = new BasicDBObject().append("Name",this.umbrella.getName());
    	conn.mongoCollection.updateOne(this.conn.searchQuery, this.conn.object);

    	//Document query = new Document();
    	this.conn.query.append("Name",this.umbrella.getName());
    	//Document setData = new Document();
    	this.conn.setData.append("Price",nextPrice);
    	//Document update = new Document();
    	this.conn.update.append("$set", this.conn.setData);  
    	conn.mongoCollection.updateOne(this.conn.query, this.conn.update);
    	System.out.println("Document updated successfully!");  	  

    }
    
    public void update_()  //E njejta si update por perdoret ne rastin kur do ndryshohet vetem data dhe jo cmimi ne price changes
    {
    	
    	MongoCursor<Document> cursor = conn.mongoCollection.find().iterator();

    	int  currentprice = getPrice(this.umbrella.getName());
    	int nextPrice =  this.price.getPrice();
    	BasicDBObject newDocument = new BasicDBObject();
    	newDocument.append("$push", new BasicDBObject("Price_Changes",new BasicDBObject(this.price.getDate(),nextPrice)));				  
    	BasicDBObject searchQuery = new BasicDBObject().append("Name",this.umbrella.getName());
    	conn.mongoCollection.updateOne(searchQuery, newDocument);

    	Document query = new Document();
    	query.append("Name",this.umbrella.getName());
    	Document setData = new Document();
    	setData.append("Price",nextPrice);
    	Document update = new Document();
    	update.append("$set", setData);  
    	conn.mongoCollection.updateOne(query, update);
    	System.out.println("Document updated successfully!");  	  

    }
    
    public void deleteByName() //Fshin nje rekord ne tabelen cader sipas emrit
    {
    	boolean flag = false;
    	DBCursor curs = conn.collection.find();
    	while(curs.hasNext())
    	{ 
    		DBObject object = curs.next();
    		if (Objects.equals(object.get("Name"), this.umbrella.getName()))
    		{
    			flag = true;
    			conn.collection.remove(object);
    			System.out.println("Product: "+object.get("Name") + " removed succesfully!");
    		}

    	}
    	if (flag == false)
    	{
    		System.out.println("No records with this name!");
    	}

    }
	
	public void deleteall() //Fshin gjithe rekordet ne tabelen cader
	{
		
		try
		{
             boolean flag = false;
		     DBCursor curs = conn.collection.find();
		     flag = curs.hasNext();
		     while(curs.hasNext())
		     { 		    	 
		         conn.collection.remove(curs.next()); 
		     }
		     if (flag == true)
		     {
		    	 System.out.println("All documents deleted successfully!");
		     }
		     else
		     {
		    	 System.out.println("No records in the collection!");
		     }
		     
		     
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	
	
	public void getByPrice() //Merr nje rekord ne baze te cmimit
	{
		try
		{
			boolean flag = false;
			MongoConnection conn = new MongoConnection();
		     DBCursor curs = conn.collection.find();
		     while(curs.hasNext())
		     {
		    	 DBObject object = curs.next();
		    	 if (Objects.equals(object.get("Price"), this.umbrella.getPrice()))
		    	 {
		    		 flag = true;
		    		 System.out.println(object.toString()); 
		    	 }
		    	 
		     }
		     if (flag == false)
	    	 {
	    		 System.out.println("No records with this price!");
	    	 }
		     
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	
	public List<Umbrella> get() //Kthen nje liste me te gjithe cadrat(rekorde) nga tabela cader
	{
		List<Umbrella> umbrellas = new ArrayList<>();
		
		    List<Document> productsObjects = (List<Document>)this.conn.mongoCollection.find().into(new ArrayList<Document>());
		    for (int j = 0;j < productsObjects.size();j++)
		    {
		    	Umbrella umbrellaObj = new Umbrella();
		    	Document product = productsObjects.get(j);
		    	umbrellaObj.setName(product.getString("Name"));
		    	umbrellaObj.setDescription(product.getString("Descritpion"));
		    	umbrellaObj.setType(product.getString("Type"));		    	
		    	umbrellaObj.setPrice(product.getInteger("Price"));

		    	List<Document> productPriceHistoryDoc = (List<Document>)product.get("Price_Changes");
		    	List<Price> productPriceHistoryObj = new ArrayList<>();
		    	for (Document priceChange : productPriceHistoryDoc)
		    	{
		    		Price priceRecord = new Price();
		    		String json = priceChange.toJson();
		    		StringBuilder element = new StringBuilder();
		    		StringBuilder element2 = new StringBuilder();
		    		for (int b = 3;b<json.length()-1;b++)
		    		{
		    			if ( b <= 12 && json.charAt(b) != '"'  )
		    			{
		    				char c = json.charAt(b);
		    				if (c == '-')
		    				{
		    					element.append('-');
		    				}
		    				else
		    				{
		    					element.append(c);
		    				}		    				
		    			}
		    			if ( b > 12 && (json.charAt(b) != '"' && json.charAt(b) != ' ' && json.charAt(b) != ':'))
		    			{		    				
		    				char c = json.charAt(b);
		    				element2.append(c);		    				
		    			}
		    			else if (b == json.length()-2)
		    			{
		    				priceRecord.setDate(element.toString());
		    				priceRecord.setPrice(Integer.parseInt(element2.toString()));		    				
		    			}		    			
		    		}
		    		productPriceHistoryObj.add(priceRecord);		    		
		    	}
		    	umbrellaObj.setPriceChanges(productPriceHistoryObj);
		    	umbrellas.add(umbrellaObj);		    	
		    }
		return umbrellas;	
	}
	
	public int getPrice(String name) //Merr cmimin e nje cadre 
	{
		int price = 0;
		try
		{
		     DBCursor curs = conn.collection.find();
		     if (curs.hasNext())
		     {
		    	 while(curs.hasNext())
			     {
			    	 DBObject object = curs.next();
			    	 if (Objects.equals(object.get("Name"),name))
			    	 {
			    		 price = (int)object.get("Price");
			    		 return price;
			    	 }
			    	
			     }
		     }
		     else
		     {
		    	 System.out.println("No records!");
		     }
		     
		     
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		return price;
	}
	
	public Umbrella enterNewProduct() //Input produkt
	{
		String name;
		String desc;
		String type;
		int price;
		ArrayList<Price> priceChang = new ArrayList<Price>();
		Scanner sc = new Scanner(System.in);
		Umbrella product = new Umbrella();
		try
		{
			System.out.println("Enter product name:");
			name = sc.nextLine();
			System.out.println("Enter product description:");
			desc = sc.nextLine();
			System.out.println("Enter product type:");
			type = sc.next();
			System.out.println("Enter product price:");
			price = Integer.parseInt(sc.next());
			System.out.println("Enter historic of price changes:");
			boolean flag = true;
			int i = 0;
			while (flag == true)
			{		
				Price priceHistory = new Price();
				System.out.println("Enter "+i+"th record.");
				System.out.println("Enter date:");
				priceHistory.setDate(sc.next());
				System.out.println("Enter price:");
				priceHistory.setPrice(sc.nextInt());	
				priceChang.add(priceHistory);		
				char c='a';
				while (c!='y'&&c!='n')
				{
					System.out.println("Do you want to continue?(y/n)");
					c = sc.next().charAt(0);
					if (c=='y'||c=='n')
					{
						if (c == 'n')
						{
							System.out.println("Exiting...");
							flag = false;
							break;
						}
					}
					else
					{
						System.out.println("Wrong letter!Type y for yes or n for no!");
					}
				}
			}
			product  = new Umbrella(name,desc,type,price,priceChang);
			
			return product;	
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return product;
			
		}	
	}

}
