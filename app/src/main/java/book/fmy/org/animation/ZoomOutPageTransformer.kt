package book.fmy.org.animation

import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager

class ZoomOutPageTransformer: ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {

        if (position <= 1) {

            val scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE)
            page.scaleX = scaleFactor
            if (position > 0) {
                page.translationX = -scaleFactor * 2
            } else if (position < 0) {
                page.translationX = scaleFactor * 2
            }

            page.scaleY = scaleFactor
        } else {

            page.scaleX = MIN_SCALE
            page.scaleY = MIN_SCALE
        }
    }

    companion object {
        //自由控制缩放比例
        private val MAX_SCALE = 1f
        private val MIN_SCALE = 0.85f//0.85f
    }

}
