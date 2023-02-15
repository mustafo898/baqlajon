package uz.rounded.data.remote.dto.main.saved.saved_lesson

import uz.rounded.domain.model.main.saved.saved_lesson.LessonXData

data class LessonSavedDto(
    val _id: String,
    val languageLevel: String,
    val lesson: LessonXData,
    val type: String
)