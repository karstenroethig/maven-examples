package karstenroethig.maven.example9;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import junit.framework.TestCase;

public class HttpClientTest extends TestCase {

	public void testGet() throws Exception {
		
		URL url = new URL( "http://www.google.de" /* "http://localhost:8090" */ );
		String username = null;
		String password = "";
		
		assertNotNull( url );

		DefaultHttpClient httpClient = null;
		InputStream in = null;
		
		try {
			httpClient = new DefaultHttpClient();
			
			if( username != null ) {
				
				CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
				credentialsProvider.setCredentials( new AuthScope( url.getHost(), url.getPort() ),
					new UsernamePasswordCredentials( username, password ) );
			}
			
			HttpGet request = new HttpGet( url.toURI() );
			
			HttpResponse response = httpClient.execute( request );
			
			int statuscode = response.getStatusLine().getStatusCode();
			
			assertEquals( statuscode, HttpStatus.SC_OK );
			
			in = response.getEntity().getContent();
			
			BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );
			
			String line = "";
			
			while( ( line = reader.readLine() ) != null ) {
				System.out.println( line );
			}
			
		} finally {
			
			if( in != null ) {
				in.close();
			}
			
			if( httpClient != null ) {
				httpClient.getConnectionManager().shutdown();
			}
		}
	}
}
