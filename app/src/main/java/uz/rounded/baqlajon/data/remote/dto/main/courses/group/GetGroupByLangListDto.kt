package uz.rounded.data.remote.dto.main.courses.group

data class GetGroupByLangListDto(
    val _id: String,
    val beginDate: String,
    val course: CourseDto,
    val currentStudentCount: Int,
    val days: String,
    val discount: Int,
    val endTime: String,
    val imgUrl: String,
    val introText: String,
    val name: String,
    val price: Int,
    val teacher: TeacherDto,
    val totalStudents: Int,
    val videoUrl: String
)