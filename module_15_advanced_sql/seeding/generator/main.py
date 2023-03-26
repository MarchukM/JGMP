import random
import string

from mimesis import Person
from mimesis.locales import Locale
from mimesis.providers import Text, Datetime
from random import randint
import csv

text = Text()
person = Person(Locale.EN)
date = Datetime()

NUM_OF_STUDENTS = 5000
NUM_OF_SUBJECTS = 100
NUM_OF_GRADES = 100000

primary_skills = ['Programming', 'Web Development', 'Graphic Design', 'Data Science', 'AI and Machine Learning',
                  'Cybersecurity',
                  'Digital Marketing', 'Project Management', 'Mobile Development', 'DevOps', 'Cloud Computing',
                  'UI/UX Design',
                  'Game Development', 'Software Testing', 'IT Support', 'Networking', 'Database Administration',
                  'Data Analysis',
                  'Software Architecture', 'Content Writing', '3D Printing', 'Photography', 'Video Production',
                  'App Development',
                  'Search Engine Optimization', 'Social Media Marketing', 'Business Analysis', 'Software Engineering',
                  'Big Data', 'Blockchain',
                  'Internet of Things', 'Cyberlaw', 'IT Consultancy', 'Digital Forensics', 'Virtual Reality',
                  'Artificial Intelligence',
                  'Cyberpsychology', 'Quantum Computing', 'Mobile Application Security', 'Cyberwarfare',
                  'Cybercrime Investigation', 'Ethical Hacking',
                  'Cryptography', 'Information Systems', 'Cyber Operations', 'Data Privacy', 'Computer Forensics',
                  'Computer Security',
                  'IT Management', 'IT Auditing', 'Software Security', 'Cybersecurity Governance',
                  'Penetration Testing', 'Cybersecurity Policy',
                  'Cybercrime Prevention', 'IT Risk Management', 'IT Compliance', 'Cybersecurity Architecture',
                  'Cloud Security', 'IT Service Management',
                  'Cybersecurity Analytics', 'IT Security Operations', 'IT Compliance Management',
                  'IT Security Architecture', 'IT Security Assessment', 'IT Risk Assessment',
                  'IT Compliance Audit', 'IT Security Governance', 'IT Security Operations Center',
                  'IT Security Incident Response', 'IT Security Compliance',
                  'IT Compliance Framework', 'IT Security Metrics', 'IT Security Architecture Design',
                  'IT Security Risk Management', 'IT Security Compliance Monitoring',
                  'IT Compliance Management Framework', 'IT Compliance Auditing', 'IT Compliance Policy',
                  'IT Compliance Risk Management']

subject_names = ['Mathematics', 'Physics', 'Chemistry',
                 'Biology', 'Computer Science', 'Information Technology',
                 'Electrical Engineering', 'Mechanical Engineering', 'Civil Engineering',
                 'Environmental Science', 'Geology', 'Economics',
                 'Accounting', 'Business-Administration', 'Marketing',
                 'Finance', 'Human Resources', 'Management',
                 'Operations Management', 'Supply Chain Management', 'Law',
                 'Political Science', 'International Relations', 'History',
                 'Anthropology', 'Sociology', 'Psychology',
                 'Philosophy', 'Religious Studies', 'Linguistics',
                 'English Literature', 'Creative Writing', 'Journalism',
                 'Media Studies', 'Film Studies', 'Art History',
                 'Musicology', 'Dance', 'Theatre',
                 'Culinary Arts', 'Fashion Design', 'Interior Design',
                 'Architecture', 'Urban Planning', 'Geography',
                 'Environmental Design', 'Education', 'Child Development',
                 'Special Education', 'Physical Education', 'Athletic Training',
                 'Exercise Science', 'Nutrition', 'Nursing',
                 'Medicine', 'Pharmacy', 'Dentistry',
                 'Veterinary Science', 'Biomedical Engineering', 'Health Administration',
                 'Public Health', 'Social Work', 'Psychology',
                 'Sociology', 'Data Science', 'Artificial Intelligence',
                 'Machine Learning', 'Robotics', 'Cybersecurity',
                 'Database Management', 'Software Development', 'Web Development',
                 'Mobile App Development', 'Game Development', 'Network Administration',
                 'Cloud Computing', 'Data Analytics', 'Business Intelligence',
                 'Marketing Analytics', 'Financial Analytics', 'Supply Chain Analytics',
                 'Data Visualization', 'Data Mining', 'Big Data',
                 'Natural Language Processing', 'Computer-Graphics', 'Human-Computer Interaction',
                 'Virtual Reality', 'Augmented Reality', 'Internet of Things',
                 'Blockchain', 'Cryptography', 'Cyberlaw',
                 'Digital Forensics', 'Ethical Hacking', 'Cloud Security',
                 'Network Security', 'Cybercrime', 'Information Warfare',
                 'Cyberpsychology']


def create_seeding():
    with open('students_data.csv', 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerows(generate_students(NUM_OF_STUDENTS))

    with open('subjects_data.csv', 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerows(generate_subjects())

    with open('exam_grades_data.csv', 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerows(generate_grades(NUM_OF_GRADES))

    with open('phone_numbers_data.csv', 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerows(generate_phone_numbers())


def generate_students(num):
    students = [["name", "surname", "dob", "primary_skill"]]
    for x in range(num):
        students.append([
            person.first_name(),
            get_person_name(),
            date.formatted_date(fmt='%Y-%m-%d'),
            random.choice(primary_skills)
        ])

    return students


def get_person_name():
    x = randint(1, 20)
    if x == 20:
        y = randint(1, 3)
        match y:
            case 1:
                return ''
            case 2:
                return random.choice(string.ascii_uppercase) + '.'
            case 3:
                return random.choice(string.ascii_uppercase)
    return person.name()


def generate_phone_numbers():
    students = [["student_id", "number"]]
    for student_id in range(NUM_OF_STUDENTS):
        students.append([
            student_id + 1,
            person.telephone()
        ])

    return students


def generate_subjects():
    subjects = [["name", "tutor"]]
    for subject_name in subject_names:
        subjects.append([
            subject_name,
            person.full_name()
        ])
    return subjects


def generate_grades(num):
    header = ["student_id", "subject_id", "mark"]
    with_grade = {}
    result = []
    while len(with_grade) < num:
        student_subject = (randint(1, NUM_OF_STUDENTS), randint(1, len(subject_names)))
        if with_grade.get(student_subject) is None:
            with_grade[student_subject] = randint(1, 10)

    for key in with_grade:
        row = list(key)
        row.append(with_grade.get(key))
        result.append(row)

    result.insert(0, header)
    return result


if __name__ == '__main__':
    create_seeding()
