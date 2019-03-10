package book.fmy.org.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import book.fmy.org.R
import book.fmy.org.ui.activity.MainActivity
import kotlinx.android.synthetic.main.boot_two_fragment.*

class BootTwoFragment : BaseFragment<BootTwoViewModel>() {
    override fun getViewMode(): BootTwoViewModel {
        return ViewModelProviders.of(this).get(BootTwoViewModel::class.java)

    }

    override fun initImmersionBar() {
    }

    companion object {
        fun newInstance() = BootTwoFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.boot_two_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BootTwoViewModel::class.java)

        btn_into.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
            if ( context is Activity) {
                (context as Activity).finish()
            }
        }
    }

}
