package com.example.myaccelerometer2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class HistoryActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val dbHelper = DBHelper(this)
        val cursor = dbHelper.getOrientation()

        val seriesX = LineGraphSeries<DataPoint>()
        val seriesY = LineGraphSeries<DataPoint>()
        val seriesZ = LineGraphSeries<DataPoint>()

        while (cursor?.moveToNext() == true) {
            val timestamp = cursor.getLong(cursor.getColumnIndex(DBHelper.TIMESTAMP_COL)).toDouble()
            val x = cursor.getFloat(cursor.getColumnIndex(DBHelper.X_COL))
            val y = cursor.getFloat(cursor.getColumnIndex(DBHelper.Y_COL))
            val z = cursor.getFloat(cursor.getColumnIndex(DBHelper.Z_COL))

            seriesX.appendData(DataPoint(timestamp, x.toDouble()), true, 1000)
            seriesY.appendData(DataPoint(timestamp, y.toDouble()), true, 1000)
            seriesZ.appendData(DataPoint(timestamp, z.toDouble()), true, 1000)
        }

        val graphViewX = findViewById<GraphView>(R.id.graphx)
        graphViewX.addSeries(seriesX)
        graphViewX.title = "Time (ms)"
        graphViewX.gridLabelRenderer.verticalAxisTitle = "X"


        val graphViewY = findViewById<GraphView>(R.id.graphy)
        graphViewY.addSeries(seriesY)
        graphViewY.title = "Time (ms)"
        graphViewY.gridLabelRenderer.verticalAxisTitle = "Y"

        val graphViewZ = findViewById<GraphView>(R.id.graphz)
        graphViewZ.addSeries(seriesZ)
        graphViewZ.title = "Time (ms)"
        graphViewZ.gridLabelRenderer.verticalAxisTitle = "Z"
    }
}
