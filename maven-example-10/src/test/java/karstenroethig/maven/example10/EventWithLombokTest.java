package karstenroethig.maven.example10;

import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;

public class EventWithLombokTest extends TestCase
{
	@Test
	public void testEvent() throws Exception
	{
		Long id = 1234567890L;
		String title = "My very first event!";
		Date date = new Date();

		// test required args constructor
		EventWithLombok event1 = new EventWithLombok( title, date );

		assertNull( event1.getId() );
		assertEquals( title, event1.getTitle() );
		assertEquals( date, event1.getDate() );

		// test no args constructor and getters/setters
		EventWithLombok event2 = new EventWithLombok();
		event2.setId( id );
		event2.setTitle( title );
		event2.setDate( date );

		assertEquals( id, event2.getId() );
		assertEquals( title, event2.getTitle() );
		assertEquals( date, event2.getDate() );

		EventWithLombok event3 = new EventWithLombok();
		event3.setId( 2345678901L );
		event3.setTitle( "A second event." );
		event3.setDate( new Date() );

		// toString()
		String toString = event2.toString();

		assertNotNull( toString );
		assertTrue( toString.contains( id.toString() ) );
		assertTrue( toString.contains(title) );

		// equals( Object )
		event1.setId( id );
		assertTrue( event1.equals( event2 ) );
		assertFalse( event1.equals( null ) );
		assertFalse( event1.equals( event3 ) );

		// hashCode()
		assertEquals( event1.hashCode(), event2.hashCode() );
		assertTrue( event1.hashCode() != event3.hashCode() );
	}
}
