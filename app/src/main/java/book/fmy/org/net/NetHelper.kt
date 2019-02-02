package book.fmy.org.net

import android.util.Log
import book.fmy.org.constant.Const.Net.URL_BASE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class HttpLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.d("HttpLogInfo", message);//okHttp的详细日志会打印出来
    }
}

object NetHelper {

    val okHttpClient: OkHttpClient by lazy {
        val logInterceptor = HttpLoggingInterceptor(HttpLogger())//创建拦截对象
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY//这一句一定要记得写，否则没有数据输出
        OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(logInterceptor)
            .build()

    }


    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(URL_BASE).build()
    }

    val service by lazy {
        retrofit.create(Api::class.java)
    }


}
