package com.lab79.sligamer.multitouchgesturesexperiement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.os.Handler
import android.view.Menu
import android.view.MenuItem

/**
 * Created by Justin Freres on 4/10/2018.
 * Lab 7-9 Multi Touch Gesture Experiment
 * Plugin Support with kotlin_version = '1.2.40'
 */

class MainActivity : AppCompatActivity() {


    // DECLARE VARIABLES
    private lateinit var gameView: GameView
    private lateinit var mThread: Thread

    companion object{
        const val DELAY = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // INIT GAMEVIEW
        gameView = GameView(this)

        // SET GAMEVIEW AS DEFAULT CONTENT VIEW
        setContentView(gameView)

        // INIT IN THREAD
        mThread = Thread(actionRun)
    }

    override fun onResume() {
        mThread.start()
        super.onResume()
    }

    override fun onPause() {
        finish()
        super.onPause()
    }
    override fun onDestroy() {
        finish()
        super.onDestroy()
    }

    private val actionRun: Runnable = object: Runnable {

        override fun run() {
            try {
                while (true) {
                    Thread.sleep(DELAY.toLong())
                    threadHandler.sendEmptyMessage(0)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private val threadHandler: Handler = object: Handler() {
        override fun handleMessage(msg: Message?) {
            gameView.update()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId
        if(id == R.string.action_settings){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}