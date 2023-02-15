package uz.rounded.data.remote.dto.main.courses.lessons

import uz.rounded.data.remote.dto.main.lesson_vidio.LessonVidioDto

data class GetLessonDetailDto(
    val _id: String,
    val grammar: Int,
    val homework: Int,
    val lesson: LessonDto,
    val listening: Int,
    val score: Int,
    val speaking: Int,
    val vocabulary: Int,
    val isSaved:Boolean,
    val video:LessonVidioDto
)