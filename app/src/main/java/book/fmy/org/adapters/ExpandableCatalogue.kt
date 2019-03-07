package book.fmy.org.adapters

import book.fmy.org.constant.Const.ItemType.BOOKCATALOGUE_ITEM_TYPE
import book.fmy.org.net.Chapter
import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity

class ExpandableCatalogue(var sectionIndex: Int, var sectionName: String) : AbstractExpandableItem<Chapter>(),
    MultiItemEntity {


    override fun getLevel(): Int {
        return 0
    }

    override fun getItemType(): Int {
        return BOOKCATALOGUE_ITEM_TYPE
    }
}
