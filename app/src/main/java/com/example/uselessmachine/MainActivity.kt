package com.example.uselessmachine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.sax.StartElementListener
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.system.exitProcess

lateinit var switchUseless: Switch
lateinit var buttonLookBusy : Button
lateinit var buttonSelfDestruct : Button
lateinit var backgroundColor : ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        // this is a lambda-- an anonymous function (function with no name) that can be  used
        // with interface with only one method
        switchUseless.setOnCheckedChangeListener { buttonView, isChecked ->
            //toast for when it becomes checked
            if (isChecked) {
                Toast.makeText(this@MainActivity, "this is a button", Toast.LENGTH_SHORT).show()
                startSwitchTimer()
            }
            //different toast for when it is unchecked
            else {
                Toast.makeText(this@MainActivity, "jk it's a checkbox", Toast.LENGTH_SHORT).show()
            }

        }

        buttonSelfDestruct.setOnClickListener {
            startSelfDestructTimer()
        }

    }

    private fun wireWidgets() {
        switchUseless = findViewById(R.id.switch_main_useless)
        buttonLookBusy = findViewById(R.id.button_main_lookbusy)
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct)
        backgroundColor = findViewById(R.id.background_main_color)
    }
    
    private fun startSwitchTimer() {
        val uselessTimer = object : CountDownTimer(3000, 500) {
            override fun onTick(millisUntilFinished: Long) {
                // leave blank for now
                if(!switchUseless.isChecked) {
                    cancel()
                }
            }
            override fun onFinish() {
                // turn the switch off
                switchUseless.isChecked = false
            }

        }
        uselessTimer.start()
    }

    private fun startSelfDestructTimer() {
        val uselessTimer = object : CountDownTimer(10000, 1000) {
            // var lastFlashTime = 10000
            var flashDuration = 1000
            var isRed = false

            override fun onTick(millisUntilFinished: Long) {

                while (millisUntilFinished > 0) {
                    buttonSelfDestruct.text = (millisUntilFinished/1000).toString()
                    if (!isRed) {
                        backgroundColor.setBackgroundColor(Color.argb
                            (255, 255, 0, 0))
                    }

                }

            }
            override fun onFinish() {
                finish()
            }
        }
        uselessTimer.start()
    }

}

/*

Switch: Useless
Button: Self-Destruct
Button: Look busy

chain useless switch and destruct button vertically, center horizontally
look busy button centered horizontally, connected to the bottom of the screen

 */