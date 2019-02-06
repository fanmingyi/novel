package book.fmy.org.net

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
        @Path("id") recommendId: String = "59fa9e2e04187ced541d5ff7"
    ): Deferred<Recommend>

    /**
     * 获取带有二级目录的书籍分类
     */
    @GET("/sub-categories")
    fun subategories(
    ): Deferred<SubCategoriesList>


    /**
     * 获取带有二级目录的书籍分类
     */
    @GET("/category-info")
    fun categoryInfo(
      @Query("gender")gender:String,
      @Query("major")major:String,
      @Query("minor")minor:String,
      @Query("start")start:Int,
      @Query("limit")limit:Int,
      @Query("type")type:String="hot"
    ): Deferred<QueryCategoryInfo>


}