package uz.rounded.data.remote.dto.main.courses.lessons

data class UpdateGrammarDto(
    val courseId: String,
    val lessonId: String,
    val grammar: Int,
    val type: String
)