package book.fmy.org;

import android.app.Application;
import android.transition.ChangeBounds;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.tencent.mmkv.MMKV;

/**
 * @author fmy
 * @date 2019/1/2
 */
public class App extends Application {
    public static App INSTANCE;
    {
        INSTANCE = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
    }


}
