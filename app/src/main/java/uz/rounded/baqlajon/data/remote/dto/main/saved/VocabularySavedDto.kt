package uz.rounded.data.remote.dto.main.saved

data class VocabularySavedDto(
    val _id: String,
    val audioUrl: String,
    val transcript: String,
    val translation: String,
    val word: String
)