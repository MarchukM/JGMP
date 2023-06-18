-- Create function that will return average mark for input user.
create or replace function get_avg_mark(firstname varchar, lastname varchar)
returns numeric as $$
begin
    return (select round(avg(er.exam_grade),1) from students s, exam_results er
	where s.student_id = er.student_id
	and s.first_name = firstname
	and s.last_name = lastname);
end; $$
language plpgsql;

-- Test function.
select get_avg_mark('Rana', 'Cruz');
