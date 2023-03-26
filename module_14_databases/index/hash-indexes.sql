drop index if exists students_first_name_idx;
drop index if exists students_last_name_idx;
drop index if exists students_phone_number_idx;

create index students_first_name_hash_idx ON students USING HASH (first_name);
create index students_last_name_hash_idx ON students USING HASH (last_name);
create index students_phone_number_hash_idx ON students USING HASH (phone_number);