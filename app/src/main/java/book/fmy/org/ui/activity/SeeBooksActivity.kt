package book.fmy.org.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import book.fmy.org.R
import book.fmy.org.ui.fragment.SeeBooksFragment
import com.gyf.barlibrary.ImmersionBar

class SeeBooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.see_books_activity)
        ImmersionBar.with(this).init()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SeeBooksFragment.newInstance())
                .commitNow()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()

    }
}
