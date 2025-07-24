package org.example

import org.example.models.Student


fun main() {
    val students=mutableListOf<Student>()
    fun handleUserCommand(command: StudentCommand) {
        when (command) {
            is StudentCommand.AddStudent -> {
                students.add(command.student)
            }
            is StudentCommand.GetStudents -> {
            }
            is StudentCommand.GetStudentsByGrade -> {
                val filteredStudents = students.filter { it.grade == command.grade }
                if (filteredStudents.isEmpty()) {
                    println("No students found with grade ${command.grade}.")
                } else {
                    println("Students with grade ${command.grade}")
                    filteredStudents.forEach { println(it) }
                }
            }
            is StudentCommand.GetStudentsByAge -> {
                val filteredStudents = students.filter { it.age == command.age }
                if (filteredStudents.isEmpty()) {
                    println("No students found with age ${command.age}.")
                } else {
                    println("Students with age ${command.age}:")
                    filteredStudents.forEach { println(it) }
                }
            }
        }
    }
    while (true) {
        println(
            """
            Please choose an option:
            1. Add new student
            2. View all students
            3. Filter by grade
            4. Filter by age
            5. Exit
        """.trimIndent()
        )
        print("Enter your choice: ")
        when (readln().toIntOrNull()) {
            1 -> {
                print("Enter name: ")
                val nameInput = readln()
                val name = if (nameInput.isNotBlank()) nameInput else {
                    println("Invalid name. Name cannot be empty.")
                    continue
                }

                print("Enter age: ")
                val ageRawInput = readln()
                val ageInput = ageRawInput.toIntOrNull()
                val age = if (ageRawInput.isNotBlank() && ageInput != null && ageInput in 6..18) {
                    ageInput
                } else {
                    println("Invalid age. Age must be between 6 and 18 and cannot be empty.")
                    continue
                }

                print("Enter grade: ")
                val grade = readln()

                print("Enter status: ")
                val status = readln()

                print("Enter GPA: ")
                val gpaInput = readln()
                val gpa = if (gpaInput.isNotBlank() && gpaInput.toDoubleOrNull() != null && gpaInput.toDouble() in 0.0..4.0) gpaInput.toDouble() else {
                    println("Invalid GPA. GPA must be a number between 0 and 4 and cannot be empty.")
                    continue
                }

                val student = Student(name, age, grade, status, gpa)
                handleUserCommand(StudentCommand.AddStudent(student))
            }

            2 -> handleUserCommand(StudentCommand.GetStudents)

            3 -> {
                print("Enter grade to filter: ")
                val grade = readln()
                handleUserCommand(StudentCommand.GetStudentsByGrade(grade))
            }

            4 -> {
                print("Enter age to filter: ")
                val ageRawInput = readln()
                val ageInput = ageRawInput.toIntOrNull()
                val age = if (ageRawInput.isNotBlank() && ageInput != null && ageInput in 6..18) {
                    ageInput
                } else {
                    println("Invalid age. Age must be between 6 and 18 and cannot be empty.")
                    continue
                }
                handleUserCommand(StudentCommand.GetStudentsByAge(age))
            }

            5 -> {
                println("Exiting...")
                return
            }

            else -> println("Invalid option. Try again.")
        }
    }
}


