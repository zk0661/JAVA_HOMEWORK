# Simple Student Management System(without database)

## Demand

- 设计带GUI界面的软件，能够现实对学生信息（id、姓名、性别、课程成绩等信息，可自行设定）增删查改，学生信息需要通过流操作存放到文件中，第二次打开软件时，能够从文件中将学生信息从文件中读出。

1. Accomplish additions，deletions，changes and  selections to student information.
2. Store data to file.

## Conceptual model
### class
1. Student
    - name, number, score, sex
2. ClassSet
    - just one
    - total number of students
    - organizing student information
    - select student by key word, return the student information
    - add a student, but the number of student cannot be repeated
    - delete a student
    - update information