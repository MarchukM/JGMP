--Select biggest mark for each student and add text description for the mark (use COALESCE and WHEN operators) – 0.3 points.
--  In case if student has not passed any exam ‘not passed' should be returned.
--  If student mark is 1,2,3 – it should be returned as ‘BAD’
--  If student mark is 4,5,6 – it should be returned as ‘AVERAGE’
--  If student mark is 7,8 – it should be returned as ‘GOOD’
--  If student mark is 9,10 – it should be returned as ‘EXCELLENT’

select st."name"||' '||st.surname as full_name, max(er.mark),
 		case
			when MAX(mark) between 1 and 3 then 'BAD'
			when MAX(mark) between 4 and 6 then 'AVERAGE'
			when MAX(mark) between 7 and 8 then 'GOOD'
            when MAX(mark) between 9 and 10 then 'EXCELLENT'
            else coalesce(max(er.mark)::text,'not passed')
       	end as mark_description
	from student st left outer join exam_result er on st.id = er.student_id
	group by full_name
	order by full_name