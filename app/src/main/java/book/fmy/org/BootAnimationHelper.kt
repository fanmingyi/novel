package book.fmy.org

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager

/**
 * @author 范明毅
 */
class BootAnimationHelper : ViewPager.PageTransformer, ViewPager.OnPageChangeListener {


    var position = 0;

    override fun onPageScrollStateChanged(state: Int) {


    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {


    }

    var pageChanged = true
    override fun onPageSelected(position: Int) {
        this.position = position
        pageChanged = true

    }

    private val ROT_MOD = -20

    override fun transformPage(page: View, position: Float) {


        var iv_bg1 = page.findViewById<ImageView>(R.id.iv_bg1)
        var iv_bg2 = page.findViewById<ImageView>(R.id.iv_bg2)
        var fl_phone_img_container = page.findViewById<ImageView>(R.id.fl_phone_img_container)
        var iv_show_1 = page.findViewById<ImageView>(R.id.iv_show_1)
        var iv_show_2 = page.findViewById<ImageView>(R.id.iv_show_2)
        Log.e("方法参数", "BootAnimationHelper.transformPage::" + "page = [${page}], position = [${position}]")

        val argbEvaluator = ArgbEvaluator()

        val view = page.parent as View

        val evaluate = argbEvaluator.evaluate(position, Color.RED, Color.BLUE)

        view.setBackgroundColor(evaluate as Int)

        //完成某个界面的滑动
        if (position == 0f) {
            if (pageChanged) {
                iv_bg1.visibility = View.VISIBLE
                iv_bg2.visibility = View.VISIBLE
                iv_bg1.translationX
                val animator_bg1 = ObjectAnimator.ofFloat(iv_bg1, "translationX", 0f, -iv_bg1.measuredWidth.toFloat())
                val animator_bg2 = ObjectAnimator.ofFloat(iv_bg2, "translationX", iv_bg1.measuredWidth.toFloat(), 0f)

                animator_bg1.duration = 3000
                animator_bg2.duration = 3000
                animator_bg1.start()
                animator_bg2.start()

                val animator_ivshow1 =
                    ObjectAnimator.ofFloat(iv_show_1, "translationX", iv_show_1.measuredWidth.toFloat(), 0f)

                val animator_ivshow2 =
                    ObjectAnimator.ofFloat(iv_show_2, "translationX", 0f, -iv_show_2.measuredWidth.toFloat())

                animator_ivshow1.duration = 3000
                animator_ivshow2.duration = 3000

                animator_ivshow1.duration = 3000
                animator_ivshow2.duration = 3000
                animator_ivshow1.start()
                animator_ivshow2.start()

                pageChanged = false


            }

        } else if (position == -1f || position == 1f) {
            //某个界面被滑出
            page.pivotX = 0f
            page.pivotY = 0f
            iv_bg1.translationX = 0f
            iv_bg2.translationX = 0f
            iv_bg2.visibility = View.GONE
            page.rotation = 0f

            iv_show_1.translationX = 0f
            iv_show_2.translationX = 0f

        } else {
            //设置旋转坐标
            page.pivotX = (page.measuredWidth / 2).toFloat()
            page.pivotY = page.measuredHeight.toFloat()
            val rotation = ROT_MOD * position * -1.25f
            page.rotation = rotation
        }


    }


}
