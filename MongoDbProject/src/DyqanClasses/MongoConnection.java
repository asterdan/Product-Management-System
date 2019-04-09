package DyqanClasses;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


//Klase per te konfiguruar lidhjen me databasen mongo
public class MongoConnection 
{
	//Members
    private String db = "dyqan";
    private String collect;
    private String user = "Aster";
    private String password = "1234";
    public Logger mongoLogger;
    public MongoClient mongo;
    public MongoCredential credential;
    public DB database; 
    public DBCollection collection ;
    MongoDatabase mdatabase;
    public MongoCollection mongoCollection;
    public MongoCursor<Document> cursor;
    public BasicDBObject object;
    public BasicDBObject searchQuery;
    public Document doc;
    public Document query;
    public Document setData;
    public Document update;
    
    
    //Constructors
    public MongoConnection()
    {
    	this.collect = "cadra";
    	mongoLogger = Logger.getLogger( "org.mongodb.driver" );
    	mongoLogger.setLevel(Level.SEVERE); 
    	mongo = new MongoClient("localhost",27017);
    	credential = MongoCredential.createCredential(user, db,password.toCharArray()); 
    	database = mongo.getDB(db); 
    	mdatabase = mongo.getDatabase(db);
    	collection = database.getCollection(collect);
    	mongoCollection = mdatabase.getCollection(collect);
    	object = new BasicDBObject();
    	doc = new Document();
    	searchQuery = new BasicDBObject();
    	query = new Document();
    	setData = new Document();
    	update = new Document();
    }
    
    
    public MongoConnection(String _collect)
    {
    	this.collect = _collect;
    	mongoLogger = Logger.getLogger( "org.mongodb.driver" );
    	mongoLogger.setLevel(Level.SEVERE); 
    	mongo = new MongoClient("localhost",27017);
    	credential = MongoCredential.createCredential(user, db,password.toCharArray()); 
    	database = mongo.getDB(db); 
    	mdatabase = mongo.getDatabase(db);
    	collection = database.getCollection(collect);
    	mongoCollection = mdatabase.getCollection(collect);
    }
    
    //Getters Setters
    
   

}
