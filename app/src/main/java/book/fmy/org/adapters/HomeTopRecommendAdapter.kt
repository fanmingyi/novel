package book.fmy.org.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import book.fmy.org.utils.UnitUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_main_top_recommend_layout.view.*


class HomeTopRecommendAdapter(var mData: List<String>, val vp: ViewPager) : PagerAdapter(),
    ViewPager.OnPageChangeListener, LifecycleObserver {


    val handler = Handler(Looper.getMainLooper())


    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
    }

    override fun onPageSelected(position: Int) {
        if (position == 0) {
            vp.postDelayed({
                vp.setCurrentItem(mData!!.size - 2, false)
            }, 3)
        } else if (position == mData!!.size - 1) {
            vp.postDelayed({
                vp.setCurrentItem(1, false)
            }, 3)
        }
    }


    init {
        vp.addOnPageChangeListener(this)
        autoScrollRecommend()
    }

    private fun autoScrollRecommend() {


        handler.postDelayed({
            autoScrollRecommend()
            val currentItem = vp.currentItem
            if (vp.adapter!!.count != 0) {
                val i = (currentItem + 1) % vp.adapter!!.count
                vp.setCurrentItem(i, true)
            }
        }, 3000)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflate =
            LayoutInflater.from(container.context).inflate(book.fmy.org.R.layout.item_main_top_recommend_layout, container, false)
        Glide.with(container.context).load(mData[position])
            .apply(
                RequestOptions.bitmapTransform(
                    RoundedCornersTransformation(
                        UnitUtils.dp2px(10),
                        0,
                        RoundedCornersTransformation.CornerType.ALL
                    )
                )
            )
            .into(inflate.iv)

        container.addView(inflate)

        return inflate


    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }


    override fun getCount(): Int {
        return if (mData == null || mData!!.size < 3) 0 else mData!!.size
    }

}
