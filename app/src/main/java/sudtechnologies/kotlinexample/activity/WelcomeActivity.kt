package sudtechnologies.kotlinexample.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import sudtechnologies.kotlinexample.R

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {
    private var buttonInto: Button? = null
    private var buttonRegister: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        buttonInto = findViewById(R.id.buttonInto)
        buttonRegister = findViewById(R.id.buttonRegister)

        buttonInto!!.setOnClickListener(this)
        buttonRegister!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonInto -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonRegister -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
