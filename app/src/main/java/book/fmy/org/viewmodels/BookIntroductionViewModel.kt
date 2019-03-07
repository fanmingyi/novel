package book.fmy.org.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import book.fmy.org.adapters.ExpandableCatalogue
import book.fmy.org.net.*
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
    val chapters: MutableLiveData<MutableList<ExpandableCatalogue>> by lazy {
        val mutableLiveData = MutableLiveData<MutableList<ExpandableCatalogue>>()
        mutableLiveData.value = mutableListOf()
        mutableLiveData
    }


    fun getchapter() {
        launch {
            //            val bookCategories = NetHelper.service.bookChapters(bookInfo.value!!._id).await()
//            chapters.value!!.clear()
//            chapters.value!!.addAll(bookCategories.chapters)
//            chapters.postValue(chapters.value)

            val expandableCatalogue = ExpandableCatalogue(1, "第一章")
            val expandableCatalogue2 = ExpandableCatalogue(2, "第二章")

            expandableCatalogue.addSubItem(Chapter(0, "1.0 乡村"))
            expandableCatalogue.addSubItem(Chapter(0, "1.1 魔法少年"))
            expandableCatalogue.addSubItem(Chapter(0, "1.2 李逍遥"))
            expandableCatalogue.addSubItem(Chapter(0, "1.3 逍遥哥哥"))
            expandableCatalogue.addSubItem(Chapter(0, "1.4 情窦初开"))


            expandableCatalogue2.addSubItem(Chapter(0, "2.0 乡村"))
            expandableCatalogue2.addSubItem(Chapter(0, "2.1 魔法少年"))
            expandableCatalogue2.addSubItem(Chapter(0, "2.2 李逍遥"))
            expandableCatalogue2.addSubItem(Chapter(0, "2.3 逍遥哥哥"))
            expandableCatalogue2.addSubItem(Chapter(0, "3.4 情窦初开"))

            chapters.value!!.add(expandableCatalogue)
            chapters.value!!.add(expandableCatalogue2)
            chapters.value!!.add(expandableCatalogue2)
            chapters.value!!.add(expandableCatalogue2)
            chapters.value!!.add(expandableCatalogue2)
            chapters.value!!.add(expandableCatalogue2)
            chapters.value!!.add(expandableCatalogue2)
            chapters.value!!.add(expandableCatalogue2)
            chapters.value!!.add(expandableCatalogue2)
            chapters.value!!.add(expandableCatalogue2)
            chapters.value!!.add(expandableCatalogue2)

            chapters.postValue(chapters.value)
        }
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
