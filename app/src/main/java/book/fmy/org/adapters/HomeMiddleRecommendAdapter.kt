package book.fmy.org.adapters

import android.widget.ImageView
import androidx.annotation.LayoutRes
import book.fmy.org.R
import book.fmy.org.constant.Const.Net.URL_RESOURCE
import book.fmy.org.net.BookInfo
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class HomeMiddleRecommendAdapter(@LayoutRes layoutResId: Int, var dataList: MutableList<BookInfo>) :
    BaseQuickAdapter<BookInfo, BaseViewHolder>(layoutResId, dataList) {

    override fun convert(helper: BaseViewHolder, book: BookInfo) {
        helper.setText(R.id.tv_introduce, book.shortIntro)
        helper.setText(R.id.tv_book_name, book.title)
        val iv_book_cover = helper.getView<ImageView>(R.id.iv_book_cover)
        Glide.with(mContext).load(URL_RESOURCE + book.cover).into(iv_book_cover)
    }


}
