package book.fmy.org.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import book.fmy.org.R
import book.fmy.org.adapters.IntroduceCommendAdapter
import book.fmy.org.constant.Const
import book.fmy.org.net.BookInfo
import book.fmy.org.utils.UnitUtils
import book.fmy.org.viewmodels.BookIntroductionViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.book_introduction_fragment.*
import kotlinx.android.synthetic.main.fragment_home_main.*

class BookIntroductionFragment : BaseFragment<BookIntroductionViewModel>() {

    override fun getViewMode(): BookIntroductionViewModel {
        return ViewModelProviders.of(this).get(BookIntroductionViewModel::class.java)
    }

    companion object {
        fun newInstance(book: Parcelable): BookIntroductionFragment {
            val bookIntroductionFragment = BookIntroductionFragment()
            bookIntroductionFragment.arguments = Bundle().apply {
                putParcelable(Const.IntentData.BOOK_INFO_OBJ_INTENT_KEY, book)
            }

            return bookIntroductionFragment

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.book_introduction_fragment, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
            val bookInfo = getParcelable<BookInfo>(Const.IntentData.BOOK_INFO_OBJ_INTENT_KEY)
            viewModel.bookInfo.value = bookInfo
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    val introduceCommendAdapter by lazy {
        IntroduceCommendAdapter(viewModel.commentList.value!!)
    }
    val linearLayoutManager by lazy {
        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun initView() {
        viewModel.bookInfo.value?.apply {
            Glide.with(context!!).load(Const.Net.URL_RESOURCE + cover).apply(
                RequestOptions.bitmapTransform(
                    RoundedCornersTransformation(
                        UnitUtils.dip2px(10f),
                        0,
                        RoundedCornersTransformation.CornerType.ALL
                    )
                )
            ).into(iv_book_cover)

            tv_book_author.text = "$author 著"

            tv_book_introduce.text = shortIntro

            tv_book_name.text = title

            tv_book_type.text = majorCate

            val serial = if (isSerial) "连载" else "完结"

            tv_isSerial.text = serial
        }



        rv_comment.layoutManager = linearLayoutManager
        introduceCommendAdapter.bindToRecyclerView(rv_comment)

        viewModel.commentList.observe(viewLifecycleOwner, Observer {
            introduceCommendAdapter.notifyDataSetChanged()
        })

        viewModel.getComment()


    }


}
