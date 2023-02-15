package uz.rounded.data.remote.dto.main.forum.create_post

data class CreatePostOnForumRequestDto (
    val imgUrl:String,
    val text:String,
    val categoryId:String
)