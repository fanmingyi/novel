package book.fmy.org.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import book.fmy.org.R
import book.fmy.org.constant.Const
import book.fmy.org.net.BookInfo
import book.fmy.org.viewmodels.HomeMainViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_main_fragment.*
import kotlinx.android.synthetic.main.item_layout.view.*


//设置切换动画
class ZoomOutPageTransformer() : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {

        if (position <= 1) {

            val scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE)

            page.scaleX = scaleFactor

            if (position > 0) {
                page.translationX = -scaleFactor * 2
            } else if (position < 0) {
                page.translationX = scaleFactor * 2
            }
            page.scaleY = scaleFactor
        } else {

            page.scaleX = MIN_SCALE
            page.scaleY = MIN_SCALE
        }
    }

    companion object {
        //自由控制缩放比例
        private val MAX_SCALE = 1f
        private val MIN_SCALE = 0.85f//0.85f
    }

}

class HomeMainFragment : Fragment() {

    companion object {
        fun newInstance() = HomeMainFragment()
    }

    private lateinit var viewModel: HomeMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_main_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeMainViewModel::class.java)

        val zoomOutPageTransformer = ZoomOutPageTransformer();

        vp.setPageTransformer(false, zoomOutPageTransformer)

        vp.offscreenPageLimit = 3

        val myAdapter = MyAdapter(viewModel.recommendBooks.value)

        vp.adapter = myAdapter

        lifecycle.addObserver(viewModel)

        vp.setCurrentItem(Int.MAX_VALUE / 2, true)

        viewModel.recommendBooks.observe(viewLifecycleOwner, Observer { data ->
            myAdapter.mData = data
            myAdapter.notifyDataSetChanged()
        })


    }

    inner class MyAdapter(var mData: List<BookInfo>?) : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflateView = LayoutInflater.from(context).inflate(R.layout.item_layout, container, false)


            val book = mData!!.get(position)

            Glide.with(context!!).load(Const.Net.URL_RESOURCE + book.cover).into(inflateView.iv)

            container.addView(inflateView)
            return inflateView
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

}
