package org.example

import HandleUserCommand
import Validator.getGrade
import Validator.getStatus
import Validator.getValidAge
import Validator.getValidGpa
import Validator.getValidGpaRange
import Validator.getValidGrade
import Validator.getValidId
import Validator.getValidName
import Validator.getValidStatus
import org.example.models.Student
import showMessage

fun main() {
    val students = mutableListOf<Student>()

    while (true) {
        val menu = """
            Please choose an option:
            1. Add new student
            2. View all students
            3. Filter by grade
            4. Filter by age
            5. Filter by GPA
            6. Filter by GPA range
            7. Filter by name
            8. Filter by status
            9. Update student by ID
            10. Remove student by ID
            11. Exit
        """.trimIndent()
        menu.showMessage()
        val choiceString = "Enter your choice: "
        choiceString.showMessage()

        when (readln().toIntOrNull()) {
            1 -> {
                val name = getValidName()
                val age = getValidAge()
                val gpa = getValidGpa()

                val grade = gpa?.let { getGrade(it) } ?: "Unknown"
                val status = gpa?.let { getStatus(it) } ?: "Pending"

                var randomId: Int
                do {
                    randomId = (1..1000).random()
                } while (students.any { it.id == randomId })

                val student = Student(name, age, grade, status, gpa, randomId).apply {
                    println("Student $name with ID $id created.")
                }

                try {
                    HandleUserCommand.handle(StudentCommand.AddStudent(student), students)
                    val studentId = "your ID is ${student.id}"
                    studentId.showMessage()
                } catch (e: Exception) {
                    println("Error adding student: ${e.message}")
                }
            }

            2 -> HandleUserCommand.handle(StudentCommand.GetStudents, students)

            3 -> {
                val grade = getValidGrade()
                HandleUserCommand.handle(StudentCommand.GetStudentsByGrade(grade), students)
            }

            4 -> {
                val age = getValidAge()
                HandleUserCommand.handle(StudentCommand.GetStudentsByAge(age), students)
            }

            5 -> {
                val gpa = getValidGpa()
                gpa?.let {
                    HandleUserCommand.handle(StudentCommand.GetStudentsByGpa(it), students)
                } ?: println("Invalid GPA.")
            }

            6 -> {
                val minGpa = getValidGpaRange("Min")
                val maxGpa = getValidGpaRange("Max")
                if (minGpa > maxGpa) {
                    val invalidRangeMessage = "Invalid GPA range. Min GPA cannot be greater than Max GPA."
                    invalidRangeMessage.showMessage()
                    continue
                }
                HandleUserCommand.handle(StudentCommand.GetStudentsByGpaRange(minGpa, maxGpa), students)
            }

            7 -> {
                val name = getValidName()
                HandleUserCommand.handle(StudentCommand.GetStudentsByName(name), students)
            }

            8 -> {
                val status = getValidStatus()
                HandleUserCommand.handle(StudentCommand.GetStudentsByStatus(status), students)
            }

            9 -> {
                val id = getValidId()
                HandleUserCommand.handle(StudentCommand.UpdateStudentById(id), students)
            }

            10 -> {
                val id = getValidId()
                try {
                    HandleUserCommand.handle(StudentCommand.RemoveStudentById(id), students)
                } catch (e: Exception) {
                    println("Error removing student: ${e.message}")
                }
            }

            11 -> {
                val exitMessage = "Exiting the program. Goodbye!"
                exitMessage.showMessage()
                return
            }

            else -> {
                val invalidChoiceMessage = "Invalid option. Try again."
                invalidChoiceMessage.showMessage()
            }
        }
    }
}
