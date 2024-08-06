package com.example.democoroutine.api

import com.example.democoroutine.util.Constants.Companion.BASE_URL
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    //instance của Retrofit để giao tiếp với một API và sử dụng Gson để chuyển đổi dữ liệu JSON
    // Khởi tạo Retrofit instance theo kiểu lazy
    companion object{
        private val retrofit by lazy {
            // Tạo một interceptor để log các yêu cầu và phản hồi HTTP
            val  logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            // Tạo một OkHttpClient và thêm interceptor logging vào nó
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            // Xây dựng instance của Retrofit
            Retrofit.Builder()
                .baseUrl(BASE_URL) // Thiết lập URL cơ bản cho API
                .addConverterFactory(GsonConverterFactory.create()) // Sử dụng Gson để chuyển đổi JSON
                .client(client)
                .build()
        }
        // Khởi tạo API interface theo kiểu lazy
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }

    }
}