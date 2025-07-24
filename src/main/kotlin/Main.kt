package org.example

import org.example.models.Student

fun main() {
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
        println(
            """
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
        )
        print("Enter your choice: ")
        when (readln().toIntOrNull()) {
            1 -> {
                print("Enter ID: ")
                val id = readln().toIntOrNull() ?: 0

                print("Enter name: ")
                val name = readln()

                print("Enter age: ")
                val age = readln().toIntOrNull() ?: 0

                print("Enter grade: ")
                val grade = readln()

                print("Enter status: ")
                val status = readln()

                print("Enter GPA: ")
                val gpa = readln().toDoubleOrNull() ?: 0.0

                val student = Student(name, age, grade, status, gpa, id)
                students.add(student)
            }

            2 -> {
                if (students.isEmpty()) {
                    println("No students available.")
                } else {
                    students.forEach { println(it) }
                }
            }

            3 -> {
                print("Enter grade to filter: ")
                val grade = readln()
                val filtered = students.filter { it.grade == grade }
                if (filtered.isEmpty()) {
                    println("No students found with grade $grade.")
                } else {
                    filtered.forEach { println(it) }
                }
            }

            4 -> {
                print("Enter age to filter: ")
                val age = readln().toIntOrNull() ?: 0
                val filtered = students.filter { it.age == age }
                if (filtered.isEmpty()) {
                    println("No students found with age $age.")
                } else {
                    filtered.forEach { println(it) }
                }
            }

            5 -> {
                print("Enter student ID to update: ")
                val id = readln().toIntOrNull()
                if (id != null) updateStudentById(id) else println("Invalid ID")
            }

            6 -> {
                println("Exiting...")
                return
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

            else -> println("Invalid option. Try again.")
        }
    }
}
