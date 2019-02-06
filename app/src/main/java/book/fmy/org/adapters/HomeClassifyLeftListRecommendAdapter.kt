package book.fmy.org.adapters

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import book.fmy.org.R
import book.fmy.org.net.BookInfo
import book.fmy.org.net.SubCategoriesFlat
import book.fmy.org.utils.UnitUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_main_top_recommend_layout.view.*


class HomeClassifyLeftListRecommendAdapter(@LayoutRes layoutResId: Int, var dataList: MutableList<SubCategoriesFlat>) :
    BaseQuickAdapter<SubCategoriesFlat, BaseViewHolder>(layoutResId, dataList) {

    var itemClick = 0

    override fun setOnItemClick(view: View, position: Int) {
        super.setOnItemClick(view, position)
    }


    override fun convert(helper: BaseViewHolder, item: SubCategoriesFlat) {
        helper.setText(R.id.tv_classify, item.mins)
        val clickColor = Color.WHITE
        val unClickColor = mContext.resources.getColor(R.color.bg_color)


        if (itemClick == helper.adapterPosition) {
            helper.setBackgroundColor(R.id.mcv_root, clickColor)
        } else {
            helper.setBackgroundColor(R.id.mcv_root, unClickColor)

        }

    }
}
