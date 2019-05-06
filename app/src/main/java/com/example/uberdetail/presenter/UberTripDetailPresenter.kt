package com.example.uberdetail.presenter

import com.example.uberdetail.interactor.UberTripRepositoryNetwork
import com.example.uberdetail.view.UberTripDetailView
import kotlinx.coroutines.*

class UberTripDetailPresenter(private val view: UberTripDetailView) {

    private val job = Job()
    private val scopeMainThread = CoroutineScope(job + Dispatchers.Main)
    private val scopeIO = CoroutineScope(job + Dispatchers.Default)

    private val repository = UberTripRepositoryNetwork()

    fun showUberTripDetail(tripId: String) {
        scopeIO.launch {
            val trip = repository.getTrip(tripId)
            val driver = repository.getTripDriver(trip.userId)
            val bill = repository.getTripBill(trip.billId)
            scopeMainThread.launch {
                view.renderTripDetail(trip, driver, bill)
            }
        }
    }

    fun onDestroy() {
        job.cancel()
    }

}