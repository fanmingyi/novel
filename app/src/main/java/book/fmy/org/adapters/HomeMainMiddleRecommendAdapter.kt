package book.fmy.org.adapters

import android.widget.ImageView
import androidx.annotation.LayoutRes
import book.fmy.org.R
import book.fmy.org.utils.UnitUtils
import book.fmy.org.constant.Const.Net.URL_RESOURCE
import book.fmy.org.net.BookInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class HomeMainMiddleRecommendAdapter(@LayoutRes layoutResId: Int, var dataList: MutableList<BookInfo>) :
    BaseQuickAdapter<BookInfo, BaseViewHolder>(layoutResId, dataList) {

    override fun convert(helper: BaseViewHolder, book: BookInfo) {

        helper.setText(R.id.tv_book_introduce, book.shortIntro)
        helper.setText(R.id.tv_book_name, book.title)
        helper.setText(R.id.tv_book_author, book.author)
        helper.setText(R.id.tv_type, book.majorCate)
        val serial = if (book.isSerial) "连载" else "完结";
        helper.setText(R.id.tv_isSerial, serial)
        val iv_book_cover = helper.getView<ImageView>(R.id.iv_book_cover)
        Glide.with(mContext).load(URL_RESOURCE + book.cover).apply(
            RequestOptions.bitmapTransform(
                RoundedCornersTransformation(
                    UnitUtils.dip2px(10f),
                    0,
                    RoundedCornersTransformation.CornerType.ALL
                )
            )
        ).into(iv_book_cover)
    }


}
