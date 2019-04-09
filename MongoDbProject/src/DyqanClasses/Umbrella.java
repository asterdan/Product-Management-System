package DyqanClasses;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;


//Klase per cadrat
public class Umbrella {
	
	//Members
	private ObjectId _id;
    private String name;
    private String description;
    private String type;
    private int price;
    private List<Price> PriceChanges = new ArrayList<Price>();
    
    
    //Constructors
    public Umbrella()
    {
    	
    }
    
    public Umbrella(String _name)
    {
    	this.name = _name;
    }
    
    public Umbrella(int _price)
    {
    	this.price = _price;
    }
    
    public Umbrella(String _name,String _description,String _type,int _price,ArrayList<Price> _priceChanges)
    {
    	this.name = _name;
    	this.description = _description;
    	this.type = _type;
    	this.price = _price;
    	this.PriceChanges = _priceChanges;
    }
    
    
    //Getters Setters
    public void setObjectId(ObjectId id)
    {
    	this._id = id;
    }
    
    public ObjectId getObjectId()
    {
    	return this._id;
    }
    
    public void setName(String _name)
    {
    	this.name = _name;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public void setDescription(String _description)
    {
    	this.description = _description;
    }
    
    public String getDescription()
    {
    	return this.description;
    }
    
    public void setType(String _type)
    {
    	this.type = _type;
    }
    
    public String getType()
    {
    	return this.type;
    }
    
    public void setPrice(int _price)
    {
    	this.price = _price;
    }
    
    public int getPrice()
    {
    	return this.price;
    }
    
    public void setPriceChanges(List<Price> prices_)
    {
    	this.PriceChanges = prices_;
    }
    
    public List<Price> getPriceChanges()
    {
    	return this.PriceChanges;
    }

  //Methods
    @Override
    public String toString()
    {
    	StringBuilder text = new StringBuilder();
    	text.append("Name : "+this.name+ "\nDescription: " + this.description + "\nType : " + this.type + "\nPrice : " + this.price);
    	text.append("\n");
    	StringBuilder prices = new StringBuilder();
    	prices.append("Price_Changes : \n");
    	for (int i = 0;i < PriceChanges.size(); i++)
    	{
    		prices.append(PriceChanges.get(i).getDate() + " : " + PriceChanges.get(i).getPrice());
    		prices.append("\n");
    	}
    	
    	text.append(prices);
    	return text.toString();
    }

}
