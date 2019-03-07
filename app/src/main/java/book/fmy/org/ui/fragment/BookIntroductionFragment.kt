package book.fmy.org.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import book.fmy.org.R
import book.fmy.org.adapters.IntroduceCommendAdapter
import book.fmy.org.adapters.IntroduceLeftCatalogueAdapter
import book.fmy.org.constant.Const
import book.fmy.org.net.BookInfo
import book.fmy.org.utils.UnitUtils
import book.fmy.org.viewmodels.BookIntroductionViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.entity.MultiItemEntity
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.book_introduction_fragment.*
import kotlinx.coroutines.launch
import treader.ReadActivity2

class BookIntroductionFragment : BaseFragment<BookIntroductionViewModel>() {
    override fun initImmersionBar() {

    }

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
        initDate()
    }

    private fun initDate() {
        launch {


            //            val bookCategories = NetHelper.service.bookChapters(viewModel.bookInfo.value!!._id).await()
//            bookCategories.chapters
        }

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
            viewModel.getchapter()
            val introduceLeftCatalogueAdapter =
                IntroduceLeftCatalogueAdapter(viewModel.chapters.value!! as MutableList<MultiItemEntity>)

            rv_catalogue.adapter = introduceLeftCatalogueAdapter
            rv_catalogue.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager?

            viewModel.chapters.observe(viewLifecycleOwner, Observer { chapterList ->
                introduceLeftCatalogueAdapter.notifyDataSetChanged()

            })

            ll_catalog.setOnClickListener {

                dl_catalogue.openDrawer(Gravity.LEFT)
            }

            btn_tart_read.setOnClickListener {
                startActivity(Intent(context, ReadActivity2::class.java))
            }


        }



        rv_comment.layoutManager = linearLayoutManager
        introduceCommendAdapter.bindToRecyclerView(rv_comment)

        viewModel.commentList.observe(viewLifecycleOwner, Observer {
            introduceCommendAdapter.notifyDataSetChanged()
        })

        var emptyViewHolder = LayoutInflater.from(context).inflate(R.layout.empty_list_layout, null)

        introduceCommendAdapter.emptyView = emptyViewHolder
        viewModel.getComment()


    }


}
