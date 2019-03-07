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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import book.fmy.org.R
import book.fmy.org.adapters.HomeClassifyRightBookShowRecommendAdapter
import book.fmy.org.constant.Const
import book.fmy.org.ui.activity.BookIntroductionActivity
import book.fmy.org.viewmodels.ShowBookClassifyInfoViewModel
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.fragment_show_book_classify_info.*
import kotlinx.android.synthetic.main.item_main_middle_recommend_layout.view.*

class ShowBookClassifyInfoFragment constructor() : BaseFragment<ShowBookClassifyInfoViewModel>() {


    lateinit var gender: String

    override fun initImmersionBar() {


    }

    lateinit var major: String

    lateinit var minor: String

    private var startIndex = 0

    private val limit = 20


    override fun getViewMode(): ShowBookClassifyInfoViewModel {


        return ViewModelProviders.of(this).get(ShowBookClassifyInfoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gender = it.getString(GENDER_PARAM)
            major = it.getString(MAJOR_PARAM)
            minor = it.getString(MINOR_PARAM)
        }

    }

    companion object {

        const val GENDER_PARAM: String = "GENDER_PARAM"
        const val MAJOR_PARAM: String = "MAJOR_PARAM"
        const val MINOR_PARAM: String = "MINOR_PARAM";
        fun newInstance(
            gender: String,
            major: String,
            minor: String
        ): ShowBookClassifyInfoFragment {

            return ShowBookClassifyInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(GENDER_PARAM, gender)
                    putString(MAJOR_PARAM, major)
                    putString(MINOR_PARAM, minor)
                }
            }
        }
    }


    private val rightRvLinearGridManager by lazy {
        GridLayoutManager(context, 2, RecyclerView.VERTICAL, false);

    }
    lateinit var homeClassifyRightBookShowRecommendAdapter: HomeClassifyRightBookShowRecommendAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_book_classify_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListView()
    }

    private fun initListView() {

        rv_show_book.layoutManager = rightRvLinearGridManager

        homeClassifyRightBookShowRecommendAdapter =
            HomeClassifyRightBookShowRecommendAdapter(
                R.layout.item_classify_right_layout,
                viewModel.categoriBooklist.value!!
            )

        val emptyView = LayoutInflater.from(context).inflate(R.layout.empty_list_layout, rv_show_book, false)

        homeClassifyRightBookShowRecommendAdapter.emptyView = emptyView

        homeClassifyRightBookShowRecommendAdapter.bindToRecyclerView(rv_show_book)

        homeClassifyRightBookShowRecommendAdapter.disableLoadMoreIfNotFullPage()

        homeClassifyRightBookShowRecommendAdapter.setEnableLoadMore(true)

        homeClassifyRightBookShowRecommendAdapter.setOnLoadMoreListener({

            viewModel.getCategoryBookList(gender, major, minor, startIndex, limit)

        }, rv_show_book)
        viewModel.categoriBooklist.observe(viewLifecycleOwner, Observer {
            homeClassifyRightBookShowRecommendAdapter.notifyDataSetChanged()
            startIndex += it.size
            homeClassifyRightBookShowRecommendAdapter.loadMoreComplete()
        })

        viewModel.getCategoryBookList(gender, major, minor, startIndex, limit)

        homeClassifyRightBookShowRecommendAdapter.setOnItemClickListener { adapter, view, position ->


            val intent = Intent(this@ShowBookClassifyInfoFragment.context, BookIntroductionActivity::class.java)

            val data = homeClassifyRightBookShowRecommendAdapter.dataList[position]

            intent.putExtra(Const.IntentData.BOOK_INFO_OBJ_INTENT_KEY, data)


            val pairCover = androidx.core.util.Pair<View, String>(
                view.iv_book_cover,
                ViewCompat.getTransitionName(view.iv_book_cover)
            )
            val makeSceneTransitionAnimation =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, pairCover)

            ActivityCompat.startActivity(context!!, intent, makeSceneTransitionAnimation.toBundle())


        }

    }

}
