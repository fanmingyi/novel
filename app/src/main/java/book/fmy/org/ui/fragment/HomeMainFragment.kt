package book.fmy.org.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import book.fmy.org.adapters.HomeTopRecommendAdapter
import book.fmy.org.animation.ZoomOutPageTransformer
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

    }

    private fun initTopRecommentView() {

        val zoomOutPageTransformer = ZoomOutPageTransformer()
        val myAdapter = HomeTopRecommendAdapter(arrayListOf<String>(), vp)
        vp.setPageTransformer(true, zoomOutPageTransformer)
        vp.adapter = myAdapter

        vp.offscreenPageLimit = 5

        viewModel.recommendBooks.observe(viewLifecycleOwner, Observer { data ->
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


        viewModel.getTopRecommendBooks()

    }


}
