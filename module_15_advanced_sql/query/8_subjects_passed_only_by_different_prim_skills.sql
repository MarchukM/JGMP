--Select all subjects which exams passed only students with the different primary skills.
--It means that all students passed the exam for the one subject must have different primary skill

select sub_name, sum(same_skills)
from (select distinct su."name" as sub_name, count(st.primary_skill) as same_skills from student st, subject su, exam_result er
	where st.id = er.student_id
	and er.subject_id = su.id
	and mark >= 8
	group by sub_name, st.primary_skill
	order by sub_name) as foo
group by sub_name
having sum(same_skills) = 1