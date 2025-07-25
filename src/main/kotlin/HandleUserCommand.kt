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
                students.add(command.student)
            }
            is StudentCommand.GetStudents -> {

                println("All students:")
                students.forEach { println(it) }
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


            is StudentCommand.RemoveStudentById -> students.removeIf { it.id == command.id }
            is StudentCommand.UpdateStudentById-> {
                val studentIndex = students.indexOfFirst { it.id == command.id }
                if (studentIndex == -1) {
                    println("Student with ID $command.id not found.")
                    return
                }

                val current = students[studentIndex]
                updateFieldsChoices(students, studentIndex, current)
            }

        }
    }
}
