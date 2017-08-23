package karstenroethig.maven.example;

import com.beust.jcommander.JCommander;

import karstenroethig.maven.example.package1.A;
import karstenroethig.maven.example.package2.B;

public class App
{
	public static void main( String[] args )
	{
		AppParams params = new AppParams();
		JCommander jc = new JCommander( params );

		try
		{
			jc.parse( args );
		}
		catch ( Exception ex )
		{
			jc.usage();

			ex.printStackTrace();
		}

		System.out.println( params.getVerbose() );
		System.out.println( params.getGroups() );
		System.out.println( params.getPassword() );

		execute();
	}

	private static void execute()
	{
		Integer i1 = new Integer( 36 );
		Integer i2 = new Integer( 56 );

		A a = new A();

		a.setTemperature( i1 );
		a.setTemperature( i2 );

		B b = new B();

		b.setTemperature( i1 );
		b.setTemperature( i2 );
	}
}
