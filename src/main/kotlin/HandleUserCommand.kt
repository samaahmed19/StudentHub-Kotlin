import Validator.getGrade
import Validator.getStatus
import Validator.getValidAge
import Validator.getValidGpa
import Validator.getValidName
import org.example.StudentCommand
import org.example.models.Student

object HandleUserCommand {
    fun handle(command: StudentCommand, students: MutableList<Student>) {
        when (command) {
            is StudentCommand.AddStudent -> {
                try {
                    students.add(command.student.apply {
                        println("Student $name is added")
                    })
                } catch (e: Exception) {
                    println("Error adding student ${e.message}")
                }
            }

            is StudentCommand.GetStudents -> {
                println("All students:")
                students.forEach {
                    println("Name: ${it.name}, Age: ${it.age}, GPA: ${it.gpa ?: "N/A"}, Grade: ${it.grade}, Status: ${it.status}, ID: ${it.id}")
                }
            }

            is StudentCommand.GetStudentsByGrade -> {
                val filteredStudents = students.filter { it.grade == command.grade }
                if (filteredStudents.isEmpty()) {
                    println("No students found with grade ${command.grade}.")
                } else {
                    println("Students with grade ${command.grade}:")
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
                val filteredStudents = students.filter { it.gpa != null && it.gpa == command.gpa }
                if (filteredStudents.isEmpty()) {
                    println("No students found with GPA ${command.gpa}.")
                } else {
                    println("Students with GPA ${command.gpa}:")
                    filteredStudents.forEach { println(it) }
                }
            }

            is StudentCommand.GetStudentsByGpaRange -> {
                val filteredStudents = students.filter { it.gpa?.let { g -> g in command.minGpa..command.maxGpa } == true }
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

            is StudentCommand.RemoveStudentById -> {
                try {
                    val removed = students.removeIf { it.id == command.id }
                    if (removed) {
                        println("Student with ID ${command.id} removed.")
                    } else {
                        println("No student found with ID ${command.id}.")
                    }
                } catch (e: Exception) {
                    println("Error removing student: ${e.message}")
                }
            }

            is StudentCommand.UpdateStudentById -> {
                val studentIndex = students.indexOfFirst { it.id == command.id }
                if (studentIndex == -1) {
                    println("Student with ID ${command.id} not found.")
                    return
                }

                val current = students[studentIndex]
                updateFieldsChoices(students, studentIndex, current)
            }

            else -> {
                println("Unknown command")
            }
            }

        }
    }

