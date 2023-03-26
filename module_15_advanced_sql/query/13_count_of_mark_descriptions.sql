--Select number of all marks for each mark type (‘BAD’, ‘AVERAGE’,…)

select mark_description, count(mark_description) from
 (select st."name"||' '||st.surname as full_name, er.mark,
 		case
			when mark between 1 and 3 then 'BAD'
			when mark between 4 and 6 then 'AVERAGE'
			when mark between 7 and 8 then 'GOOD'
            when mark between 9 and 10 then 'EXCELENT'
            else coalesce(er.mark::text,'not passed')
       	end as mark_description
	from student st left outer join exam_result er on st.id = er.student_id
	order by full_name) as tmp
group by mark_description
