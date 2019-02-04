package book.fmy.org.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import book.fmy.org.net.BookInfo
import book.fmy.org.net.NetHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeMainViewModel(app: Application) : BaseViewModel(app) {


    /**
     *    顶部轮播推荐小说
     */
    val topRecommendBooks: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
    /**
     *    中轮播推荐小说
     */
    val middleRecommendBooks: MutableLiveData<List<BookInfo>> by lazy {
        MutableLiveData<List<BookInfo>>()
    }

    override fun onCreat() {
        super.onCreat()
        Log.e("FMY", "onCreat")
    }

    override fun onResume() {
        super.onResume()


    }

    fun getTopRecommendBooks() {
        topRecommendBooks.value = listOf(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549190113345&di=260995f190b8f1522d98c5befbcdbe35&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F63a1a2768cfd82cb3215659c6c758bca61e9a4d7156b18-jZ1LwL_fw658",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549190262463&di=9b7261058c8bb0250e28562e06ece32a&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F017d8b5aa10040a80121246da9c3a2.jpg%401280w_1l_2o_100sh.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549190287303&di=f43340c6bea830ddf46f5434e990fd78&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019c9b5aa10043a801206d96b64d66.jpg%401280w_1l_2o_100sh.jpg"
        )
    }

    fun getMiddleRecommendBooks() {

        launch {
            val recommend = NetHelper.service.recommend().await()
            withContext(Dispatchers.Main){
                middleRecommendBooks.value = recommend.books
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }


}
