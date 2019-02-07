package book.fmy.org.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import book.fmy.org.R
import book.fmy.org.viewmodels.BookIntroductionViewModel

class BookIntroductionFragment : BaseFragment<BookIntroductionViewModel>() {
    override fun getViewMode(): BookIntroductionViewModel {
        return ViewModelProviders.of(this).get(BookIntroductionViewModel::class.java)
    }

    companion object {
        fun newInstance() = BookIntroductionFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.book_introduction_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
