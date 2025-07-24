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
            }
            is StudentCommand.GetStudentsByAge -> {
                val filteredStudents = students.filter { it.age == command.age }

            }
        }
    }
}



