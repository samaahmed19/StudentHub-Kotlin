# 🧩 SDLC Plan for StudentHub

---

## 📋 1. Requirements Phase

In this phase, the team discussed and defined the main features of the application:

- Add new students
- View all students
- Edit existing student data
- Remove students from the system
- Filter students by name, grade, GPA, or status
- Handle nullable values safely using Kotlin tools
- Apply collection operations (e.g., filter, map, reduce)
- Calculate average GPA for passed students

---

## 🎨 2. Design Phase

In this phase, the team:

- Created a **Class Diagram**:
  - `Student` class: represents a student
  - `StudentManager` class: handles operations like add, view, update, delete

- Created a **Sequence Diagram** for the "Add Student" use case

- Chose data storage method using `MutableList<Student>`

---

## 💻 3. Development Phase

In this phase, the team started implementing the core functionality:

- Built `Student`, `StudentManager`, and `Main.kt`
- Developed functions to:
  - Add, view, update, delete, and filter students
- Built a command-line menu in `Main.kt`
- Used Kotlin features for null safety:
  - `let`, `run`, `apply`, `?.`, `?:`, `!!`
- Applied functional operations like:
  - `map`, `filter`, `forEach`, `reduce`, `fold`
- Used GitHub to track progress and collaborate as a team

---

## 🧪 4. Testing Phase

Manual testing was done to verify:

- Correct handling of empty or invalid inputs
- Program stability when nullable fields are missing
- Proper functionality of all menu options
- Filtering and GPA calculations
- Console output correctness

---

## 🚀 5. Deployment Phase

In this phase:

- The project was pushed to GitHub in a clean, structured format
- Diagrams were added inside the `diagrams/` folder
- `README.md` was written to explain the project
- Final review was done before the last push

---

## 🧾 6. Maintenance Phase (Future Scope)

- Improve user experience and input validation
- Add features like:
  - Exporting data to `.csv` or `.txt`
  - Admin login system
  - JSON support
- Fix bugs or issues if reported by users or testers
