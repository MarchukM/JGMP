-- Create function that will return student at "red zone" (red zone means at least 2 marks <=3).
CREATE OR REPLACE FUNCTION get_students_in_red_zone()
RETURNS TABLE(full_name TEXT) AS $$
BEGIN
    RETURN QUERY (with tmp as (
	select concat_ws(' ', first_name, last_name) as full_name, count(er.exam_grade) as num_of_bad_marks from students s, exam_results er
		where s.student_id = er.student_id
		and er.exam_grade <= 3
		group by full_name
		order by full_name
	) select tmp.full_name from tmp where num_of_bad_marks > 1);
END; $$
LANGUAGE plpgsql;

-- Test get_students_in_red_zone() function.
select get_students_in_red_zone()