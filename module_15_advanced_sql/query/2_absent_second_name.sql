-- Select all students who does not have second name (it is absent or consists from only one letter/letter with dot)

select * from student where surname = null or surname ~ '^[a-zA-Z]?[.]?$'