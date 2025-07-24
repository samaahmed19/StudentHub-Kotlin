package org.example

import HandleUserCommand
import Validator.getGrade
import Validator.getStatus
import Validator.getValidAge
import Validator.getValidGpa
import Validator.getValidGrade
import Validator.getValidName
import org.example.models.Student
import showMessage


fun main() {
    val students=mutableListOf<Student>()
    while (true) {
        val menu = """
            Please choose an option:
            1. Add new student
            2. View all students
            3. Filter by grade
            4. Filter by age
            5. Exit
        """.trimIndent()
        menu.showMessage()
        val choiceString="Enter your choice: "
        choiceString.showMessage()
        when (readln().toIntOrNull()) {
            1 -> {
                val name = getValidName()
                val age = getValidAge()

                val gpa=getValidGpa()

                val grade = getGrade(gpa)
                val status = getStatus(gpa)
                val student = Student(name, age, grade, status, gpa)
                HandleUserCommand.handle(StudentCommand.AddStudent(student), students)
            }

            2 -> HandleUserCommand.handle(StudentCommand.GetStudents, students)

            3 -> {
                val grade=getValidGrade()
                HandleUserCommand.handle(StudentCommand.GetStudentsByGrade(grade), students)
            }

            4 -> {
                val age = getValidAge()
                HandleUserCommand.handle(StudentCommand.GetStudentsByAge(age), students)
            }

            5 -> {
                println("Enter ")


            }

            else -> {
                val invalidChoiceMessage = "Invalid option. Try again."
                invalidChoiceMessage.showMessage()
            }
        }
    }
}

