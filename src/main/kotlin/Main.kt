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
                val name = readln()

                print("Enter age: ")
                val age = readln().toIntOrNull() ?: 0

                print("Enter GPA: ")
                val gpa = readln().toDoubleOrNull() ?: 0.0

                val grade = getGrade(gpa)
                val status = getStatus(gpa)
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
                val age = readln().toIntOrNull() ?: 0
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

fun getStatus(gpa: Double): String {
    return if(gpa>=2.0){
        "Passed"
    } else {
        "Failed"
    }
}

fun getGrade(gpa: Double): String {
    return when {
        gpa >= 3.8 -> "A+"
        gpa >= 3.4 -> "A"
        gpa >= 3 -> "B+"
        gpa >= 2.8 -> "B"
        gpa >= 2.6 -> "C+"
        gpa >= 2.4 -> "C"
        gpa >= 2.2 -> "D+"
        gpa >= 2.0 -> "D"
        else -> "F"
    }
}