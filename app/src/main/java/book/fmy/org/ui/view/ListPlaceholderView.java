package book.fmy.org.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import book.fmy.org.R;

public class ListPlaceholderView {

    Context context;
    private ConstraintLayout constraintLayout;
    private TextView tv_tip;
    View.OnClickListener onClickListener;

    public ListPlaceholderView(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        produceEmptyView(context);
        this.onClickListener = onClickListener;
    }

    public ConstraintLayout produceEmptyView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        constraintLayout = (ConstraintLayout) layoutInflater.inflate(R.layout.empty_list_layout, null);
        tv_tip = constraintLayout.findViewById(R.id.tv_tip);
        constraintLayout.setOnClickListener(onClickListener);
        return constraintLayout;
    }


    public void setText(String msg) {
        tv_tip.setText(msg);
    }


    public ConstraintLayout getView() {
        return constraintLayout;
    }


}
