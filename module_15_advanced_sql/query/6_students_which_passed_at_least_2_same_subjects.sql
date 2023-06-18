--Select students who passed at least two exams for the same subject.
--Note: since it's not specified what exactly means 'passed exam' I will count all marks >= 8

select distinct st.surname||' '||st.name as full_name from student st, subject su, exam_result er
	where st.id = er.student_id
	and er.subject_id = su.id
	and mark >= 8
	group by full_name, su."name"
	having count(su."name") >= 2