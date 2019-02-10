package book.fmy.org.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import book.fmy.org.net.BookInfo
import book.fmy.org.net.Doc
import book.fmy.org.net.NetHelper
import book.fmy.org.net.QueryComment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookIntroductionViewModel(app: Application) : BaseViewModel(app) {


    val queryComment: MutableLiveData<QueryComment> by lazy {
        MutableLiveData<QueryComment>()
    }

    val commentList: MutableLiveData<MutableList<Doc>> by lazy {
        val emptyList = mutableListOf<Doc>()
        val mutableLiveData = MutableLiveData<MutableList<Doc>>()
        mutableLiveData.value = emptyList
        return@lazy mutableLiveData
    }

    val bookInfo: MutableLiveData<BookInfo> by lazy {
        MutableLiveData<BookInfo>()
    }

    fun getComment() {

        launch {
            val bookDetail: QueryComment
            try {
                bookDetail = NetHelper.service.queryBookComment(bookInfo.value!!._id).await()
                queryComment.postValue(bookDetail)
                withContext(Dispatchers.Main) {
                    commentList.value!!.clear()
                    commentList.value?.addAll(bookDetail.docs)
                    commentList.value = commentList.value

                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }


        }
    }

}
