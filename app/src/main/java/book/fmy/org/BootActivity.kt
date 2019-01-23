package book.fmy.org

import android.annotation.TargetApi
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.transition.Scene
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_boot.*
import java.lang.Thread.sleep
import java.security.AccessController.getContext

/**
 * 第一次登陆的时候的引导页面
 */
class BootActivity : AppCompatActivity() {


    var fragments = arrayOf(BlankFragment.newInstance(), BlankFragment.newInstance())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boot)

        val vpAdapter = VpAdapter(supportFragmentManager)
        vp.adapter = vpAdapter
        var bootAnimationHelper = BootAnimationHelper()
        vp.addOnPageChangeListener(bootAnimationHelper)
        vp.setPageTransformer(false, bootAnimationHelper)
    }

    inner class VpAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return 2;
        }

    }
}
