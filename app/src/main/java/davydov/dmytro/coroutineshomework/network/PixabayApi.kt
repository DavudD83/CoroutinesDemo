package davydov.dmytro.coroutineshomework.network

import davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos.Photo
import retrofit2.http.GET
import retrofit2.http.Query


interface PixabayApi {

    @GET
    suspend fun search(
        @Query("q") query: String,
        @Query("order") order: String,
        @Query("image_type") imageType: String = DEFAULT_IMAGE_TYPE,
        @Query("editors_choice") editorsChoice: Boolean = true
    ): List<Photo>

    companion object {
        const val DEFAULT_IMAGE_TYPE = "photo"
    }
}