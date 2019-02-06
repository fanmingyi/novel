package book.fmy.org.net

import com.google.gson.annotations.SerializedName


/**
 * 书籍分类某个信息
 */
data class BookCategories(
    @SerializedName("name")
    val name: String,
    @SerializedName("bookCount")
    val bookCount: Int,
    @SerializedName("monthlyCount")
    val monthlyCount: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("bookCover")
    val bookCover: List<String>
)

/**
 * 所有书籍目录
 */
data class Categories(
    @SerializedName("male")
    val male: List<BookCategories>,
    @SerializedName("female")
    val female: List<BookCategories>,
    @SerializedName("picture")
    val picture: List<BookCategories>,
    @SerializedName("press")
    val press: List<BookCategories>,
    @SerializedName("ok")
    val ok: Boolean
)


/**
 * 某个书籍的信息
 */
data class BookInfo(
    val _id: String,
    val allowMonthly: Boolean,
    val isSerial: Boolean,
    val author: String,
    val banned: Int,
    val contentType: String,
    val cover: String,
    val lastChapter: String,
    val latelyFollower: Int,
    val majorCate: String,
    val minorCate: String,
    val retentionRatio: Double,
    val shortIntro: String,
    val site: String,
    val sizetype: Int,
    val superscript: String,
    val tags: List<String>,
    val title: String
)

/**
 * 带有二级目录书籍
 */

data class SubCategoriesList(
    @SerializedName("male")
    val male: List<SubCategories>,
    @SerializedName("female")
    val female: List<SubCategories>,
    @SerializedName("picture")
    val picture: List<SubCategories>,
    @SerializedName("press")
    val press: List<SubCategories>,
    @SerializedName("ok")
    val ok: Boolean
)

/**
 * 带有二级目录书籍json
 */

data class SubCategories(
    @SerializedName("major")
    val major: String,
    @SerializedName("mins")
    val mins: List<String>
)


/**
 * 带有二级目录书籍和对应的一集目录名称
 */
data class SubCategoriesFlat(
    val major: String,
    val mins: String,
    val gender: String
)

/**
 *查询分类结果
 */
data class QueryCategoryInfo(
    val total: Int,
    val books: MutableList<BookInfo>,
    val ok: Boolean
)

data class Recommend(@SerializedName("ok") val ok: Boolean, @SerializedName("books") val books: List<BookInfo>)