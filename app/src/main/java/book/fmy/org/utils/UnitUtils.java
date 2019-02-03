package book.fmy.org.utils;

import android.util.TypedValue;
import book.fmy.org.App;

public class UnitUtils {

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, App.INSTANCE.getResources().getDisplayMetrics());
    }

    public static int px2dp(float pxValue) {
        return (int) (pxValue / App.INSTANCE.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int px2sp(float pxValue) {
        return (int) (pxValue / App.INSTANCE.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static float px2sp(int size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, size, App.INSTANCE.getResources().getDisplayMetrics());
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / App.INSTANCE.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int dip2px(float dipValue) {
        return (int) (dipValue * App.INSTANCE.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int sp2px(float spValue) {
        return (int) (spValue * App.INSTANCE.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static float sp2px(int size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, App.INSTANCE.getResources().getDisplayMetrics());
    }
}