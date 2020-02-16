package davydov.dmytro.coroutineshomework.coroutinesBase

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel


class FragmentScopeManager : LifecycleObserver {
    private var job: Job? = null

    val fragmentScope: CoroutineScope
        get() {
            checkNotNull(job) {  "You are trying to get scope of fragment outside of view lifecycle" }

            return CoroutineScope(job!!.plus(Dispatchers.Main))
        }

    fun createScopeWith(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(this)

        job = Job()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroyScope() {
        fragmentScope.cancel().also { job = null }
    }
}