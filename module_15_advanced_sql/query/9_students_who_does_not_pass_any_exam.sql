--Select students who does not pass any exam using each the following operator: – 0.5 points.

--Outer join
select st.id, st."name"||' '||st.surname as full_name
    from student st
	left outer join exam_result er ON st.id = er.student_id
	where mark is null

--Subquery with ‘not in’ clause
select st.id, st."name"||' '||st.surname as full_name
	from student st
	where st.id not in (select er.student_id  from exam_result er)

--Subquery with ‘any ‘ clause
select st.id, st."name"||' '||st.surname as full_name
	from student st
	where not st.id = ANY (select er.student_id from exam_result er);
