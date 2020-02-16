package davydov.dmytro.coroutineshomework.searchPhotosFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import davydov.dmytro.coroutineshomework.R
import davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos.PhotoSearcher
import davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos.SearchPhotosFragment

class SearchPhotosFlowFragment : Fragment()  {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState ?: childFragmentManager
            .beginTransaction()
            .add(R.id.flow, SearchPhotosFragment())
            .commit()
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        (childFragment as? SearchPhotosFragment)?.photoSearcher = PhotoSearcher()
    }
}