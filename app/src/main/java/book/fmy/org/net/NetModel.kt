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
    val author: String,
    val contentType: String,
    val cover: String,
    val isSerial: Boolean,
    val lastChapter: String,
    val latelyFollower: Int,
    val majorCate: String,
    val minorCate: String,
    val otherReadRatio: Double,
    val retentionRatio: Double,
    val shortIntro: String,
    val site: String,
    val title: String
)

data class Recommend(@SerializedName("ok") val ok: Boolean, @SerializedName("books") val books: List<BookInfo>)