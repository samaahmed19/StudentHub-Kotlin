package org.example

import org.example.models.Student

sealed class StudentCommand {
    data class AddStudent(val student: Student) : StudentCommand()
    object GetStudents : StudentCommand()
    data class GetStudentsByGrade(val grade: String) : StudentCommand()
    data class GetStudentsByAge(val age: Int) : StudentCommand()
    data class RemoveStudentByName(val name: String) : StudentCommand()
    data class RemoveStudentById(val id: Int) : StudentCommand()
    data class UpdateStudentByName(val name: String, val updatedStudent: Student) : StudentCommand()
    data class UpdateStudentById(val id: Int, val updatedStudent: Student) : StudentCommand()
}
