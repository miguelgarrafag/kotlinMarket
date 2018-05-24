package sudtechnologies.kotlinexample.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import sudtechnologies.kotlinexample.R

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    private var relativeContent: RelativeLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

    }

    override fun onClick(v: View?) {

    }
}
