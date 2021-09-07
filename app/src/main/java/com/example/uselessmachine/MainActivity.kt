package com.example.uselessmachine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.sax.StartElementListener
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import kotlin.system.exitProcess

lateinit var switchUseless: Switch
lateinit var buttonLookBusy : Button
lateinit var buttonSelfDestruct : Button
lateinit var backgroundColor : ConstraintLayout
lateinit var groupMainUI : Group
lateinit var progressBarLookBusy : ProgressBar
lateinit var textLookBusy : TextView
lateinit var textDownloading : TextView

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
                Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
            }

        }

        buttonSelfDestruct.setOnClickListener {
            startSelfDestructTimer()
        }

        buttonLookBusy.setOnClickListener {
            groupMainUI.visibility = View.INVISIBLE
            progressBarLookBusy.visibility = View.VISIBLE
            progressBarLookBusy.progress = 0
            textLookBusy.visibility = View.VISIBLE
            textDownloading.text = "Downloading Files..."
            textDownloading.visibility = View.VISIBLE

            startLookBusyTimer()
        }

    }

    private fun wireWidgets() {
        switchUseless = findViewById(R.id.switch_main_useless)
        buttonLookBusy = findViewById(R.id.button_main_lookbusy)
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct)
        backgroundColor = findViewById(R.id.background_main_color)
        groupMainUI = findViewById(R.id.group_main_mainUI)
        groupMainUI.visibility = View.VISIBLE
        progressBarLookBusy = findViewById(R.id.progressBar_main_lookBusy)
        progressBarLookBusy.visibility = View.INVISIBLE
        textLookBusy = findViewById(R.id.text_main_lookBusy)
        textLookBusy.visibility = View.INVISIBLE
        textDownloading = findViewById(R.id.text_main_downloading)
        textDownloading.visibility = View.INVISIBLE
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

        val uselessTimer = object : CountDownTimer(5000, 500) {

            var isRed = false

            override fun onTick(millisUntilFinished: Long) {

                buttonSelfDestruct.text = (6 + (millisUntilFinished/1000)).toString()

                    if (isRed) {
                        backgroundColor.setBackgroundColor(Color.argb(255, 255, 255, 255))
                        isRed = false
                    }
                    else {
                        backgroundColor.setBackgroundColor(Color.argb(255, 255, 0, 0))
                        isRed = true
                    }


            }
            override fun onFinish() {
                startSelfDestructTimer2()
            }
        }
        uselessTimer.start()
    }

    private fun startSelfDestructTimer2() {

        val uselessTimer = object : CountDownTimer(2000, 250) {
            // var lastFlashTime = 10000L
            var isRed = false

            override fun onTick(millisUntilFinished: Long) {

                buttonSelfDestruct.text = (4 + (millisUntilFinished/1000)).toString()

                if (isRed) {
                    backgroundColor.setBackgroundColor(Color.argb(255, 255, 255, 255))
                    isRed = false
                }
                else {
                    backgroundColor.setBackgroundColor(Color.argb(255, 255, 0, 0))
                    isRed = true
                }


            }
            override fun onFinish() {
                startSelfDestructTimer3()
            }
        }
        uselessTimer.start()
    }

    private fun startSelfDestructTimer3() {

        val uselessTimer = object : CountDownTimer(3000, 200) {
            // var lastFlashTime = 10000L
            var isRed = false

            override fun onTick(millisUntilFinished: Long) {

                buttonSelfDestruct.text = (1 + millisUntilFinished/1000).toString()

                if (isRed) {
                    backgroundColor.setBackgroundColor(Color.argb(255, 255, 255, 255))
                    isRed = false
                }
                else {
                    backgroundColor.setBackgroundColor(Color.argb(255, 255, 0, 0))
                    isRed = true
                }


            }
            override fun onFinish() {
                finish()
            }
        }
        uselessTimer.start()
    }

    private fun startLookBusyTimer() {
        val lookBusyTimer = object : CountDownTimer(10000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                progressBarLookBusy.setProgress((100 - millisUntilFinished/100).toInt())
                textLookBusy.text = "${(100 - millisUntilFinished/100).toInt()}" + "/100"

            }
            override fun onFinish() {
                groupMainUI.visibility = View.VISIBLE
                progressBarLookBusy.visibility = View.INVISIBLE
                textLookBusy.visibility = View.INVISIBLE
                textDownloading.visibility = View.INVISIBLE
            }

        }
        lookBusyTimer.start()
    }

}

/*

Switch: Useless
Button: Self-Destruct
Button: Look busy

chain useless switch and destruct button vertically, center horizontally
look busy button centered horizontally, connected to the bottom of the screen

 */