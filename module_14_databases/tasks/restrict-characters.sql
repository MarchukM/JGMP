-- Add validation on DB level that will check username on special characters (reject student name with next characters '@', '#', '$')
-- Since username field is absent in my schema I will add restriction for first_name and last_name columns

alter table students
  add constraint check_first_name_prohibited_symbols
  check (first_name !~ '^.*[@#$].*$') ;

alter table students
  add constraint check_last_name_prohibited_symbols
  check (last_name !~ '^.*[@#$].*$') ;

-- Validation test
insert into students (first_name, last_name, birth_date, phone_number, email)
values ('V@sia', 'P$$pkin', '2021-09-27', '88005553535', 'test@test.com');

-- Result:
-- SQL Error [23514]: ERROR: new row for relation "students" violates check constraint "check_first_name_prohibited_symbols"
--  Detail: Failing row contains (100003, V@sia, P$$pkin, 2021-09-27, 88005553535, test@test.com, 2023-01-31 15:42:11.240551, 2023-01-31 15:42:11.240551).