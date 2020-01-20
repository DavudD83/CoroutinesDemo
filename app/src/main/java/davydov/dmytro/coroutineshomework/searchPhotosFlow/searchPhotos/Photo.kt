package davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("webformatURL")
    val url: String,
    @SerializedName("likes")
    val likesCount: Int
)