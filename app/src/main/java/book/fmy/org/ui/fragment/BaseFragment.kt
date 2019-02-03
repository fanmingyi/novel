package book.fmy.org.ui.fragment

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import book.fmy.org.viewmodels.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<T : BaseViewModel> : Fragment(), CoroutineScope {
    private val job: Job by lazy {
        Job()
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()

    }

    abstract fun getViewMode(): T

    lateinit var viewModel: T

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewMode()

        lifecycle.addObserver(viewModel)

    }
}
