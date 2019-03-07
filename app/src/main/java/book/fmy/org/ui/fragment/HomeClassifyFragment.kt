package book.fmy.org.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.ArrayMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import book.fmy.org.R
import book.fmy.org.adapters.HomeClassifyLeftListRecommendAdapter
import book.fmy.org.net.SubCategoriesFlat
import book.fmy.org.viewmodels.HomeClassifyViewModel
import kotlinx.android.synthetic.main.fragment_home_classify.*

class HomeClassifyFragment : BaseFragment<HomeClassifyViewModel>() {
    override fun initImmersionBar() {

    }

    private val leftAllClassifyRvLinearLayoutManager by lazy {
        LinearLayoutManager(context, RecyclerView.VERTICAL, false);
    }

    private lateinit var homeClassifyLeftListRecommendAdapter: HomeClassifyLeftListRecommendAdapter

    private val category2fragment: ArrayMap<SubCategoriesFlat, ShowBookClassifyInfoFragment> by lazy {
        return@lazy ArrayMap<SubCategoriesFlat, ShowBookClassifyInfoFragment>()
    }

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

    }

    var showFragment: Fragment? = null


    private fun initLeftListView() {

        rv_book_classify.setHasFixedSize(true)
        rv_book_classify.layoutManager = leftAllClassifyRvLinearLayoutManager

        homeClassifyLeftListRecommendAdapter = HomeClassifyLeftListRecommendAdapter(
            R.layout.item_classify_left_layout,
            viewModel.subCategoriesFlatList.value!!
        )


        viewModel.getSubCategoriesFlatList()

        rv_book_classify.adapter = homeClassifyLeftListRecommendAdapter

        /**
         * 当数据源改变
         */
        viewModel.subCategoriesFlatList.observe(viewLifecycleOwner,
            Observer<MutableList<SubCategoriesFlat>> {
                homeClassifyLeftListRecommendAdapter.data.clear()
                homeClassifyLeftListRecommendAdapter.dataList = it
                homeClassifyLeftListRecommendAdapter.data.addAll(it)
                homeClassifyLeftListRecommendAdapter.notifyDataSetChanged()

                if (it.size > 0) {
                    fragmentManager?.beginTransaction()?.apply {
                        if (showFragment == null) {
                            val subCategoriesFlat = it[0]

                            var fragment = category2fragment[subCategoriesFlat]

                            if (fragment == null) {
                                fragment = ShowBookClassifyInfoFragment.newInstance(
                                    subCategoriesFlat.gender,
                                    subCategoriesFlat.major,
                                    subCategoriesFlat.mins
                                )
                                category2fragment[subCategoriesFlat] = fragment
                            }

                            if (!fragment.isAdded) {
                                this.add(R.id.fl_show_book, fragment)
                            }
                            showFragment = fragment
                            commit()
                        }

                    }


                }

            })

        /**
         * 左侧点击事件切换界面
         */
        homeClassifyLeftListRecommendAdapter.setOnItemClickListener { adapter, view, position ->

            if (position == homeClassifyLeftListRecommendAdapter.itemClick) {
                return@setOnItemClickListener
            }
            homeClassifyLeftListRecommendAdapter.notifyItemChanged(homeClassifyLeftListRecommendAdapter.itemClick)

            homeClassifyLeftListRecommendAdapter.itemClick = position

            view.setBackgroundColor(Color.WHITE)

            val subCategoriesFlat = homeClassifyLeftListRecommendAdapter.dataList[position]

            fragmentManager?.apply {
                this.beginTransaction().apply {
                    var fragment = category2fragment[subCategoriesFlat]

                    if (fragment == null) {
                        fragment = ShowBookClassifyInfoFragment.newInstance(
                            subCategoriesFlat.gender,
                            subCategoriesFlat.major,
                            subCategoriesFlat.mins
                        )
                        category2fragment[subCategoriesFlat] = fragment
                    }


                    if (!fragment!!.isAdded) {
                        add(R.id.fl_show_book, fragment!!)
                    }

                    show(fragment!!)

                    if (showFragment != null) {
                        hide(showFragment!!)
                    }

                    showFragment = fragment


                    commit()
                }

            }

        }


    }

}
