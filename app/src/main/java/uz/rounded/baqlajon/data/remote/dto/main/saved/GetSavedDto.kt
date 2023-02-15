package uz.rounded.data.remote.dto.main.saved

import uz.rounded.data.remote.dto.main.saved.saved_lesson.LessonSavedDto
import uz.rounded.domain.model.main.saved.saved_lesson.ObjectSavedLessonData

data class GetSavedDto(
    val _id: String,
    val type: String,
    val isLiked: Boolean? = false,
    val vocabulary: VocabularySavedDto? = null,
    val podcast: PodcastSavedDto? = null,
    val forum: ForumSavedDto? = null,
    val lessons: List<LessonSavedDto>? = null,
    val student: StudentDto? = null
)