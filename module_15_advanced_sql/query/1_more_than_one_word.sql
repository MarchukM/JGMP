--Select all primary skills that contain more than one word (please note that both ‘-‘ and ‘ ’ could be used as a separator)

select primary_skill from student
	where primary_skill like '% %'
	or primary_skill like '%-%';