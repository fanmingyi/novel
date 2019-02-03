package book.fmy.org.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import book.fmy.org.net.BookInfo
import book.fmy.org.utils.UnitUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_layout.view.*


class HomeMiddleRecommendAdapter(@LayoutRes layoutResId: Int, dataList: List<BookInfo>) :
    BaseQuickAdapter<BookInfo, BaseViewHolder>(layoutResId, dataList) {

    override fun convert(helper: BaseViewHolder?, item: BookInfo?) {

    }


}
