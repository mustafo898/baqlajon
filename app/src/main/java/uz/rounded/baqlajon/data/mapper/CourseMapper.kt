package uz.rounded.baqlajon.data.mapper

import uz.rounded.baqlajon.data.remote.dto.main.course.*
import uz.rounded.baqlajon.domain.model.main.course.*

fun GetCourseDto.toModel(): GetCourseModel {
    return GetCourseModel(
        _id,
        authorId,
        createdAt,
        description,
        image,
        language,
        rating,
        studentCount,
        time,
        title,
        updatedAt,
        videoCount,
        viewCount
    )
}

fun GetByIdCourseDto.toModel(): GetByIdCourseModel {
    return GetByIdCourseModel(
        _id,
        author.toModel(),
        authorId,
        comment.map { it.toModel() },
        createdAt,
        description,
        freeVideo.map { it.toModel() },
        image,
        language,
        rating,
        studentCount,
        time,
        title,
        updatedAt,
        video.map { it.toModel() },
        videoCount,
        viewCount
    )
}

fun RequestCommentModel.toModel(): RequestCommentDto {
    return RequestCommentDto(text, rating)
}

fun VideoDto.toModel(): VideoModel {
    return VideoModel(
        _id,
        courseId,
        createdAt,
        description,
        imageUrl,
        index,
        isFree,
        title,
        time,
        updatedAt,
        videoUrl,
        viewCount
    )
}

fun FreeVideoDto.toModel(): FreeVideoModel {
    return FreeVideoModel(
        _id,
        courseId,
        createdAt,
        description,
        imageUrl,
        index,
        isFree,
        title,
        updatedAt,
        videoUrl,
        viewCount
    )
}

fun CommentDto.toModel(): CommentModel {
    return CommentModel(courseId, createdAt, rating, text, updatedAt)
}

fun AuthorDto.toModel(): AuthorModel {
    return AuthorModel(_id, createdAt, description, firstName, image, lastName, updatedAt)
}

fun GetMyCourseDto.toModel(): GetMyCourseModel {
    return GetMyCourseModel(
        _id,
        completedCount,
        course.toModel(),
        courseId,
        createdAt,
        isSeen,
        status,
        updatedAt,
        userId
    )
}