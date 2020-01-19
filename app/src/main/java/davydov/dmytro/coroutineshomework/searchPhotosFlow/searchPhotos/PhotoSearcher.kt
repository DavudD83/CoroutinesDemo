package davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos

import android.util.Log
import davydov.dmytro.coroutineshomework.network.SearchEngine
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class PhotoSearcher private constructor(private val searchEngine: SearchEngine) {
    private var searchJob: Job? = null

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("SEARCH ERROR", "Search failed with an error ${throwable.message}")
    }

    suspend fun search(query: String, order: SearchOrder = SearchOrder.POPULAR): SearchPhotosState {
        searchJob?.cancel().also { searchJob = Job() }

        return try {
            val photos =
                withContext(Dispatchers.IO + searchJob!! + coroutineExceptionHandler) {
                    searchEngine.search(
                        query,
                        order
                    )
                }

            if (photos.isNotEmpty()) {
                SearchPhotosState.SearchResult(photos)
            } else {
                SearchPhotosState.EmptySearch
            }
        } catch (ex: Throwable) {
            SearchPhotosState.Error
        }
    }
}

enum class SearchOrder {
    POPULAR,
    LATEST,
}