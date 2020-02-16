package davydov.dmytro.coroutineshomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import davydov.dmytro.coroutineshomework.searchPhotosFlow.SearchPhotosFlowFragment

class PhotosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photos_activity)

        savedInstanceState ?: supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, SearchPhotosFlowFragment())
            .commit()
    }
}
