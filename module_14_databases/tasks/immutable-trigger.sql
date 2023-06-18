drop table if exists student_address;

-- Create initial table
create table student_address(
    student_name VARCHAR (50) not null,
    address text not null
);

-- Seed some data
insert into student_address(student_name, address) values ('Test Testovich', 'St.Peterburg');
insert into student_address(student_name, address) values ('Ivan Ivanov', 'Moscow');
insert into student_address(student_name, address) values ('Petr Petrov', 'Turkey');

-- Create function which on update create tmp table an insert new row here instead of initial one
create function student_address_update_trigger() returns trigger language plpgsql as $$
begin
	create table if not exists student_address_tmp as table student_address;

	insert into student_address_tmp (student_name, address)
		values (new.student_name, new.address);

  	new.student_name = old.student_name;
 	new.address = old.address;
	return new;
end $$;

-- Create trigger
create trigger student_address_trigger before
update on student_address for each row execute procedure student_address_update_trigger();

-- Test
update student_address
set student_name = 'Update Updatovich'
where student_name = 'Test Testovich';