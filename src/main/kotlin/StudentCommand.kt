package org.example

import org.example.models.Student

sealed class StudentCommand {
    data class AddStudent(val student: Student) : StudentCommand()
    object GetStudents : StudentCommand()
    data class GetStudentsByGrade(val grade: String) : StudentCommand()
    data class GetStudentsByAge(val age: Int) : StudentCommand()
    data class GetStudentsByName(val name: String) : StudentCommand()
    data class GetStudentsByStatus(val status: String) : StudentCommand()
    data class GetStudentsByGpa(val gpa: Double) : StudentCommand()
    data class GetStudentsByGpaRange(val minGpa: Double, val maxGpa: Double) : StudentCommand()
}