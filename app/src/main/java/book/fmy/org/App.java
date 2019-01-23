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


    @Override
    public void onCreate() {
        super.onCreate();

        MMKV.initialize(this);
    }


}
