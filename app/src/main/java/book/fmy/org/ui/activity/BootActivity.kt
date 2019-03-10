package book.fmy.org.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import book.fmy.org.ui.animation.BootAnimationHelper
import book.fmy.org.R
import book.fmy.org.ui.fragment.BootOneFragment
import book.fmy.org.ui.fragment.BootTwoFragment
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_boot.*

/**
 * 第一次登陆的时候的引导页面
 */
class BootActivity : BaseActivity() {


    var fragments = arrayOf(
        BootOneFragment.newInstance(), BootOneFragment.newInstance(),
        BootTwoFragment.newInstance())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boot)

        val vpAdapter = VpAdapter(supportFragmentManager)
        vp.adapter = vpAdapter
        var bootAnimationHelper = BootAnimationHelper()
        vp.addOnPageChangeListener(bootAnimationHelper)
        vp.setPageTransformer(false, bootAnimationHelper)
        ImmersionBar.with(this).init()

    }

    inner class VpAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy();
    }
}
