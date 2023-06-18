from mimesis import Person
from mimesis.locales import Locale
from mimesis.providers import Text, Datetime
from random import randint
import csv

text = Text()
person = Person(Locale.EN)
date = Datetime()

NUM_OF_STUDENTS = 100000
NUM_OF_SUBJECTS = 1000
NUM_OF_GRADES = 1000000


def create_seeding():
    with open('students_data.csv', 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerows(generate_students(NUM_OF_STUDENTS))

    with open('subjects_data.csv', 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerows(generate_subjects(NUM_OF_SUBJECTS))

    with open('exam_grades_data.csv', 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerows(generate_grades(NUM_OF_GRADES))


def generate_students(num):
    students = [["first_name", "last_name", "birth_date", "phone_number", "email"]]
    for x in range(num):
        students.append([
            person.first_name(),
            person.name(),
            date.formatted_date(fmt='%Y-%m-%d'),
            person.telephone(),
            person.email(unique=True)]),

    return students


def generate_subjects(num):
    subjects = [["subject_name", "tutor_firstname", "tutor_lastname"]]
    for x in range(num):
        subjects.append([
            ' '.join(text.words(quantity=10)),
            person.first_name(),
            person.name()])
    return subjects


def generate_grades(num):
    header = ["student_id", "subject_id", "exam_grade"]
    with_grade = {}
    result = []
    while len(with_grade) < num:
        student_subject = (randint(1, NUM_OF_STUDENTS), randint(1, NUM_OF_SUBJECTS))
        if with_grade.get(student_subject) is None:
            with_grade[student_subject] = randint(1, 5)

    for key in with_grade:
        row = list(key)
        row.append(with_grade.get(key))
        result.append(row)

    result.insert(0, header)
    return result


if __name__ == '__main__':
    create_seeding()
