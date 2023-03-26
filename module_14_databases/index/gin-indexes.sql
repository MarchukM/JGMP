drop index if exists students_first_name_idx;
drop index if exists students_last_name_idx;
drop index if exists students_phone_number_idx;

drop index if exists students_first_name_hash_idx;
drop index if exists students_last_name_hash_idx;
drop index if exists students_phone_number_hash_idx;

CREATE EXTENSION pg_trgm;

create index students_first_name_gin_idx ON students USING GIN (first_name gin_trgm_ops);
create index students_last_name_gin_idx ON students USING GIN (last_name gin_trgm_ops);
create index students_phone_number_gin_idx ON students USING GIN (phone_number gin_trgm_ops);