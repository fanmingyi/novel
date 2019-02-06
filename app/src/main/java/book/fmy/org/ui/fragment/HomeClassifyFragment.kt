package book.fmy.org.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import book.fmy.org.R
import book.fmy.org.adapters.HomeClassifyLeftListRecommendAdapter
import book.fmy.org.adapters.HomeClassifyRightBookShowRecommendAdapter
import book.fmy.org.net.SubCategoriesFlat
import book.fmy.org.viewmodels.HomeClassifyViewModel
import kotlinx.android.synthetic.main.fragment_home_classify.*

class HomeClassifyFragment : BaseFragment<HomeClassifyViewModel>() {

    override fun getViewMode(): HomeClassifyViewModel {
        return ViewModelProviders.of(this).get(HomeClassifyViewModel::class.java)
    }

    companion object {
        fun newInstance() = HomeClassifyFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_classify, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    private fun initView() {
        initLeftListView()
        initRightShowView()

    }

    private fun initRightShowView() {
        rv_show_book.layoutManager = rightRvLinearGridManager
        viewModel.subCategoriesFlatList
        homeClassifyRightBookShowRecommendAdapter =
            HomeClassifyRightBookShowRecommendAdapter(
                R.layout.item_classify_right_layout,
                viewModel.categoriBooklist.value!!
            )


        homeClassifyRightBookShowRecommendAdapter.bindToRecyclerView(rv_show_book)
        /**
         * TODO
         */
//        rv_show_book.adapter = homeClassifyRightBookShowRecommendAdapter

        homeClassifyRightBookShowRecommendAdapter.setEnableLoadMore(true)


        homeClassifyRightBookShowRecommendAdapter.setOnLoadMoreListener({

            Log.e("FMY","setOnLoadMoreListener")

        }, rv_show_book)
        viewModel.categoriBooklist.observe(viewLifecycleOwner, Observer {

            homeClassifyRightBookShowRecommendAdapter.notifyDataSetChanged()

        })

//        val viewList = leftAllClassifyRvLinearLayoutManager.getChildAt(0)!!
//
//        viewList.performClick()


    }

    private val leftAllClassifyRvLinearLayoutManager by lazy {
        LinearLayoutManager(context, RecyclerView.VERTICAL, false);

    }
    private val rightRvLinearGridManager by lazy {
        GridLayoutManager(context, 2, RecyclerView.VERTICAL, false);

    }
    lateinit var homeClassifyLeftListRecommendAdapter: HomeClassifyLeftListRecommendAdapter
    lateinit var homeClassifyRightBookShowRecommendAdapter: HomeClassifyRightBookShowRecommendAdapter

    private fun initLeftListView() {
        rv_book_classify.layoutManager = leftAllClassifyRvLinearLayoutManager as RecyclerView.LayoutManager?

        homeClassifyLeftListRecommendAdapter = HomeClassifyLeftListRecommendAdapter(
            R.layout.item_classify_left_layout,
            viewModel.subCategoriesFlatList.value!!
        )

        viewModel.getSubCategoriesFlatList()
        rv_book_classify.adapter = homeClassifyLeftListRecommendAdapter

        viewModel.subCategoriesFlatList.observe(viewLifecycleOwner,
            Observer<MutableList<SubCategoriesFlat>> {
                homeClassifyLeftListRecommendAdapter.data.clear()
                homeClassifyLeftListRecommendAdapter.dataList = it
                homeClassifyLeftListRecommendAdapter.data.addAll(it)
                homeClassifyLeftListRecommendAdapter.notifyDataSetChanged()

            })

        homeClassifyLeftListRecommendAdapter.setOnItemClickListener { adapter, view, position ->
            homeClassifyLeftListRecommendAdapter.notifyItemChanged(homeClassifyLeftListRecommendAdapter.itemClick)
            homeClassifyLeftListRecommendAdapter.itemClick = position
            view.setBackgroundColor(Color.WHITE)
            val subCategoriesFlat = homeClassifyLeftListRecommendAdapter.dataList[position]
            viewModel.getCategoriBooklist(
                subCategoriesFlat.gender,
                subCategoriesFlat.major,
                subCategoriesFlat.mins,
                0,
                20
            )
        }


    }

}
