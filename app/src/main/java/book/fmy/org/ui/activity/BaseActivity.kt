package book.fmy.org.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import book.fmy.org.ext.COMPUTAION
import com.gyf.barlibrary.ImmersionBar
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

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ImmersionBar.with(this).init();

    }
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        ImmersionBar.with(this).destroy();

    }
}
