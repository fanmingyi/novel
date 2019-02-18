package book.fmy.org;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import com.tencent.mmkv.MMKV;
import org.litepal.LitePalApplication;
import treader.Config;
import treader.PageFactory2;
import treader.util.PageFactory;

import java.io.*;

/**
 * @author fmy
 * @date 2019/1/2
 */
public class AppContext extends Application {
    public static AppContext INSTANCE;
    {
        INSTANCE = this;
    }



    public static volatile Context applicationContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        MMKV.initialize(this);

        LitePalApplication.initialize(this);
        Config.createConfig(this);
        PageFactory.createPageFactory(this);
        PageFactory2.createPageFactory(this);

//        initialEnv();
    }



}
