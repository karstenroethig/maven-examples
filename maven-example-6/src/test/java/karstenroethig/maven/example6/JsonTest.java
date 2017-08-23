package karstenroethig.maven.example6;

import org.json.JSONArray;
import org.json.JSONObject;

import junit.framework.TestCase;

public class JsonTest extends TestCase
{
	public void testParseJson() throws Exception
	{
		StringBuffer json = new StringBuffer();

		json.append( "{\n" );
		json.append( "  \"name\": \"Alice\",\n" );
		json.append( "  \"age\": 20,\n" );
		json.append( "  \"address\": {\n" );
		json.append( "    \"streetAddress\": \"100 Wall Street\",\n" );
		json.append( "    \"city\": \"New York\"\n" );
		json.append( "  },\n" );
		json.append( "  \"phoneNumber\": [\n" );
		json.append( "    {\n" );
		json.append( "      \"type\": \"home\",\n" );
		json.append( "      \"number\": \"212-333-1111\"\n" );
		json.append( "    },\n" );
		json.append( "    {\n" );
		json.append( "      \"type\": \"fax\",\n" );
		json.append( "      \"number\": \"646-444-2222\"\n" );
		json.append( "    }\n" );
		json.append( "  ]\n" );
		json.append( "}" );

		System.out.println( json.toString() );

		JSONObject jsonObj = new JSONObject( json.toString() );

		String name = jsonObj.getString( "name" );
		int age = jsonObj.getInt( "age" );

		System.out.println( "Name: " + name );
		System.out.println( "Age: " + age );

		if ( jsonObj.has( "address" ) )
		{
			JSONObject address = jsonObj.getJSONObject( "address" );

			System.out.println( address.getString( "streetAddress" ) + ", " + address.getString( "city" ) );
		}

		JSONArray jsonArray = jsonObj.getJSONArray( "phoneNumber" );

		for ( int i = 0; i < jsonArray.length(); i++ )
		{
			JSONObject entry = jsonArray.getJSONObject( i );

			String type = entry.getString( "type" );
			String number = entry.getString( "type" );

			System.out.println( type + ": " + number );
		}
	}
}
