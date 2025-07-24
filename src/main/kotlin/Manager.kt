package org.example

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

    fun getStudentsByName(name: String): List<Student> {
        return students.filter { it.name == name }
    }
    fun  getStudentsByStatus(status: String): List<Student> {
        return students.filter { it.status == status }
    }
    fun  getStudentsByGpa(gpa: Double): List<Student> {
        return students.filter { it.gpa == gpa }
    }
    fun getStudentsByGpaRange(min: Double, max: Double): List<Student> {
        return students.filter { it.gpa in min..max }
    }
}