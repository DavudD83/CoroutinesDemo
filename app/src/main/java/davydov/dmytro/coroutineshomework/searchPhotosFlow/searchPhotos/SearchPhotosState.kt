package davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos


sealed class SearchPhotosState {
    object Error : SearchPhotosState()
    data class SearchResult(val photos: List<Photo>) : SearchPhotosState()
    object EmptySearch : SearchPhotosState()
}