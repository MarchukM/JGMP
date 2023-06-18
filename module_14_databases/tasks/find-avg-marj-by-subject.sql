-- Create function that will return avarage mark for input subject name.
CREATE OR REPLACE FUNCTION get_avg_mark_by_subject(subjectname VARCHAR)
RETURNS NUMERIC AS $$
BEGIN
    RETURN (select ROUND(AVG(er.exam_grade),1) from subjects s, exam_results er
	where s.subject_id = er.subject_id
	and s.subject_name = subjectname);
END; $$
LANGUAGE plpgsql;

-- Test function invocation
select get_avg_mark_by_subject('claims lucky saw transformation barbara rivers licensed formula char odds');