package uz.rounded.data.remote.dto.main.vocabulary

data class VocabularyDto(
    val _id: String? = null,
    val lessonId: String? = null,
    val audioUrl: String? = null,
    val imgUrl: String? = null,
    val word: String? = null,
    val translation: String? = null,
    val transcript: String? = null,
    val wordGroupId: String? = null,
    val definition: String? = null,
    val example: String? = null,
    val otherWord1: String,
    val otherWord2: String,
    val wordGroup: String,
    val score: Int? = null,
    val isSaved: Boolean
)