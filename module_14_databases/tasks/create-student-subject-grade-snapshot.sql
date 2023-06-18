-- Create snapshot that will contain next data: student name, student surname, subject name, mark
-- (snapshot means that in case of changing some data in source table – your snapshot should not change).

create table student_subject_grade_snapshot as
	select st.first_name, st.last_name, su.subject_name, er.exam_grade
	from students st, subjects su, exam_results er
	where st.student_id = er.student_id
	and er.subject_id = su.subject_id;

-- Test query snapshot
select * from student_subject_grade_snapshot;