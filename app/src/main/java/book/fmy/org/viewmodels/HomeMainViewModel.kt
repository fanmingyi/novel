package book.fmy.org.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import book.fmy.org.net.BookInfo
import book.fmy.org.net.NetHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeMainViewModel(app: Application) : BaseViewModel(app) {


    // 顶部轮播推荐小说
    val recommendBooks: MutableLiveData<List<BookInfo>> by lazy {
        MutableLiveData<List<BookInfo>>()
    }

    override fun onCreat() {
        super.onCreat()
        Log.e("FMY", "onCreat")
    }

    override fun onResume() {
        super.onResume()

        launch(Dispatchers.IO) {

            val recommend = NetHelper.service.recommend().await()
        }
    }


    override fun onCleared() {
        super.onCleared()
    }


}
