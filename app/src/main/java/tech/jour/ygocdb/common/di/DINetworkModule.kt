package tech.jour.ygocdb.common.di

import tech.jour.ygocdb.base.constant.VersionStatus
import tech.jour.ygocdb.common.constant.NetBaseUrlConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.converter.gson.GsonConverterFactory
import tech.jour.ygocdb.BuildConfig
import tech.jour.ygocdb.common.helper.HttpLogger
import javax.inject.Singleton

/**
 * 全局作用域的网络层的依赖注入模块c
 *
 * @author Qu Yunshuo
 * @since 6/4/21 8:58 AM
 */
@Module
@InstallIn(SingletonComponent::class)
class DINetworkModule {

    /**
     * [OkHttpClient]依赖提供方法
     *
     * @return OkHttpClient
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        // 日志拦截器部分
        val level = if (BuildConfig.VERSION_TYPE != VersionStatus.RELEASE) BODY else NONE
        val logInterceptor = HttpLoggingInterceptor(HttpLogger()).setLevel(level)

        return OkHttpClient.Builder()
            .connectTimeout(10L * 1000L, TimeUnit.MILLISECONDS)
            .readTimeout(10L * 1000L, TimeUnit.MILLISECONDS)
            .addInterceptor(logInterceptor)
            .retryOnConnectionFailure(true)
            .build()
    }

    /**
     * 项目主要服务器地址的[Retrofit]依赖提供方法
     *
     * @param okHttpClient OkHttpClient OkHttp客户端
     * @return Retrofit
     */
    @Singleton
    @Provides
    fun provideMainRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetBaseUrlConstant.MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}