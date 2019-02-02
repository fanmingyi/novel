package book.fmy.org.ext

import android.app.Activity
import android.os.AsyncTask
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext


object AndroidComputationThreadPool : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(block)
    }
}

val Dispatchers.COMPUTAION: CoroutineDispatcher
    get() {
        return AndroidComputationThreadPool
    }