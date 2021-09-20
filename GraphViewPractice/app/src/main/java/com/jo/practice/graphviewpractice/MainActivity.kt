package com.jo.practice.graphviewpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var graphView: GraphView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        graphView = findViewById(R.id.graph_view)

        val dateList = ArrayList<String>()
        dateList.add("08/01")
        dateList.add("08/06")
        dateList.add("08/11")
        dateList.add("08/16")
        dateList.add("08/21")
        dateList.add("08/26")
        dateList.add("08/31")

        graphView.addAllDateTextList(dateList)
        val dotList = ArrayList<Pair<Int,Int>>()
        dotList.add(Pair(1,90))
        dotList.add(Pair(5,70))
        dotList.add(Pair(7,50))
        dotList.add(Pair(10,80))
        dotList.add(Pair(12,30))
        dotList.add(Pair(31,100))
        graphView.putGraphDotOnGraph(31,dotList)
        graphView.invalidate()
    }
}