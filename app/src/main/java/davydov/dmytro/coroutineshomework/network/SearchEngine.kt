package davydov.dmytro.coroutineshomework.network

import davydov.dmytro.coroutineshomework.BuildConfig
import davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos.Photo
import davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos.SearchOrder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchEngine {

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(ApiKeyInterceptor)
        .build()

    private val pixabayApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PixabayApi::class.java)

    suspend fun search(query: String, order: SearchOrder): List<Photo> {
        val orderForApi = when(order) {
            SearchOrder.LATEST -> LATEST_ORDER_STR
            SearchOrder.POPULAR -> POPULAR_ORDER_STR
        }

        return pixabayApi.search(query, orderForApi).photos
    }

    private const val LATEST_ORDER_STR = "latest"
    private const val POPULAR_ORDER_STR = "popular"
}