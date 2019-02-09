package book.fmy.org.adapters

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
import book.fmy.org.net.Doc
import book.fmy.org.utils.UnitUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_main_top_recommend_layout.view.*


class IntroduceCommendAdapter(dataList: MutableList<Doc>) :
    BaseQuickAdapter<Doc, BaseViewHolder>(R.layout.item_introduction_comment_layout, dataList), LifecycleObserver {

    override fun convert(helper: BaseViewHolder?, item: Doc?) {

    }


}
