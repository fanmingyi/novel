package book.fmy.org.viewmodels

import android.app.Application
import android.text.TextUtils
import android.util.ArrayMap
import androidx.core.util.Pair
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


    //男生
    private val maleSubCategoriesList = ArrayMap<String, MutableList<BookInfo>>()
    //女生
    private val femaleSubCategoriesList = ArrayMap<String, MutableList<BookInfo>>()

    fun getSubCategoriesFlatList() {
        launch(Dispatchers.IO) {
            try {
                val data = NetHelper.service.subategories().await()

                var list = mutableListOf<SubCategoriesFlat>()

                val maleList = data.male.map { subCategories ->
                    val mutableList = mutableListOf<SubCategoriesFlat>()
                    subCategories.mins.forEach { minName ->
                        val subCategoriesFlat =
                            SubCategoriesFlat(subCategories.major, minName, Const.Net.gender_type_male)
                        mutableList.add(subCategoriesFlat)

                    }
                    return@map mutableList
                }.flatten()

                val femaleList = data.female.map { subCategories ->
                    val mutableList = mutableListOf<SubCategoriesFlat>()
                    subCategories.mins.forEach { minName ->
                        val subCategoriesFlat =
                            SubCategoriesFlat(subCategories.major, minName, Const.Net.gender_type_female)
                        mutableList.add(subCategoriesFlat)
                    }

                    return@map mutableList
                }.flatten()

                list.addAll(maleList)
                list.addAll(femaleList)
                subCategoriesFlatList.postValue(list)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}
