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

            is StudentCommand.GetStudentsByGpa -> {
                val filteredStudents = students.filter { it.gpa == command.gpa }
                if (filteredStudents.isEmpty()) {
                    println("No students found with GPA ${command.gpa}.")
                } else {
                    println("Students with GPA ${command.gpa}:")
                    filteredStudents.forEach { println(it) }
                }
            }
            is StudentCommand.GetStudentsByGpaRange -> {
                val filteredStudents = students.filter { it.gpa in command.minGpa..command.maxGpa }
                if (filteredStudents.isEmpty()) {
                    println("No students found with GPA between ${command.minGpa} and ${command.maxGpa}.")
                } else {
                    println("Students with GPA between ${command.minGpa} and ${command.maxGpa}:")
                    filteredStudents.forEach { println(it) }
                }
            }
            is StudentCommand.GetStudentsByName -> {
                val filteredStudents = students.filter { it.name.equals(command.name, ignoreCase = true) }
                if (filteredStudents.isEmpty()) {
                    println("No students found with name ${command.name}.")
                } else {
                    println("Students with name ${command.name}:")
                    filteredStudents.forEach { println(it) }
                }
            }
            is StudentCommand.GetStudentsByStatus -> {
                val filteredStudents = students.filter { it.status.equals(command.status, ignoreCase = true) }
                if (filteredStudents.isEmpty()) {
                    println("No students found with status ${command.status}.")
                } else {
                    println("Students with status ${command.status}:")
                    filteredStudents.forEach { println(it) }
                }
            }
        }
    }
    fun getValidName(): String {
        while (true) {
            print("Enter name: ")
            val nameInput = readln()
            if (nameInput.isNotBlank()) {
                return nameInput
            } else {
                println("Invalid name. Name cannot be empty.")
            }
        }
    }
    fun getValidAge(): Int {
        while (true) {
            print("Enter age: ")
            val ageRawInput = readln()
            val ageInput = ageRawInput.toIntOrNull()
            if (ageRawInput.isNotBlank() && ageInput != null && ageInput in 6..60) {
                return ageInput
            } else {
                println("Invalid age. Age must be between 6 and 60 and cannot be empty.")
            }
        }
    }
    fun getValidGpa(): Double {
        while (true) {
            print("Enter GPA: ")
            val gpaInput = readln()
            if (gpaInput.isNotBlank() && gpaInput.toDoubleOrNull() != null && gpaInput.toDouble() in 0.0..4.0) {
                return gpaInput.toDouble()
            } else {
                println("Invalid GPA. GPA must be a number between 0 and 4 and cannot be empty.")
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
                val name = getValidName()
                val age = getValidAge()

                print("Enter grade: ")
                val grade = readln()

                print("Enter status: ")
                val status = readln()
                
                val gpa = getValidGpa()

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
                val age = getValidAge()
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


