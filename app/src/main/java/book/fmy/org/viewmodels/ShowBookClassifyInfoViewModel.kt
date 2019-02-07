package book.fmy.org.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import book.fmy.org.net.BookInfo
import book.fmy.org.net.NetHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowBookClassifyInfoViewModel(app: Application) : BaseViewModel(app) {
    /**
     *  右侧分类信息
     */
    val categoriBooklist: MutableLiveData<MutableList<BookInfo>> by lazy {
        val mutableListOf = mutableListOf<BookInfo>()
        val mutableLiveData = MutableLiveData<MutableList<BookInfo>>()
        mutableLiveData.value = mutableListOf
        return@lazy mutableLiveData
    }

    fun getCategoryBookList(
        gender: String,
        major: String,
        minor: String,
        start: Int,
        limit: Int,
        type: String = "hot"
    ) {
        launch(Dispatchers.IO) {
            try {
                val queryCategoryInfo = NetHelper.service.categoryInfo(gender, major, minor, start, limit, type).await()
                val books = queryCategoryInfo.books
                categoriBooklist.value!!.addAll(books)
                categoriBooklist.postValue(categoriBooklist.value!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }


    }
}
