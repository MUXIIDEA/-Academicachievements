package com.bugmuxi.academicachievements.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit网络请求客户端
 * 
 * 功能说明：
 * - 配置Retrofit实例
 * - 配置OkHttpClient
 * - 配置日志拦截器
 * 
 * @author School System
 * @version 1.0.0
 */
object RetrofitClient {
    
    // API基础地址 (根据实际情况修改)
    private const val BASE_URL = "http://192.168.1.100:8080/api/"
    
    // 日志拦截器
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    // OkHttpClient配置
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    
    // Retrofit实例
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    /**
     * 获取API服务实例
     */
    val classBoardApiService: ClassBoardApiService by lazy {
        retrofit.create(ClassBoardApiService::class.java)
    }
}
