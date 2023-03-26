--Select top 5 students who passed their last exam better than average students.

select st.id, st."name"||' '||st.surname as full_name, mark from student st, exam_result er2
	where st.id = er2.student_id
	and er2.id = any (select max(id) as last_exam_id from exam_result er group by er.student_id) --identifying last exam by max exam id for the student
	and mark > (select avg(mark) from exam_result er)
	order by mark desc
	limit 5