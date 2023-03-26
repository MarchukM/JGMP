-- Find student by name (exact match)
select * from students where first_name = 'Danny';

-- Find student by surname (partial match)
select * from students where last_name like '%Devona%';

-- Find student by phone number (partial match)
select * from students where phone_number like '%0886%';

-- Find student with marks by user surname (partial match)
select st, er.exam_grade from students st, exam_results er
	where st.student_id = er.student_id
	and st.last_name like '%Nicky%'
	and er.exam_grade > 3;