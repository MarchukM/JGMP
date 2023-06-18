--Add trigger that will update column updated_datetime to current date in case of updating any of student.

create function update_updated_datetime_on_student()
returns trigger as $$
begin
    new.updated_datetime = now();
return new;
end;
$$ language 'plpgsql';

create trigger update_student_updated_datetime
    before update
	on students
    for each row
execute procedure update_updated_datetime_on_student();

-- Test update of the student
update students set phone_number = '89312509189'
where first_name = 'Harold'
and last_name = 'Shan';

-- Verify results
select * from students
where first_name = 'Harold'
and last_name = 'Shan';