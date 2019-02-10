package book.fmy.org.adapters

import android.widget.ImageView
import androidx.lifecycle.LifecycleObserver
import book.fmy.org.R
import book.fmy.org.constant.Const
import book.fmy.org.net.Doc
import book.fmy.org.utils.TimeFormatUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class IntroduceCommendAdapter(dataList: MutableList<Doc>) :
    BaseQuickAdapter<Doc, BaseViewHolder>(R.layout.item_introduction_comment_layout, dataList), LifecycleObserver {

    override fun convert(helper: BaseViewHolder, doc: Doc) {


        doc.author.apply {
            val iv_user_img = helper.getView<ImageView>(R.id.iv_user_img)
            Glide.with(mContext).load(Const.Net.URL_RESOURCE + avatar).apply(
                RequestOptions.circleCropTransform()
            ).into(iv_user_img)
            helper.setText(R.id.tv_user_name, nickname)
        }

        if (helper.adapterPosition <= 2) {
            helper.setVisible(R.id.mcv_ranking, true)

            helper.setText(R.id.tv_ranking,"TOP${helper.adapterPosition+1}")
        }else{
            helper.setVisible(R.id.mcv_ranking, false)
        }
        doc.apply {
            helper.setText(R.id.tv_comment, content)
            helper.setText(R.id.tv_history, TimeFormatUtil.UTC2GST(created))
        }

    }


}
