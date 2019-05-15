package book.fmy.org.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import book.fmy.org.R
import book.fmy.org.ui.activity.ui.helperandtellactivity2.HelperAndTellActivity2Fragment
import com.gyf.barlibrary.ImmersionBar

class HelperAndTellActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.helper_and_tell_activity2_activity)
        ImmersionBar.with(this).init();

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HelperAndTellActivity2Fragment.newInstance())
                .commitNow()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy();

    }

}
