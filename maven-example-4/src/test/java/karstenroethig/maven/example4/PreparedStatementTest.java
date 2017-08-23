package karstenroethig.maven.example4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import junit.framework.TestCase;

public class PreparedStatementTest extends TestCase
{
	private Connection conn;

	@Override
	protected void setUp() throws Exception
	{
		try
		{
			Class.forName( "org.h2.Driver" );
		}
		catch ( ClassNotFoundException ex )
		{
			System.err.println( "driver not found" );

			return;
		}

		conn = DriverManager.getConnection( "jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1;MVCC=TRUE", "sa", "" );
	}

	@Override
	protected void tearDown() throws Exception
	{
		if ( conn != null )
		{
			conn.close();
		}
	}

	public void testUsage()
	{
		assertNotNull( conn );

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = conn.prepareStatement( "drop table EVENTS if exists" );
			pstmt.executeUpdate();
			pstmt.close();

			pstmt = conn.prepareStatement( "create table EVENTS ( EVENT_DATE timestamp, title varchar(255) )" );
			pstmt.executeUpdate();
			pstmt.close();

			pstmt = conn.prepareStatement( "insert into EVENTS ( EVENT_DATE, title ) values ( ?, ? )" );

			pstmt.setObject( 1, new Date() );
			pstmt.setString( 2, "Our very first event!" );
			pstmt.executeUpdate();

			pstmt.setObject( 1, new Date() );
			pstmt.setString( 2, "A follow up event" );
			pstmt.executeUpdate();

			pstmt.close();

			pstmt = conn.prepareStatement( "select * from EVENTS" );
			rs = pstmt.executeQuery();

			if ( rs != null )
			{
				while ( rs.next() )
				{
					Date date = rs.getDate( "EVENT_DATE" );
					String title = rs.getString( "title" );

					System.out.println( "Event (" + date + "): " + title );
				}
			}
		}
		catch ( SQLException ex )
		{
			fail( ex.getMessage() );
		}
		finally
		{
			if ( pstmt != null )
			{
				try
				{
					pstmt.close();
				}
				catch ( SQLException ex )
				{
					// nothing to do
				}
			}

			if ( rs != null )
			{
				try
				{
					rs.close();
				}
				catch ( SQLException ex )
				{
					// nothing to do
				}
			}
		}
	}
}
