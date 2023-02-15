package uz.rounded.data.remote.dto.main.courses.group

data class GroupDetailDto(
    val _id: String,
    val beginDate: String,
    val commentCount: Int,
    val course: CourseDetailDto,
    val currentStudentCount: Int,
    val days: String,
    val discount: Int?=null,
    val endTime: String,
    val imgUrl: String,
    val introText: String,
    val name: String,
    val price: Int,
    val startTime: String,
    val teacher: TeacherDto,
    val totalStudents: Int,
    val videoUrl: String
)