package com.example.uberdetail.view

import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip

interface UberTripDetailView {

    fun renderTripDetail(trip: Trip, driver: Driver, bill: Bill)
    fun showError(message: String)
}
