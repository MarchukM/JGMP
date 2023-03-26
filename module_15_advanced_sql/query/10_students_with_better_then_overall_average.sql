--Select all students whose average mark is bigger than overall average mark.

select st.id, st."name"||' '||st.surname as full_name, avg(mark) as average from student st, exam_result er
    where st.id = er.student_id
    group by st.id, full_name
    having avg(mark) > (select avg(mark) from exam_result er)