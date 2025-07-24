import Validator.getGrade
import Validator.getStatus
import Validator.getValidAge
import Validator.getValidGpa
import Validator.getValidName
import org.example.models.Student

fun updateFieldsChoices(
    students: MutableList<Student>,
    studentIndex: Int,
    current: Student
){
    val updateMenu = """
            Update student :
            1. Update name
            2. Update age
            3. Update GPA
            4. Cancel
        """.trimIndent()
    updateMenu.showMessage()
    when (readln().toIntOrNull()) {
        1 -> {
            val newName = getValidName()
            students[studentIndex] = current.copy(name = newName)
            println("Name updated successfully.")
        }
        2 -> {
            val newAge = getValidAge()
            students[studentIndex] = current.copy(age = newAge)
            println("Age updated successfully.")
        }
        3 -> {
            val newGpa = getValidGpa()
            val grade = getGrade(newGpa)
            val status = getStatus(newGpa)
            students[studentIndex] = current.copy(gpa = newGpa, grade = grade, status = status)
            println("GPA updated successfully.")
        }
        4 -> println("Update cancelled.")
        else -> println("Invalid choice. No changes made.")
    }
}