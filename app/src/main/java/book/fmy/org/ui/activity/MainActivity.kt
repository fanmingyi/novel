package book.fmy.org.ui.activity

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.util.SparseArray
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import book.fmy.org.R
import book.fmy.org.ui.fragment.HomeClassifyFragment
import book.fmy.org.ui.fragment.HomeMainFragment
import book.fmy.org.ui.fragment.PersonalFragment
import com.google.android.material.tabs.TabLayout
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_item_layout.view.*


class MainActivity : BaseActivity() {


    val index2Fragment by lazy {
        val sparseArray = SparseArray<Fragment>()
        sparseArray.put(0, homeMainFragment)
        sparseArray.put(1, homeClassifyFragment)
        sparseArray.put(2, personalFragment)

        sparseArray

    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy();
    }

    val homeMainFragment: HomeMainFragment by lazy {
        val fragment = HomeMainFragment.newInstance()
        fragment
    }
    val personalFragment: PersonalFragment by lazy {
        val fragment = PersonalFragment.newInstance()
        fragment
    }
    private val homeClassifyFragment: HomeClassifyFragment by lazy {
        val fragment = HomeClassifyFragment.newInstance()
        fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTranslucentStatus(true)
        setContentView(R.layout.activity_main)
        initView()
        initClick()
        ImmersionBar.with(this).init();


    }

    private fun initClick() {


    }

    private fun initView() {
        initNavacation()

    }

    private fun setTranslucentStatus(on: Boolean) {
        val win = window
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
    private fun initNavacation() {


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
                val fragment = index2Fragment[tab.position]

                fragment?.let {
                    val transient = this@MainActivity.supportFragmentManager.beginTransaction()
                    transient.hide(it)
                    transient.commitAllowingStateLoss()
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                var iv: ImageView = tab.customView!!.findViewById(R.id.iv_icon)
                if (iv.drawable is StateListDrawable) {
                    val stateListDrawable = iv.drawable as StateListDrawable
                    val animatedVectorDrawable = stateListDrawable.current
                    (animatedVectorDrawable as AnimatedVectorDrawable).start()
                }

                var fragment = index2Fragment[tab.position]

                fragment?.let {
                    val transient = this@MainActivity.supportFragmentManager.beginTransaction()
                    if (!it.isAdded) {
                        transient.add(R.id.fl_contain, it)
                    }
                    transient.show(it)
                    transient.commitAllowingStateLoss()
                }
            }

        })

        newTabItem("主页", R.drawable.select_home_main, tb_navigation, true)
        newTabItem("分类", R.drawable.select_home_classification, tb_navigation)
        newTabItem("个人", R.drawable.select_home_personage, tb_navigation)

    }


    fun newTabItem(
        title: String, @DrawableRes stateListDrawableId: Int,
        tabLayout: TabLayout,
        isSelect: Boolean = false
    ) {

        val newTab = tabLayout.newTab()
        var tabItemLayout = layoutInflater.inflate(book.fmy.org.R.layout.tab_item_layout, newTab.view, false);
        tabItemLayout.iv_icon.setImageDrawable(getDrawable(stateListDrawableId))
        tabItemLayout.tv_tab_name.text = title
        newTab.customView = tabItemLayout
        tabLayout.addTab(newTab, isSelect)
    }

}
