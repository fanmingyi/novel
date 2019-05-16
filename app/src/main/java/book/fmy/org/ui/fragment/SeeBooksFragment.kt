package book.fmy.org.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import book.fmy.org.R
import book.fmy.org.viewmodels.SeeBooksViewModel
import book.fmy.org.viewmodels.ShowBookClassifyInfoViewModel
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.see_books_fragment.*

class SeeBooksFragment : BaseFragment<SeeBooksViewModel>() {
    override fun getViewMode(): SeeBooksViewModel {
        return ViewModelProviders.of(this).get(SeeBooksViewModel::class.java)
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this).reset().fitsSystemWindows(true)
            .statusBarColor(R.color.colorPrimary)
            .init()
    }

    companion object {
        fun newInstance() = SeeBooksFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.see_books_fragment, container, false)
    }

    val data = mutableListOf<String>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        data.add("《老人与海》")
        data.add("《Android入门开发》")
        data.add("《最美的女孩》")
        data.add("《蛙》")
        data.add("《过去的三十年》")
        data.add("《习近平领导下的中国》")
        data.add("《为什么人人都爱朱镕基》")
        data.add("《过去的三十年》")
        data.add("《过去的三十年》")
        data.add("《过去的三十年》")
        step_view
            .setStepViewTexts(data)//总步骤
            .setTextSize(12)//set textSize
            .setStepsViewIndicatorComplectingPosition(data.size)
            .setStepsViewIndicatorCompletedLineColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorPrimary
                )
            )//设置StepsViewIndicator完成线的颜色
            .setStepsViewIndicatorUnCompletedLineColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorPrimary
                )
            )//设置StepsViewIndicator未完成线的颜色
            .setStepViewComplectedTextColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorPrimary
                )
            )//设置StepsView text完成线的颜色
            .setStepViewUnComplectedTextColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorPrimary
                )
            )//设置StepsView text未完成线的颜色
            .setStepsViewIndicatorCompleteIcon(
                ContextCompat.getDrawable(
                    context!!,
                    R.mipmap.complet
                )
            )//设置StepsViewIndicator CompleteIcon
            .setStepsViewIndicatorDefaultIcon(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.default_icon
                )
            )//设置StepsViewIndicator DefaultIcon
            .setStepsViewIndicatorAttentionIcon(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.attention
                )
            );//设置StepsViewIndicator AttentionIcon


    }

}
