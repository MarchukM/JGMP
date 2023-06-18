--Select students who passed at least two (five is more representative in my data set) exams for different subject.
--Note: since it's not specified what exactly means 'passed exam' I will count all marks >= 8

select st.surname ||' '||st."name" as full_name ,count(distinct su."name") from student st, subject su, exam_result er
	where st.id = er.student_id
	and er.subject_id = su.id
	and mark >= 8
	group by full_name
	having count(distinct su."name") > 5