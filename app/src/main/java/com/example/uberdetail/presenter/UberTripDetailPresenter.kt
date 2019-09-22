package com.example.uberdetail.presenter

import com.example.uberdetail.interactor.TripRepository
import com.example.uberdetail.view.UberTripDetailView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UberTripDetailPresenter(private val view: UberTripDetailView,
                              private val coroutineCtx: CoroutineContext = Dispatchers.Main,
                              private val repository: TripRepository = TripRepository()) :
    CoroutineScope by CoroutineScope(coroutineCtx) {

    fun showUberTripDetail(tripId: String) {

        val handler = CoroutineExceptionHandler { _, exception ->
            view.showError("$exception")
        }

        launch(handler) {
            val trip = repository.getTrip(tripId)
            val driver = repository.getTripDriver(trip.userId)
            val bill = repository.getTripBill(trip.billId)
            view.renderTripDetail(trip, driver, bill)
        }
    }

    fun onDestroy() {
        coroutineContext.cancelChildren()
    }

}