package book.fmy.org.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import book.fmy.org.R
import book.fmy.org.adapters.HomeMiddleRecommendAdapter
import book.fmy.org.adapters.HomeTopRecommendAdapter
import book.fmy.org.animation.ZoomOutPageTransformer
import book.fmy.org.net.BookInfo
import book.fmy.org.viewmodels.HomeMainViewModel
import kotlinx.android.synthetic.main.fragment_home_main.*


class HomeMainFragment : BaseFragment<HomeMainViewModel>() {


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
        initTopRecommentView()
        initMiddleRecommentView()

    }

    private fun initMiddleRecommentView() {
        val homeMiddleRecommendAdapter =
            HomeMiddleRecommendAdapter(R.layout.item_main_middle_recommend_layout, mutableListOf())
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_recommend.layoutManager = linearLayoutManager
        rv_recommend.adapter = homeMiddleRecommendAdapter
//        rv_recommend.setHasFixedSize(true)
        rv_recommend.isNestedScrollingEnabled = false
        viewModel.getMiddleRecommendBooks()
        viewModel.middleRecommendBooks.observe(viewLifecycleOwner,
            Observer<List<BookInfo>> { data ->
                homeMiddleRecommendAdapter.dataList.clear()
                homeMiddleRecommendAdapter.dataList.addAll(data)
                homeMiddleRecommendAdapter.notifyDataSetChanged()

            })
    }

    private fun initTopRecommentView() {

        val zoomOutPageTransformer = ZoomOutPageTransformer()
        val myAdapter = HomeTopRecommendAdapter(arrayListOf<String>(), vp)
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
