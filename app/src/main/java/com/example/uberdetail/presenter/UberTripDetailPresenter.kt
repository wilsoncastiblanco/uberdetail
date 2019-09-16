package com.example.uberdetail.presenter

import com.example.uberdetail.interactor.UberTripRepositoryNetwork
import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip
import com.example.uberdetail.view.UberTripDetailView

class UberTripDetailPresenter(private val view: UberTripDetailView) {

    private val repository = UberTripRepositoryNetwork()

    fun showUberTripDetail(tripId: String) {
        repository.getTrip(tripId, object : Event<Trip> {

            override fun onSuccess(trip: Trip?) {

                repository.getTripDriver(trip?.userId.orEmpty(), object : Event<Driver> {

                    override fun onSuccess(driver: Driver?) {

                        repository.getTripBill(trip?.billId.orEmpty(), object : Event<Bill> {

                            override fun onSuccess(bill: Bill?) {

                                view.renderTripDetail(trip!!, driver!!, bill!!)

                            }

                            override fun onError(throwable: Throwable) {
                                view.showError(throwable.message.orEmpty())
                            }

                        }) // Bill Request
                    }

                    override fun onError(throwable: Throwable) {
                        view.showError(throwable.message.orEmpty())
                    }

                }) // Driver Request

            }

            override fun onError(throwable: Throwable) {
                view.showError(throwable.message.orEmpty())
            }

        }) // Trip Request

    }

    fun destroy() {

    }

    interface Event<T> {
        fun onSuccess(entity: T?)
        fun onError(error: Throwable)
    }

}