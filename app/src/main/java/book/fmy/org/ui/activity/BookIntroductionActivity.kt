package book.fmy.org.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import book.fmy.org.R
import book.fmy.org.constant.Const
import book.fmy.org.net.BookInfo
import book.fmy.org.ui.fragment.BookIntroductionFragment
import com.gyf.barlibrary.ImmersionBar

class BookIntroductionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_introduction_activity)
        ImmersionBar.with(this).init()

        val bookId = intent.getParcelableExtra<BookInfo>(Const.IntentData.BOOK_INFO_OBJ_INTENT_KEY)

        if (savedInstanceState == null) {
            val bookIntroductionFragment = BookIntroductionFragment.newInstance(bookId)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,bookIntroductionFragment)
                .commitNow()
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        finishAfterTransition()
    }

}
