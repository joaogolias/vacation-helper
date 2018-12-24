package com.example.joaogolias.vacationhelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private var selectedTab: TabItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSelectedTab(food_tab)

        food_tab.setOnClickListener {tab ->
            setSelectedTab(tab as TabItem)
        }

        transport_tab.setOnClickListener {tab ->
            setSelectedTab(tab as TabItem)
        }

        hotel_tab.setOnClickListener {tab ->
            setSelectedTab(tab as TabItem)
        }

        other_costs_tab.setOnClickListener {tab ->
            setSelectedTab(tab as TabItem)
        }

        statistic_tab.setOnClickListener {tab ->
            setSelectedTab(tab as TabItem)
        }
    }

    private fun setSelectedTab(tabItem: TabItem) {
        tabItem.changeColorState()
        selectedTab?.changeColorState()
        selectedTab = tabItem
    }

}
