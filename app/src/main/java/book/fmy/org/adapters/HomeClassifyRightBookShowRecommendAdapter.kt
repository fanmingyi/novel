package book.fmy.org.adapters

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import book.fmy.org.R
import book.fmy.org.constant.Const
import book.fmy.org.net.BookInfo
import book.fmy.org.net.SubCategoriesFlat
import book.fmy.org.utils.UnitUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_main_top_recommend_layout.view.*


class HomeClassifyRightBookShowRecommendAdapter(@LayoutRes layoutResId: Int, var dataList: MutableList<BookInfo>) :
    BaseQuickAdapter<BookInfo, BaseViewHolder>(layoutResId, dataList) {

    var itemClick = 0

    override fun setOnItemClick(view: View, position: Int) {
        super.setOnItemClick(view, position)
    }


    override fun convert(helper: BaseViewHolder, book: BookInfo) {
        helper.setText(R.id.tv_book_name, book.title)

        val iv_book_cover = helper.getView<ImageView>(R.id.iv_book_cover)
        Glide.with(mContext).load(Const.Net.URL_RESOURCE + book.cover).apply(
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
