DROP TABLE IF EXISTS exam_results;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS subjects;

create table students (
    student_id serial primary key,
    first_name VARCHAR (50) not null,
    last_name VARCHAR (50) not null,
    birth_date DATE not null,
    phone_number VARCHAR (25),
    email VARCHAR (255) unique not null,
    created_datetime TIMESTAMP not null default NOW(),
    updated_datetime TIMESTAMP not null default NOW()
);

create table subjects  (
    subject_id serial primary key,
    subject_name VARCHAR (250) not null,
    tutor_firstname VARCHAR (50) not null,
    tutor_lastname VARCHAR (50) not null,
    created_datetime TIMESTAMP not null default NOW(),
    updated_datetime TIMESTAMP not null default NOW()
);

CREATE TABLE exam_results (
    student_id int REFERENCES students (student_id) ON UPDATE CASCADE ON DELETE cascade,
    subject_id int REFERENCES subjects (subject_id) ON UPDATE cascade,
    exam_grade int NOT null,
    CONSTRAINT exam_results_pkey PRIMARY KEY (student_id, subject_id)
);
