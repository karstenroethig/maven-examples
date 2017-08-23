package karstenroethig.maven.example10;

import java.util.Date;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EventWithoutLombok
{
	private Long	id;
	private String	title;
	private Date	date;

	public EventWithoutLombok()
	{
	}

	public EventWithoutLombok( String title, Date date )
	{
		this.title = title;
		this.date = date;
	}

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate( Date date )
	{
		this.date = date;
	}

	public int compareTo( Object obj )
	{
		if ( ( obj == null ) || ( ( obj instanceof EventWithoutLombok ) == false ) )
		{
			return -1;
		}

		EventWithoutLombok castObj = (EventWithoutLombok) obj;
		
		return new CompareToBuilder()
				.append( getId(), castObj.getId() )
				.append( getTitle(), castObj.getTitle() )
				.append( getDate(), castObj.getDate() )
				.toComparison();
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( ( obj == null ) || ( ( obj instanceof EventWithoutLombok ) == false ) )
		{
			return false;
		}

		if ( this == obj )
		{
			return true;
		}

		EventWithoutLombok castObj = (EventWithoutLombok) obj;
		
		return new EqualsBuilder()
				.append( getId(), castObj.getId() )
				.append( getTitle(), castObj.getTitle() )
				.append( getDate(), castObj.getDate() )
				.isEquals();
	}

	@Override
	public int hashCode()
	{
		// pick 2 hard-coded, odd, >0 ints as args
		return new HashCodeBuilder( 1, 31 )
				.append( id )
				.append( title )
				.append( date )
				.toHashCode();
	}

	@Override
	public String toString()
	{
		return new ToStringBuilder( this )
				.append( id )
				.append( title )
				.append( date )
				.toString();
	}
}
