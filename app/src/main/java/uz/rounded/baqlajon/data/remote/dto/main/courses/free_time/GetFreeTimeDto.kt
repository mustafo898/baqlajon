package uz.rounded.data.remote.dto.main.courses.free_time

data class GetFreeTimeDto(
    val Friday: List<String>,
    val Monday: List<String>,
    val Saturday: List<String>,
    val Sunday: List<String>,
    val Thursday: List<String>,
    val Tuesday: List<String>,
    val Wednesday: List<String>,
    val _id: String,
    val teacherId: String
)