package davydov.dmytro.coroutineshomework.network

import retrofit2.http.GET
import retrofit2.http.Query


interface PixabayApi {

    @GET("api/")
    suspend fun search(
        @Query("q") query: String,
        @Query("order") order: String,
        @Query("image_type") imageType: String = DEFAULT_IMAGE_TYPE,
        @Query("editors_choice") editorsChoice: Boolean = true,
        @Query("orientation") orientation: String = DEFAULT_ORIENTATION
    ): SearchPhotoResponse

    companion object {
        private const val DEFAULT_IMAGE_TYPE = "photo"
        private const val DEFAULT_ORIENTATION = "horizontal"
    }
}