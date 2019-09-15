package com.example.uberdetail.presenter

import com.example.uberdetail.interactor.UberTripRepositoryNetwork
import com.example.uberdetail.view.UberTripDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UberTripDetailPresenter(private val view: UberTripDetailView) {

    private val repository = UberTripRepositoryNetwork()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun showUberTripDetail(tripId: String) {
        val observer =
            repository.getTrip(tripId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.renderTripDetail(it.third, it.first, it.second)
                },
                {
                    view.showError()
                }
            )

        compositeDisposable.add(observer)
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

}