package com.example.uberdetail.presenter

import com.example.uberdetail.interactor.UberTripRepositoryNetwork
import com.example.uberdetail.view.UberTripDetailView
import kotlinx.coroutines.*

class UberTripDetailPresenter(private val view: UberTripDetailView) : CoroutineScope by MainScope() {

    private val repository = UberTripRepositoryNetwork()

    fun showUberTripDetail(tripId: String) {
        launch {
            val trip = repository.getTrip(tripId)
            val driver = repository.getTripDriver(trip.userId)
            val bill = repository.getTripBill(trip.billId)
            view.renderTripDetail(trip, driver, bill)
        }
    }

    fun onDestroy() {
        coroutineContext.cancel()
    }

}