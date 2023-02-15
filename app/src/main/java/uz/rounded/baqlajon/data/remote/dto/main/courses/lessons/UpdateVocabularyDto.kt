package uz.rounded.data.remote.dto.main.courses.lessons

data class UpdateVocabularyDto(
    val courseId: String,
    val lessonId: String,
    val vocabulary: Int,
    val type: String
)