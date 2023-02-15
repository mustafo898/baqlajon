package uz.rounded.data.remote.dto.main.courses.lesson_finish

data class LessonFinishRequestDto(
    val courseId: String,
    val grammarScore: Int,
    val homeworkScore: Int,
    val lessonId: String,
    val listeningScore: Int,
    val score: Int,
    val speakingScore: Int,
    val type: String
)