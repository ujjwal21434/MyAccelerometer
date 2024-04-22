package com.example.myaccelerometer2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var dbHelper: DBHelper
    private var startTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Accelerometer"

        val sensmgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometerSensor = sensmgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accelerometerSensor != null){
            sensmgr.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            Toast.makeText(this, "No Accelerometer Found", Toast.LENGTH_SHORT).show()
        }

        dbHelper = DBHelper(this)
        startTime = System.currentTimeMillis()


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

    }

    private var lastUpdate: Long = 0

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {

        val curTime = System.currentTimeMillis()
        //changing sensing intervals by changing the value below
        if ((curTime - lastUpdate) > 0) {
            lastUpdate = curTime

            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                findViewById<TextView>(R.id.X).text = "X: $x"
                findViewById<TextView>(R.id.Y).text = "Y: $y"
                findViewById<TextView>(R.id.Z).text = "Z: $z"

                val elapsedTime = System.currentTimeMillis() - startTime
                dbHelper.addOrientation(x, y, z, elapsedTime)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}



