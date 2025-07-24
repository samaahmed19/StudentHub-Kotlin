package org.example

import org.example.models.Student
import kotlin.collections.mutableListOf

class Manager {
    private val students = mutableListOf<Student>()
    
    fun addStudent(student: Student) {
        students.add(student)
    }
    fun getStudents(): List<Student> {
        return students
    }

    fun getStudentsByGrade(grade: String): List<Student> {
        return students.filter { it.grade == grade }
    }
    fun getStudentsByAge(age: Int): List<Student> {
        return students.filter { it.age == age }
    }
}