package book.fmy.org.viewmodels

import android.app.Application
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import book.fmy.org.ext.COMPUTAION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(app: Application) : AndroidViewModel(app), LifecycleObserver, CoroutineScope {

    val job: Job by lazy {
        Job()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.COMPUTAION + job

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreat() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    open fun onAny() {

    }

}
