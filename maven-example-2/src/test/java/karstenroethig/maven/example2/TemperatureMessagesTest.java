package karstenroethig.maven.example2;

import java.util.Locale;

import org.junit.Test;

import junit.framework.TestCase;

public class TemperatureMessagesTest extends TestCase
{
	@Test
	public void testMessages()
	{
		System.out.println( TemperatureMessages.changedFromTo( 36, 56 ) );

		Locale.setDefault( Locale.ENGLISH );

		System.out.println( TemperatureMessages.changedFromTo( 56, 36 ) );
	}
}
