object Validator {
    val grades= listOf("A+","A" ,"B+","B", "C","C+", "D","D+", "F")
    fun getValidName(): String {
        while (true) {
            val namePrompt = "Enter name: "
            namePrompt.showMessage()
            val input = readln()
            input.validateName()?.let { return it }
            val invalidNameMessage = "Invalid name. Name cannot be empty."
            invalidNameMessage.showMessage()
        }
    }

    fun getValidAge(): Int {
        while (true) {
            val agePrompt = "Enter age (6-60): "
            agePrompt.showMessage()
            val raw = readln()
            raw.validateAge()?.let { return it }
            val invalidAgeMessage = "Invalid age. Age must be a number between 6 and 60 and cannot be empty."
            invalidAgeMessage.showMessage()
        }
    }

    fun getValidGpa(): Double {
        while (true) {
            val gpaPrompt = "Enter GPA (0.0-4.0): "
            gpaPrompt.showMessage()
            val raw = readln()
            raw.toValidGpa()?.let { return it }
            val invalidGpaMessage = "Invalid GPA. GPA must be a number between 0.0 and 4.0."
            invalidGpaMessage.showMessage()
        }
    }
    fun getValidGrade(): String {
        while (true) {
            val gradePrompt = "Enter grade (${grades.joinToString(", ")}): "
            gradePrompt.showMessage()
            val input = readln()
            input.validateGrade(grades)?.let { return it }
            val invalidGradeMessage = "Invalid grade. Grade must be one of the following: ${grades.joinToString(", ")}."
            invalidGradeMessage.showMessage()
        }
    }

    fun getStatus(gpa: Double): String = gpa.setStatus()

    fun getGrade(gpa: Double): String = gpa.setGrade()
}