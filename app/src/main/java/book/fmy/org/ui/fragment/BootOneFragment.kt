package book.fmy.org.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import book.fmy.org.R
import book.fmy.org.viewmodels.BootOneViewModel
import java.util.*


class BootOneFragment : Fragment() {

    companion object {
        fun newInstance() = BootOneFragment()
    }

    private lateinit var viewModel: BootOneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val random = Random()
        val inflate = inflater.inflate(R.layout.blank_fragment, container, false)
        inflate.setBackgroundColor(Color.argb(random.nextInt(256),random.nextInt(256),random.nextInt(256),random.nextInt(256)))
        return inflate
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }





}
