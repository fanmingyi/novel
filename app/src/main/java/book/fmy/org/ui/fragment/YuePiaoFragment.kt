package book.fmy.org.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import book.fmy.org.R
import book.fmy.org.viewmodels.YuePiaoViewModel
import com.chad.library.adapter.base.BaseViewHolder
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.item_yuepiao.view.*
import kotlinx.android.synthetic.main.yue_piao_fragment.*

class YuePiaoFragment : BaseFragment<YuePiaoViewModel>() {
    override fun getViewMode(): YuePiaoViewModel {
        return ViewModelProviders.of(this).get(YuePiaoViewModel::class.java)
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this).reset().fitsSystemWindows(true)
            .statusBarColor(R.color.colorPrimary)
            .init()
    }

    companion object {
        fun newInstance() = YuePiaoFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.yue_piao_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        list.layoutManager = linearLayoutManager

        val listAdapter = ListAdapter()

        data.add(R.mipmap.yuepiao1)
        data.add(R.mipmap.yuepiao2)
        data.add(R.mipmap.yuepiao3)

        list.adapter = listAdapter

    }

    var data = mutableListOf<Int>()


    inner class ListAdapter : RecyclerView.Adapter<MyViewHold>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHold {
            val inflateView = LayoutInflater.from(context).inflate(R.layout.item_yuepiao, parent, false)
            return MyViewHold(inflateView)
        }

        override fun getItemCount(): Int {
            return data.size;
        }

        override fun onBindViewHolder(holder: MyViewHold, position: Int) {

            val img_id = data[position]
            holder.iv.setImageResource(img_id)
            holder.iv2.setImageResource(img_id)
        }


    }

    class MyViewHold(val view: View) : BaseViewHolder(view) {

        var iv: ImageView = view.img_shadow
        var iv2: ImageView = view.img_shadow2

    }
}
