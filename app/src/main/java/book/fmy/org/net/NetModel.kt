package book.fmy.org.net

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


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
@Parcelize
data class BookInfo(
    val _id: String = "",
    val allowMonthly: Boolean = false,
    val isSerial: Boolean = false,
    val author: String = "",
    val banned: Int = -1,
    val contentType: String = "",
    val cover: String = "",
    val lastChapter: String = "",
    val latelyFollower: Int = -1,
    val majorCate: String = "",
    val minorCate: String = "",
    val retentionRatio: Double = (-1).toDouble(),
    val shortIntro: String = "",
    val site: String = "",
    val sizetype: Int = -1,
    val superscript: String = "",
    val title: String = ""
) : Parcelable {
    var tags: MutableList<String>? = null
        get() {
            return field ?: mutableListOf<String>()
        }


}

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


/**
 * 书籍详情
 */
data class Bookdetail(
    val _gg: Boolean,
    val _id: String,
    val _le: Boolean,
    val advertRead: Boolean,
    val allowBeanVoucher: Boolean,
    val allowFree: Boolean,
    val allowMonthly: Boolean,
    val allowVoucher: Boolean,
    val author: String,
    val authorDesc: String,
    val banned: Int,
    val buytype: Int,
    val cat: String,
    val chaptersCount: Int,
    val contentType: String,
    val cover: String,
    val creater: String,
    val currency: Int,
    val donate: Boolean,
    val followerCount: Int,
    val gender: List<String>,
    val hasCopyright: Boolean,
    val hasCp: Boolean,
    val isAllowNetSearch: Boolean,
    val isForbidForFreeApp: Boolean,
    val isSerial: Boolean,
    val lastChapter: String,
    val latelyFollower: Int,
    val limit: Boolean,
    val longIntro: String,
    val majorCate: String,
    val majorCateV2: String,
    val minorCate: String,
    val minorCateV2: String,
    val originalAuthor: String,
    val postCount: Int,
    val rating: Rating,
    val retentionRatio: String,
    val serializeWordCount: Int,
    val sizetype: Int,
    val superscript: String,
    val tags: List<String>,
    val title: String,
    val updated: String,
    val wordCount: Int
)

data class Rating(
    val count: Int,
    val isEffect: Boolean,
    val score: Double
)


data class QueryComment(
    @SerializedName("docs")
    var docs: List<Doc> = listOf(),
    @SerializedName("ok")
    var ok: Boolean = false,
    @SerializedName("today")
    var today: Int = 0
)


data class Doc(
    @SerializedName("_id")
    var id: String = "",
    @SerializedName("author")
    var author: Author = Author(),
    @SerializedName("block")
    var block: String = "",
    @SerializedName("book")
    var book: BookInfo =BookInfo(),
    @SerializedName("content")
    var content: String = "",
    @SerializedName("created")
    var created: String = "",
    @SerializedName("likeCount")
    var likeCount: Int = 0,
    @SerializedName("priority")
    var priority: Float = 0f,
    @SerializedName("rating")
    var rating: Int = 0,
    @SerializedName("state")
    var state: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("updated")
    var updated: String = ""
)

data class Author(
    @SerializedName("_id")
    var id: String = "",
    @SerializedName("activityAvatar")
    var activityAvatar: String = "",
    @SerializedName("avatar")
    var avatar: String = "",
    @SerializedName("gender")
    var gender: String = "",
    @SerializedName("lv")
    var lv: Int = 0,
    @SerializedName("nickname")
    var nickname: String = "",
    @SerializedName("type")
    var type: String = ""
)