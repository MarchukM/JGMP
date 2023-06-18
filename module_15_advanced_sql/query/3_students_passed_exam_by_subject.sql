--Select number of students passed exams for each subject and order result by number of student descending.
--Note: since it's not specified what exactly means 'passed exam' I will count all marks > 8

select su."name", count(st.id) as students_passed  from student st, subject su, exam_result er
	where st.id = er.student_id
	and er.subject_id = su.id
	and er.mark > 8
	group by su."name"
	order by students_passed desc