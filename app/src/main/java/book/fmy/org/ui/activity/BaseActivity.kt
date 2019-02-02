package book.fmy.org.ui.activity

import androidx.appcompat.app.AppCompatActivity
import book.fmy.org.ext.COMPUTAION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseActivity : AppCompatActivity(), CoroutineScope {

    private val job: Job by lazy {
        Job()
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()

    }
}
