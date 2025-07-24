fun String.validateName(): String? =
    takeIf { it.isNotBlank() }

fun String.validateAge(): Int? =
    toIntOrNull()?.takeIf { it in 6..60 }

fun String.toValidGpa(): Double? =
    toDoubleOrNull()?.takeIf { it in 0.0..4.0 }

fun Double.setStatus(): String =
    if (this >= 2.0) "Passed" else "Failed"
fun String.validateGrade(validGrades: List<String>): String? =
    takeIf { it.isNotBlank() && it in validGrades }

fun Double.setGrade(): String = when {
    this >= 3.8 -> "A+"
    this >= 3.4 -> "A"
    this >= 3.0 -> "B+"
    this >= 2.8 -> "B"
    this >= 2.6 -> "C+"
    this >= 2.4 -> "C"
    this >= 2.2 -> "D+"
    this >= 2.0 -> "D"
    else         -> "F"
}

fun Any?.showMessage() = println(this)
