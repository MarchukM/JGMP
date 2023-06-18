drop index if exists students_first_name_gin_idx;
drop index if exists students_last_name_gin_idx;
drop index if exists students_phone_number_gin_idx;

create index students_first_name_gist_idx ON students USING GIST (first_name gist_trgm_ops);
create index students_last_name_gist_idx ON students USING GIST (last_name gist_trgm_ops);
create index students_phone_number_gist_idx ON students USING GIST (phone_number gist_trgm_ops);