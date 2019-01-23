package book.fmy.org

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_item_layout.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newTabItem("主页",R.drawable.select_cb_bg,tb_navigation)
        newTabItem("分类",R.drawable.select_home_classification,tb_navigation)
        newTabItem("个人",R.drawable.select_home_personage,tb_navigation)

        tb_navigation.addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                var iv: ImageView = tab.customView!!.findViewById(R.id.iv_icon)
                if (iv.drawable is StateListDrawable) {

                    val stateListDrawable = iv.drawable as StateListDrawable

                    val animatedVectorDrawable = stateListDrawable.current

                    (animatedVectorDrawable as AnimatedVectorDrawable).start()

                    println()


                }
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                println()
                var iv: ImageView = tab.customView!!.findViewById(R.id.iv_icon)


                if (iv.drawable is StateListDrawable) {

                    val stateListDrawable = iv.drawable as StateListDrawable

                    val animatedVectorDrawable = stateListDrawable.current

                    (animatedVectorDrawable as AnimatedVectorDrawable).start()

                    println()

                }
            }


        })

    }


    fun newTabItem(title: String, @DrawableRes stateListDrawableId: Int, tabLayout: TabLayout) {
        val newTab = tabLayout.newTab()
        var tabItemLayout = layoutInflater.inflate(R.layout.tab_item_layout, newTab.view, false);
        tabItemLayout.iv_icon.setImageDrawable(getDrawable(stateListDrawableId))
        tabItemLayout.tv_tab_name.text = title
        newTab.customView = tabItemLayout
        tabLayout.addTab(newTab)
    }

}
