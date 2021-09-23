package com.makeus.kkongi.movieappwithrxjava.src.main

import android.os.Bundle
import android.util.Log
import com.makeus.kkongi.movieappwithrxjava.config.ApplicationClass.Companion.API_KEY
import com.makeus.kkongi.movieappwithrxjava.config.ApplicationClass.Companion.retrofit
import com.makeus.kkongi.movieappwithrxjava.config.BaseActivity
import com.makeus.kkongi.movieappwithrxjava.databinding.ActivityMainBinding
import com.makeus.kkongi.movieappwithrxjava.src.main.domain.Results
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var disposable: Disposable
    private val COMEDY = "35"
    private val SF = "878"
    private val HORROR = "27"
    private val THRILLER = "53"
    private val CRIME = "80"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val service = retrofit.create(MainRetrofitInterface::class.java)
        disposable =
            service.getMovieListByGenre(API_KEY, COMEDY, "ko-KR", 1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> showResult(result) }, { error -> showError(error.message) })

        /*
        tmdb api를 이용해서 리스트를 불러온 다음에 거기에 나온 첫번째 결과 id를 이용해 상세 조회를 할 것이다.
         */

        service.getMovieListByGenre(API_KEY, COMEDY, "ko-KR", 1).flatMap { firstCallResult ->
            service.getMovieOne(
                firstCallResult.results[0].id,
                API_KEY,
                "ko-KR"
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { secondResult ->
                showMovieTitle(secondResult.title)
            }

        /*
        enqueue 방식은 정상적으로 작동할까?
         */
        service.getTodayTrend(API_KEY, "ko-KR").enqueue(object : Callback<Results?> {
            override fun onResponse(
                call: Call<Results?>?,
                response: Response<Results?>
            ) {
                if (response.isSuccessful) {
                    Log.d("MainActivity", "posts loaded from API")
                } else {
                    val statusCode: Int = response.code()
                    // handle request errors depending on status code
                }
            }

            override fun onFailure(call: Call<Results?>?, t: Throwable?) {
                Log.d("MainActivity", "error loading from API")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun showResult(result: Results) {

    }

    private fun showError(message: String?) {
        message?.let {
            Log.d("Error Msg", it)
        }
    }

    private fun showMovieTitle(title: String) {
        showCustomToast(title)
    }
}