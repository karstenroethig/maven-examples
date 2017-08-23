package karstenroethig.maven.example4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import junit.framework.TestCase;

public class JdbcTemplateTest extends TestCase
{
	private JdbcTemplate jdbcTemplate;

	@Override
	protected void setUp() throws Exception
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName( "org.h2.Driver" );
		dataSource.setUrl( "jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1;MVCC=TRUE" );
		dataSource.setUsername( "sa" );
		dataSource.setPassword( "" );

		jdbcTemplate = new JdbcTemplate( dataSource );
	}

	public void testUsage()
	{
		assertNotNull( jdbcTemplate );
		
		jdbcTemplate.update( "drop table PROJECT if exists" );
		jdbcTemplate.update( "create table PROJECT ( key varchar( 12 ), name varchar(255) )" );

		/*
		 * INSERT
		 */
		Set<Project> insertPojects = new HashSet<Project>();

		insertPojects.add( new Project( "KEY1", "First project" ) );
		insertPojects.add( new Project( "KEY2", "Another project" ) );

		for ( Project insertProject : insertPojects )
		{
			Object[] params = new Object[]{ insertProject.getKey(), insertProject.getName() };

			jdbcTemplate.update( "insert into PROJECT ( key, name ) values ( ?, ? )", params );
		}

		/*
		 * SELECT a single Object
		 */
		String sql = "select * from PROJECT where key = ?";
		Object[] params = new Object[]{ "KEY1" };
		ProjectRowMapper rowMapper = new ProjectRowMapper();

		Project project = jdbcTemplate.queryForObject( sql, params, rowMapper );

		System.out.println( "Project '" + project.getName() + "' (key: " + project.getKey() + ")" );

		/*
		 * SELECT lists
		 */
		sql = "select * from PROJECT";

		List<Project> projects = jdbcTemplate.query( sql, rowMapper );

		for ( Project proj : projects )
		{
			System.out.println( "Project '" + proj.getName() + "' (key: " + proj.getKey() + ")" );
		}
	}

	private class Project
	{
		private String key;
		private String name;

		public Project()
		{
		}

		public Project( String key, String name )
		{
			this.key = key;
			this.name = name;
		}

		public String getKey()
		{
			return key;
		}

		public void setKey( String key )
		{
			this.key = key;
		}

		public String getName()
		{
			return name;
		}

		public void setName( String name )
		{
			this.name = name;
		}
	}

	private class ProjectRowMapper implements RowMapper<Project>
	{
		@Override
		public Project mapRow( ResultSet rs, int rowNum ) throws SQLException
		{
			Project project = new Project();

			project.setKey( rs.getString( "key" ) );
			project.setName( rs.getString( "name" ) );

			return project;
		}
	}
}
