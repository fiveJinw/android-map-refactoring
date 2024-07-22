package campus.tech.kakao.map.network

import campus.tech.kakao.map.BuildConfig
import campus.tech.kakao.map.data.KakaoResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    const val API_KEY = "KakaoAK ${BuildConfig.KAKAO_REST_API_KEY}"

    val retrofitService: RetrofitService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    fun searchCategory(categoryGroupCode: String, callback: Callback<KakaoResponse>) {
        retrofitService.getSearchCategory(API_KEY, categoryGroupCode).enqueue(callback)
    }

    fun searchKeyword(query: String, callback: Callback<KakaoResponse>) {
        retrofitService.getSearchKeyword(API_KEY, query).enqueue(callback)
    }
}
