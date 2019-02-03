package book.fmy.org.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import book.fmy.org.utils.UnitUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_layout.view.*


class HomeTopRecommendAdapter(var mData: List<String>, val vp: ViewPager) : PagerAdapter(),
    ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
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
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflate =
            LayoutInflater.from(container.context).inflate(book.fmy.org.R.layout.item_layout, container, false)
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
