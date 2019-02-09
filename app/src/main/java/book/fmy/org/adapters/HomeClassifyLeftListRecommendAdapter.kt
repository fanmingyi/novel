package book.fmy.org.adapters

import android.graphics.Color
import android.view.View
import androidx.annotation.LayoutRes
import book.fmy.org.R
import book.fmy.org.net.SubCategoriesFlat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class HomeClassifyLeftListRecommendAdapter(@LayoutRes layoutResId: Int, var dataList: MutableList<SubCategoriesFlat>) :
    BaseQuickAdapter<SubCategoriesFlat, BaseViewHolder>(layoutResId, dataList) {

    var itemClick = 0

    override fun setOnItemClick(view: View, position: Int) {
        super.setOnItemClick(view, position)
    }


    override fun convert(helper: BaseViewHolder, item: SubCategoriesFlat) {
        helper.setText(R.id.tv_classify, item.mins)
        val clickColor = Color.WHITE
        val unClickColor = mContext.resources.getColor(R.color.colorDefaultBackground)


        if (itemClick == helper.adapterPosition) {
            helper.setBackgroundColor(R.id.mcv_root, clickColor)
        } else {
            helper.setBackgroundColor(R.id.mcv_root, unClickColor)

        }

    }
}
