package davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import davydov.dmytro.coroutineshomework.R
import davydov.dmytro.coroutineshomework.coroutinesBase.FragmentScopeManager
import kotlinx.android.synthetic.main.fragment_photos_search.*
import kotlinx.coroutines.*

val Any?.exhaustive
    get() = this

class SearchPhotosFragment : Fragment() {

    lateinit var photoSearcher: PhotoSearcher

    private val fragmentScopeManager = FragmentScopeManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentScopeManager.createScopeWith(viewLifecycleOwner)
        return inflater.inflate(R.layout.fragment_photos_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photos.run {
            layoutManager = LinearLayoutManager(context!!)
            adapter = PhotosAdapter()
        }

        textQuery.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val orderBy = orderBy.checkedChipId.let { id ->
                    if (id == R.id.popular) {
                        SearchOrder.POPULAR
                    } else {
                        SearchOrder.LATEST
                    }
                }

                searchPhotos(s.toString(), order = orderBy)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        orderBy.setOnCheckedChangeListener { _, _ ->
            textQuery.text = textQuery.text
        }

        orderBy.check(R.id.popular)
    }

    private fun searchPhotos(query: String, order: SearchOrder) {
        fragmentScopeManager.fragmentScope.launch {
            showLoading()

            val resultState = photoSearcher.search(query, order = order)

            hideLoading()

            when (resultState) {
                SearchPhotosState.EmptySearch -> {
                    optionalText.run {
                        visibility = View.VISIBLE
                        text = getString(R.string.empty_search_text)
                    }
                    photos.visibility = View.GONE
                }
                SearchPhotosState.Error -> {
                    optionalText.run {
                        visibility = View.VISIBLE
                        text = getString(R.string.error_search_text)
                    }
                    photos.visibility = View.GONE
                }
                is SearchPhotosState.SearchResult -> {
                    optionalText.visibility = View.GONE
                    photos.visibility = View.VISIBLE

                    val adapter = (photos.adapter as PhotosAdapter)
                    adapter.updatePhotos(resultState.photos)
                }
            }.exhaustive
        }
    }

    private fun showLoading() {
        loadingIndicator.visibility = View.VISIBLE
        optionalText.visibility = View.GONE
        photos.visibility = View.GONE
    }

    private fun hideLoading() {
        loadingIndicator.visibility = View.GONE
    }
}

