package com.example.phonebatterystate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import android.content.IntentFilter
import android.content.BroadcastReceiver
import android.content.Context
import android.os.BatteryManager
import com.example.phonebatterystate.databinding.ActivityMainBinding

var binding: ActivityMainBinding? = null


class MainActivity : AppCompatActivity() {

    //
    private var batteryReceiver: BroadcastReceiver? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        batteryReceiver = CheckPhoneBatteryState()


    }

    override fun onStart() {
        //
        registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        super.onStart()
    }

    override fun onStop() {
        //
        unregisterReceiver(batteryReceiver)
        super.onStop()
    }


}


//
class CheckPhoneBatteryState : BroadcastReceiver() {

    //
    override fun onReceive(context: Context, intent: Intent) {
        //

        if (intent.action == "android.intent.action.BATTERY_CHANGED") {

            //
            val batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)

            if (batteryLevel in 100 downTo 70) binding?.textViewBattaryLevelId?.text = "Excellent"
            else if (batteryLevel in 69 downTo 30) binding?.textViewBattaryLevelId?.text = "Good"
            else if (batteryLevel in 29 downTo 5) binding?.textViewBattaryLevelId?.text = "Low"

        }

        //


        if (intent.getIntExtra(
                BatteryManager.EXTRA_STATUS,
                -1
            ) == BatteryManager.BATTERY_STATUS_CHARGING
        ) {
            binding?.textViewChargingStateId?.text = "Charging"
        } else {
            binding?.textViewChargingStateId?.text = "Not Charging"
        }
    }

}