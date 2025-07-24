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
    val students = mutableListOf<Student>()

    fun removeStudentById(id: Int): Boolean {
        return students.removeIf { it.id == id }
    }

    fun removeStudentByName(name: String): Boolean {
        return students.removeIf { it.name.equals(name, ignoreCase = true) }
    }

    fun updateStudentById(id: Int) {
        val studentIndex = students.indexOfFirst { it.id == id }
        if (studentIndex == -1) {
            println("Student with ID $id not found.")
            return
        }

        println("Enter new name:")
        val name = readln()
        println("Enter new age:")
        val age = readln().toIntOrNull() ?: 0
        println("Enter new grade:")
        val grade = readln()
        println("Enter new status:")
        val status = readln()
        println("Enter new GPA:")
        val gpa = readln().toDoubleOrNull() ?: 0.0

        val updatedStudent = Student(name, age, grade, status, gpa, id)
        students[studentIndex] = updatedStudent
        println("Student updated successfully.")
    }

    while (true) {
        val menu = """
            Please choose an option:
            1. Add new student
            2. View all students
            3. Filter by grade
            4. Filter by age
            5. Update student by ID
            6. Exit
            7. Remove student by ID
            8. Remove student by name
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
                print("Enter student ID to update: ")
                val id = readln().toIntOrNull()
                if (id != null) updateStudentById(id) else println("Invalid ID")
            }

            6 -> {
                println("Exiting...")
                return
                println("Enter ")


            }

            7 -> {
                print("Enter student ID to remove: ")
                val id = readln().toIntOrNull()
                if (id != null && removeStudentById(id)) {
                    println("Student with ID $id removed.")
                } else {
                    println("Student not found.")
                }
            }

            8 -> {
                print("Enter student name to remove: ")
                val name = readln()
                if (removeStudentByName(name)) {
                    println("Student named $name removed.")
                } else {
                    println("Student not found.")
                }
            }

            else -> {
                val invalidChoiceMessage = "Invalid option. Try again."
                invalidChoiceMessage.showMessage()
            }
        }
    }
}


