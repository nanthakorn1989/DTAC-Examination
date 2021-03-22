package com.dtac.examination.app.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

open class BaseViewModel : ViewModel() {

    fun <T> serviceSuccess(response: Response<T>, mutableLiveData: MutableLiveData<T>) {
        Observable.just(response.body() as T)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :
                Observer<T?> {

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

                override fun onNext(respone: T) {
                    mutableLiveData.value = respone
                }
            })
    }
}