package DyqanClasses;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class RawJsonGetter 
{
	StringBuilder sb;
	private String location = "Tirana";
	private String appId = "0a9a5ffbdd741513b8ca9939cbcf5c88";
	private String appId2 = "648c39696974fafd4d49a7e96319b15f";
	protected String rawJson;
	
	public RawJsonGetter()
	{
		this.sb = new StringBuilder();
		rawJson = "";
	}
	
	public String getRawJSON()
	{
		String ls="";
		this.sb.append("https://api.openweathermap.org/data/2.5/forecast?q=");
		this.sb.append(this.location);
		this.sb.append("&appid=");
		this.sb.append(this.appId2);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(this.sb.toString());	
		try {
			ls =  target.request().get(String.class).toString();
			return ls;
		}
		catch(Exception ex)
		{
			//System.out.println(ex.getMessage());
		}
		this.rawJson = ls;
		return this.rawJson;
	}

}
