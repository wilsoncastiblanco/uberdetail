package com.example.uberdetail.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uberdetail.R
import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip
import com.example.uberdetail.presenter.UberTripDetailPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UberTripDetailView {

    private val presenter = UberTripDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.showUberTripDetail("2")
    }

    override fun renderTripDetail(trip: Trip, driver: Driver, bill: Bill) {
        text_trip.text = "Trip Identifier: ${trip.id}"
        text_driver.text = "Driver Name: ${driver.name}"
        text_bill.text = "Trip Price: ${bill.price}"
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
