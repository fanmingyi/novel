package book.fmy.org.adapters

import book.fmy.org.R
import book.fmy.org.constant.Const
import book.fmy.org.constant.Const.ItemType.BOOKCATALOGUE_ITEM_TYPE
import book.fmy.org.constant.Const.ItemType.CHAPTER_ITEM_TYPE
import book.fmy.org.net.Chapter
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity


class IntroduceLeftCatalogueAdapter(data: MutableList<MultiItemEntity>) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {

    init {
        addItemType(Const.ItemType.BOOKCATALOGUE_ITEM_TYPE, R.layout.item_catalogue_section)
        addItemType(Const.ItemType.CHAPTER_ITEM_TYPE, R.layout.item_catalogue_section)
    }

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {

        when (item.itemType) {
            BOOKCATALOGUE_ITEM_TYPE -> {
                if (item is ExpandableCatalogue) {
                    helper.setText(R.id.tv_section,item.sectionName)
                    helper.itemView.setOnClickListener {
                        if (item.isExpanded) {
                            collapse(helper.adapterPosition)
                        } else {
                            expand(helper.adapterPosition)
                        }
                    }

                }

            }
            CHAPTER_ITEM_TYPE -> {
                if (item is Chapter) {
                    helper.setText(R.id.tv_section,item.sectionName)
                }

            }

        }
    }


}
