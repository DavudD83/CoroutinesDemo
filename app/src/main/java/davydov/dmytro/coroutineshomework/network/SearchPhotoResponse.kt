package davydov.dmytro.coroutineshomework.network

import com.google.gson.annotations.SerializedName
import davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos.Photo


data class SearchPhotoResponse(
    @SerializedName("hits")
    val photos: List<Photo>
)