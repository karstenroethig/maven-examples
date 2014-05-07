package karstenroethig.maven.example;

import karstenroethig.maven.example.package1.A;
import karstenroethig.maven.example.package2.B;

public class App {
	
	public static void main( String[] args ) {
	
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
