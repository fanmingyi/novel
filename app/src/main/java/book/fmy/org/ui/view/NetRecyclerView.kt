package book.fmy.org.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class NetRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    RecyclerView(context, attrs, defStyle) {


    override fun onMeasure(widthSpec: Int, heightSpec: Int) {


        val makeMeasureSpec = MeasureSpec.makeMeasureSpec(Int.MAX_VALUE shr 2, MeasureSpec.EXACTLY)

        super.onMeasure(widthSpec, makeMeasureSpec)


    }

}
