package karstenroethig.maven.example9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer {

	public static void main( String[] args ) throws Exception {
		
		ServerSocket server = new ServerSocket( 8090 );

		Socket session = server.accept();

		BufferedReader reader = new BufferedReader( new InputStreamReader( session.getInputStream() ) );

		String line = null;
		
		while( ( line = reader.readLine() ) != null ) {
			System.out.println( line );
		}
	}
}
