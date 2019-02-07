package book.fmy.org.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import book.fmy.org.R
import book.fmy.org.ui.fragment.BookIntroductionFragment

class BookIntroductionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_introduction_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BookIntroductionFragment.newInstance())
                .commitNow()
        }
    }

}
