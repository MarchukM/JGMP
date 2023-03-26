--Select number of students with the same exam marks for each subject.

select s."name", mark, count(*) from exam_result er, subject s
	where s.id = er.subject_id
	group by mark, s."name"
	order by s."name", mark