package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ContentOfTopic(
    @PrimaryKey
    val id: Int,
    @SerializedName("lang_id")
    val langId: Int,
    @SerializedName("topic_id")
    val topicId: Int,
    var content: String,
    @SerializedName("order_id")
    val order: Int,
    @SerializedName("type_id")
    val type: Int
){
    override fun equals(other: Any?): Boolean {
        if (other is ContentOfTopic) {
            if (other.id == this.id && other.topicId == this.topicId)
                return true
            return false
        }
        return false
    }
}
