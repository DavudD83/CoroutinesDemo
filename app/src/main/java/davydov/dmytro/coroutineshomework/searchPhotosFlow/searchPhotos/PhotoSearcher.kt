package davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos

import davydov.dmytro.coroutineshomework.network.SearchEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class PhotoSearcher {
    private var searchJob: Job? = null

    suspend fun search(query: String, order: SearchOrder = SearchOrder.POPULAR): SearchPhotosState {
        searchJob?.cancel().also { searchJob = Job() }

        return try {
            val photos =
                withContext(Dispatchers.IO + searchJob!!) {
                    SearchEngine.search(
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