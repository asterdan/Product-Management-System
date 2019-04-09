package DyqanClasses;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;


/********************************************************************************************
*           Nuk perdoret ne projekt mund te perdoret ne testim me vone                      *
*********************************************************************************************
*/
public class MongoDbWeatherClient
{
	//Members
	public MongoConnection conn;
    public Weather weather;
    
    //Constructors
    public MongoDbWeatherClient()
    {
    	this.conn = new MongoConnection("rainhistory");
    }
    
    public MongoDbWeatherClient(Weather _rain)
    {
    	this.conn = new MongoConnection("rainhistory");
    	this.weather = _rain;
    }
    
    //Methods
    
    public List<Weather> getRainHistory()
    {
          List<Weather> list = new ArrayList<>();
	      DBCursor cursor = this.conn.collection.find();
	      
	      if(cursor.hasNext())
    	  {
	    	  while (cursor.hasNext())
		      {
	    		  DBObject object = cursor.next();
	    		  Weather weatherInstance = new Weather(object.get("Date").toString(),object.get("HasRained").toString());
	    		  list.add(weatherInstance);
		      }
    		 
    	  }
    	  else
    	  {
    		  System.out.println("No records!");
    	  }
	      
	      return list;
	     
    }
    
    public void updateRainHistory()
    {
    	try
    	{
		      BasicDBObject object = new BasicDBObject("Date",this.weather.getDate())
		    		  .append("HasRained", this.weather.getMoti());
		      this.conn.collection.insert(object);
		      System.out.println("Rain history updated!");
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
    
    
    

}
