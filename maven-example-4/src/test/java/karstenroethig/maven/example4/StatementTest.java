package karstenroethig.maven.example4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import junit.framework.TestCase;

public class StatementTest extends TestCase {
	
	private Connection conn;
	
	@Override
	protected void setUp() throws Exception {
		
		try {
			Class.forName( "org.h2.Driver" );
		} catch( ClassNotFoundException ex ) {
			System.err.println( "driver not found" );
			
			return;
		}
		
		conn = DriverManager.getConnection( "jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1;MVCC=TRUE", "sa", "" );
	}
	
	@Override
	protected void tearDown() throws Exception {
		
		if( conn != null ) {
			conn.close();
		}
	}
	
	public void testUsage() {
		
		assertNotNull( conn );
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			
			stmt.execute( "drop table EVENTS if exists" );
			stmt.execute( "create table EVENTS ( EVENT_DATE timestamp, title varchar(255) )" );
			
			stmt.execute( "insert into EVENTS ( EVENT_DATE, title ) values ( '2013-01-01', 'Our very first event!' )" );
			stmt.execute( "insert into EVENTS ( EVENT_DATE, title ) values ( '2013-01-01', 'A follow up event' )" );

			rs = stmt.executeQuery( "select * from EVENTS" );
			
			if( rs != null ) {
				
				while( rs.next() ) {
					
					Date date = rs.getDate( "EVENT_DATE" );
					String title = rs.getString( "title" );
					
					System.out.println( "Event (" + date + "): " + title );
				}
			}
			
		} catch( SQLException ex ) {
			fail( ex.getMessage() );
		} finally {
			
			if( stmt != null ) {
				try {
					stmt.close();
				} catch( SQLException ex ) {
					// nothing to do
				}
			}
			
			if( rs != null ) {
				try {
					rs.close();
				} catch( SQLException ex ) {
					// nothing to do
				}
			}
		}
	}

}
