package book.fmy.org.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import book.fmy.org.viewmodels.BrowserHistoryViewModel
import kotlinx.android.synthetic.main.browser_history_fragment.*
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import book.fmy.org.R
import com.vivian.timelineitemdecoration.itemdecoration.DotItemDecoration
import android.graphics.Color
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import book.fmy.org.ui.activity.BrowserHistoryActivity
import com.chad.library.adapter.base.BaseViewHolder
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.item_browser_history.view.*


class BrowserHistoryFragment() : BaseFragment<BrowserHistoryViewModel>() {
    override fun getViewMode(): BrowserHistoryViewModel {
        return ViewModelProviders.of(this).get(BrowserHistoryViewModel::class.java)

    }

    override fun initImmersionBar() {

//        (activity as BrowserHistoryActivity).setSupportActionBar(tb_title)
        ImmersionBar.with(this).reset().fitsSystemWindows(true)
            .statusBarColor(R.color.colorPrimary)
            .init()
    }

    companion object {
        fun newInstance() = BrowserHistoryFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.browser_history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_list.layoutManager = staggeredGridLayoutManager
        val mItemDecoration = DotItemDecoration.Builder(context)
            .setOrientation(DotItemDecoration.VERTICAL)//如果LayoutManager设置了横向，那么这里也要设置成横向
            .setItemStyle(DotItemDecoration.STYLE_DRAW)//选择dot使用图片资源或者用canvas画
            .setTopDistance(20f)//单位dp
            .setItemInterVal(10f)//单位dp
            .setItemPaddingLeft(10f)//如果不设置，默认和item间距一样
            .setItemPaddingRight(10f)//如果不设置，默认和item间距一样
            .setDotColor(resources.getColor(R.color.colorPrimary))
            .setDotRadius(2)//单位dp
            .setDotPaddingTop(0)
            .setDotInItemOrientationCenter(false)//设置dot居中
            .setLineColor(resources.getColor(R.color.colorPrimary))//设置线的颜色
            .setLineWidth(1f)//单位dp
            .setEndText("END")//设置结束的文字
            .setTextColor(resources.getColor(R.color.colorPrimary))
            .setTextSize(10f)//单位sp
            .setDotPaddingText(2f)//单位dp.设置最后一个点和文字之间的距离
            .setBottomDistance(40f)//设置底部距离，可以延长底部线的长度
            .create()
        rv_list.addItemDecoration(mItemDecoration)


        val listAdapter = ListAdapter()


        data.add(Pair("2019.05.16. 09:31", "书籍不错我感觉还可以"))
        data.add(Pair("2019.05.16. 07:00", "确实从里面学到了一些知识"))
        data.add(Pair("2019.05.15. 11:23", "老人与海的翻版？"))
        data.add(Pair("2019.05.14. 01:21", "加油作者"))
        data.add(Pair("2019.05.13. 02:31", "可以我喜欢"))
        data.add(Pair("2019.05.13. 01:41", "莫名的喜欢你"))
        data.add(Pair("2019.05.13. 00:21", "打卡签到算了"))


        rv_list.adapter = listAdapter


    }

    var data = mutableListOf<Pair<String, String>>()


    inner class ListAdapter : RecyclerView.Adapter<MyViewHold>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHold {
            val inflateView = LayoutInflater.from(context).inflate(R.layout.item_browser_history, parent, false)
            return MyViewHold(inflateView)
        }

        override fun getItemCount(): Int {
            return data.size;
        }

        override fun onBindViewHolder(holder: MyViewHold, position: Int) {
            val (time, comment) = data[position]

            holder.tv_time.text = time
            holder.tv_comment.text = comment
        }


    }

    class MyViewHold(val view: View) : BaseViewHolder(view) {
        var tv_time: TextView;
        var tv_comment: TextView;

        init {

            tv_time = view.tv_time
            tv_comment = view.tv_comment
        }
    }


}
