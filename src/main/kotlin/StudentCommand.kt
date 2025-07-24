package org.example

import org.example.models.Student

sealed class StudentCommand {
    data class AddStudent(val student: Student) : StudentCommand()
    object GetStudents : StudentCommand()
    data class GetStudentsByGrade(val grade: String) : StudentCommand()
    data class GetStudentsByAge(val age: Int) : StudentCommand()
}