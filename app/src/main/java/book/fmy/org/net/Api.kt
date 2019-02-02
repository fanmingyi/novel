package book.fmy.org.net

import kotlinx.coroutines.Deferred
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    /**
     * 获取所有书籍分类
     */
    @GET("/categories")
    fun categories(
    ): Deferred<Categories>

    /**
     * 获取推荐
     */
    @GET("/recommend/{id}")
    fun recommend(
        @Path("id") recommendId: String = "53115e30173bfacb4904897e"
    ): Deferred<Recommend>


}