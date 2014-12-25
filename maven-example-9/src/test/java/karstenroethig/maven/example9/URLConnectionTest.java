package karstenroethig.maven.example9;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.DatatypeConverter;

import junit.framework.TestCase;

public class URLConnectionTest extends TestCase {

	public void testConnection() throws Exception {
		
		URL url = new URL( "http://www.google.de" /* "http://localhost:8090" */ );
		String username = null;
		String password = "";
		
		assertNotNull( url );

		InputStream in = null;
		
		try {
			URLConnection urlConnection = url.openConnection();
			
			if( username != null ) {
				
				String userpass = username + ":" + password;
				String basicAuth = "Basic " + new String( DatatypeConverter.printBase64Binary( userpass.getBytes() ) );
				
				urlConnection.setRequestProperty( "Authorization", basicAuth );
			}
			
			assertTrue( urlConnection instanceof HttpURLConnection );
			
			HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
			
			int statuscode = httpConnection.getResponseCode();
			
			assertEquals( statuscode, HttpURLConnection.HTTP_OK );
			
			in = httpConnection.getInputStream();
			
			BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );
			
			String line = "";
			
			while( ( line = reader.readLine() ) != null ) {
				System.out.println( line );
			}
			
		} finally {
			
			if( in != null ) {
				in.close();
			}
		}
	}
}
