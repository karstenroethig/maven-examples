package karstenroethig.maven.example6;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import junit.framework.TestCase;
import karstenroethig.maven.example6.jaxb.Issue;
import karstenroethig.maven.example6.jaxb.Issues;
import karstenroethig.maven.example6.jaxb.Project;

public class JaxbTest extends TestCase {

	public void testMarshal() throws Exception {
		
		Project project = new Project();
		
		project.setKey( "KEY" );
		project.setName( "projectname" );
		
		Issues issues = new Issues();
		List<Issue> issuesList = issues.getIssue();
		
		Issue issue = new Issue();
		
		issue.setNumber( new BigInteger( "1" ) );
		issue.setSummary( "Summary" );
		issue.setDescription( "Description" );
		
		issuesList.add( issue );
		
		issue = new Issue();
		
		issue.setNumber( new BigInteger( "2" ) );
		issue.setSummary( "Summary" );
		issue.setDescription( "Description" );
		
		issuesList.add( issue );
		
		project.setIssues( issues );
		
		/*
		 * Marshal
		 */
		JAXBContext context = JAXBContext.newInstance( Project.class );
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );

		File file = new File( "target/" + project.getName() + ".xml" );

		marshaller.marshal( project, file );

		/*
		 * Unmarshal
		 */
		Unmarshaller unmarshaller = context.createUnmarshaller();

		Project proj = (Project)unmarshaller.unmarshal( file );

		System.out.println( "Porject: " +  proj.getName() + " (key: " + proj.getKey() + ")" );
	}
}
