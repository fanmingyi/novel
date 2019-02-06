package book.fmy.org.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import book.fmy.org.constant.Const
import book.fmy.org.net.BookInfo
import book.fmy.org.net.NetHelper
import book.fmy.org.net.SubCategoriesFlat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.Query

class HomeClassifyViewModel(app: Application) : BaseViewModel(app) {


    /**
     *  左侧分类信息
     */
    val subCategoriesFlatList: MutableLiveData<MutableList<SubCategoriesFlat>> by lazy {
        val mutableListOf = mutableListOf<SubCategoriesFlat>()
        val mutableLiveData = MutableLiveData<MutableList<SubCategoriesFlat>>()
        mutableLiveData.value = mutableListOf
        return@lazy mutableLiveData
    }

    /**
     *  右侧分类信息
     */
    val categoriBooklist: MutableLiveData<MutableList<BookInfo>> by lazy {
        val mutableListOf = mutableListOf<BookInfo>()
        val mutableLiveData = MutableLiveData<MutableList<BookInfo>>()
        mutableLiveData.value = mutableListOf
        return@lazy mutableLiveData
    }

    fun getCategoriBooklist(
        gender: String,
        major: String,
        minor: String,
        start: Int,
        limit: Int,
        type: String = "hot"
    ) {

        launch(Dispatchers.IO) {
            val queryCategoryInfo = NetHelper.service.categoryInfo(gender, major, minor, start, limit, type).await()

            val books = queryCategoryInfo.books

            categoriBooklist.value!!.clear()
            categoriBooklist.value!!.addAll(books)
            categoriBooklist.postValue(categoriBooklist.value!!)

        }


    }

    fun getSubCategoriesFlatList() {
        launch(Dispatchers.IO) {
            val data = NetHelper.service.subategories().await()
            var list = mutableListOf<SubCategoriesFlat>()

            val maleList = data.male.flatMap {
                it.mins
            }.map {
                SubCategoriesFlat(data.male[0].major, it,Const.Net.gender_type_male)
            }

            val femaleList = data.male.flatMap {
                it.mins
            }.map {
                SubCategoriesFlat(data.female[0].major, it,Const.Net.gender_type_female)
            }

            list.addAll(maleList)
            list.addAll(femaleList)
            subCategoriesFlatList.postValue(list)

        }
    }

}
