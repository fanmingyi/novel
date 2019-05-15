package book.fmy.org.ui.activity.ui.helperandtellactivity2

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import book.fmy.org.R
import book.fmy.org.ui.fragment.BaseFragment
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.helper_and_tell_activity2_fragment.*

class HelperAndTellActivity2Fragment : BaseFragment<HelperAndTellActivity2ViewModel>() {
    override fun getViewMode(): HelperAndTellActivity2ViewModel {
        return ViewModelProviders.of(this).get(HelperAndTellActivity2ViewModel::class.java)

    }

    override fun initImmersionBar() {

        ImmersionBar.with(this).reset().fitsSystemWindows(true).navigationBarColor(R.color.colorPrimary)
            .statusBarColor(R.color.colorPrimary)
            .init()
    }


    companion object {
        fun newInstance() = HelperAndTellActivity2Fragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.helper_and_tell_activity2_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel


        btn_submit.setOnClickListener {

            activity!!.finish()
            Toast.makeText(context!!.applicationContext, "提交成功", Toast.LENGTH_LONG).show()
        }
    }

}
