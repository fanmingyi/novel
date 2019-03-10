package book.fmy.org.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import book.fmy.org.R
import book.fmy.org.adapters.HomeMainMiddleRecommendAdapter
import book.fmy.org.adapters.HomeMainTopRecommendAdapter
import book.fmy.org.animation.ZoomOutPageTransformer
import book.fmy.org.constant.Const
import book.fmy.org.net.BookInfo
import book.fmy.org.ui.activity.BookIntroductionActivity
import book.fmy.org.viewmodels.HomeMainViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.fragment_home_main.*
import kotlinx.android.synthetic.main.item_main_middle_recommend_layout.view.*


class HomeMainFragment : BaseFragment<HomeMainViewModel>() {


    override fun initImmersionBar() {
        ImmersionBar.with(this).reset().fitsSystemWindows(true).navigationBarColor(R.color.colorPrimary)
            .statusBarColor(R.color.colorPrimary)
            .init();

    }


    override fun getViewMode(): HomeMainViewModel {

        return ViewModelProviders.of(this).get(HomeMainViewModel::class.java)
    }

    companion object {
        fun newInstance() = HomeMainFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(book.fmy.org.R.layout.fragment_home_main, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()


    }

    private fun initView() {
        initTopCommentView()
        initMiddleCommentView()

    }

    private fun initMiddleCommentView() {
        val homeMiddleRecommendAdapter =
            HomeMainMiddleRecommendAdapter(R.layout.item_main_middle_recommend_layout, mutableListOf())
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_recommend.layoutManager = linearLayoutManager as RecyclerView.LayoutManager?
        rv_recommend.adapter = homeMiddleRecommendAdapter
        rv_recommend.setHasFixedSize(true)
        rv_recommend.isNestedScrollingEnabled = false
        viewModel.getMiddleRecommendBooks()
        viewModel.middleRecommendBooks.observe(viewLifecycleOwner,
            Observer<List<BookInfo>> { data ->
                homeMiddleRecommendAdapter.dataList.clear()
                homeMiddleRecommendAdapter.dataList.addAll(data)
                homeMiddleRecommendAdapter.notifyDataSetChanged()

            })

        homeMiddleRecommendAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->

                val intent = Intent(this@HomeMainFragment.context, BookIntroductionActivity::class.java)

                val data = homeMiddleRecommendAdapter.dataList[position]

                intent.putExtra(Const.IntentData.BOOK_INFO_OBJ_INTENT_KEY, data)

                val pairCover = androidx.core.util.Pair<View, String>(
                    view.tv_book_name,
                    ViewCompat.getTransitionName(view.tv_book_name)
                )
                val pairCover2 = androidx.core.util.Pair<View, String>(
                    view.iv_book_cover,
                    ViewCompat.getTransitionName(view.iv_book_cover)
                )
                val pairCover3 = androidx.core.util.Pair<View, String>(
                    view.tv_book_author,
                    ViewCompat.getTransitionName(view.tv_book_author)
                )
                val makeSceneTransitionAnimation =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, pairCover2)
//                context!!.startActivity(intent)

                ActivityCompat.startActivity(context!!, intent, makeSceneTransitionAnimation.toBundle())

            }

    }

    private fun initTopCommentView() {

        val zoomOutPageTransformer = ZoomOutPageTransformer()
        val myAdapter = HomeMainTopRecommendAdapter(arrayListOf<String>(), vp)
        vp.setPageTransformer(true, zoomOutPageTransformer)
        vp.adapter = myAdapter

        vp.offscreenPageLimit = 5

        viewModel.topRecommendBooks.observe(viewLifecycleOwner, Observer { data ->
            myAdapter.mData = List(5) {
                if (it == 0) {
                    return@List data[2]
                } else if (it == 4) {
                    return@List data[0]
                }
                return@List data[it - 1]
            }

            vp.adapter = myAdapter
            vp.setCurrentItem(1, false)
        })

        cl_vp_father.setOnTouchListener { v, e ->
            return@setOnTouchListener vp.onTouchEvent(e)
        }

        lifecycle.addObserver(myAdapter)
        viewModel.getTopRecommendBooks()


    }


    override fun onDestroyView() {
        super.onDestroyView()


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
