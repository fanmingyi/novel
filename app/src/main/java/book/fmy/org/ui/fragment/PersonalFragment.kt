package book.fmy.org.ui.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import book.fmy.org.R
import book.fmy.org.ui.activity.BrowserHistoryActivity
import book.fmy.org.ui.activity.HelperAndTellActivity
import book.fmy.org.ui.activity.YuePiaoActivity
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.personal_fragment.*

class PersonalFragment : BaseFragment<PersonalViewModel>() {

    override fun getViewMode(): PersonalViewModel {
        return ViewModelProviders.of(this).get(PersonalViewModel::class.java)
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this).reset().navigationBarColor(R.color.colorPrimary)
            .statusBarColor(android.R.color.transparent)
            .init();
    }

    companion object {
        fun newInstance() = PersonalFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.personal_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PersonalViewModel::class.java)
        // TODO: Use the ViewModel


        cl_helpAndTell.setOnClickListener {

            val intent = Intent(this@PersonalFragment.context, HelperAndTellActivity::class.java)


            startActivity(intent)
        }

        cl_broswer_histrory.setOnClickListener {
            val intent = Intent(this@PersonalFragment.context, BrowserHistoryActivity::class.java)


            startActivity(intent)
        }

        cl_yuepiao.setOnClickListener {
            val intent = Intent(this@PersonalFragment.context, YuePiaoActivity::class.java)


            startActivity(intent)
        }


    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {

        }
    }
}
