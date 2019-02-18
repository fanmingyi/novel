package book.fmy.org.utils;

import android.util.TypedValue;
import book.fmy.org.AppContext;

public class UnitUtils {

    public static int dp2px(int dp) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, AppContext.INSTANCE.getResources().getDisplayMetrics());
    }

    public static int px2dp(float pxValue) {
        return (int) (pxValue / AppContext.INSTANCE.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int px2sp(float pxValue) {
        return (int) (pxValue / AppContext.INSTANCE.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static float px2sp(int size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, size, AppContext.INSTANCE.getResources().getDisplayMetrics());
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / AppContext.INSTANCE.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int dip2px(float dipValue) {
        return (int) (dipValue * AppContext.INSTANCE.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int sp2px(float spValue) {
        return (int) (spValue * AppContext.INSTANCE.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static float sp2px(int size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, AppContext.INSTANCE.getResources().getDisplayMetrics());
    }
}