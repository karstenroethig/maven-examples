package karstenroethig.maven.example4;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateAnnotationsTest extends TestCase {
	
	private SessionFactory sessionFactory;
	
	@Override
	protected void setUp() throws Exception {
		
		// A SessionFactory is set up once for an application
		sessionFactory = new Configuration()
			.configure() // configures settings from hibernate.cfg.xml
			.buildSessionFactory();
	}
	
	@Override
	protected void tearDown() throws Exception {
		
		if( sessionFactory != null ) {
			sessionFactory.close();
		}
	}
	
	@SuppressWarnings( { "unchecked" } )
	public void testUsage() {
		
		// create a couple of events...
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save( new Event( "Our very first event!", new Date() ) );
		session.save( new Event( "A follow up event", new Date() ) );
		session.getTransaction().commit();
		session.close();
		
		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		List<Event> result = session.createQuery( "from Event" ).list();
		
		for( Event event : result ) {
			System.out.println( "Event (" + event.getDate() + "): " + event.getTitle() );
		}
		
		session.getTransaction().commit();
		session.close();
	}

}