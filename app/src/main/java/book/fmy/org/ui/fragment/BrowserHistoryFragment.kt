package book.fmy.org.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import book.fmy.org.R
import book.fmy.org.viewmodels.BrowserHistoryViewModel

class BrowserHistoryFragment() : BaseFragment<BrowserHistoryViewModel>() {
    override fun getViewMode(): BrowserHistoryViewModel {
        return ViewModelProviders.of(this).get(BrowserHistoryViewModel::class.java)

    }

    override fun initImmersionBar() {
    }

    companion object {
        fun newInstance() = BrowserHistoryFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.browser_history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
