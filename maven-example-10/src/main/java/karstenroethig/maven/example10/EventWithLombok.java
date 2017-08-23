package karstenroethig.maven.example10;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EventWithLombok
{
	private Long	id;
	
	@NonNull
	private String	title;
	
	@NonNull
	private Date	date;
}
