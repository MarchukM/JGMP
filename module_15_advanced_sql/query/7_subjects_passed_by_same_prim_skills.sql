select su."name", count(distinct st.primary_skill) from student st, subject su, exam_result er
	where st.id = er.student_id
	and er.subject_id = su.id
	and mark >= 8
	group by su."name"
	having count(distinct st.primary_skill) = 1
	order by su."name"