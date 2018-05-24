package sudtechnologies.kotlinexample.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import sudtechnologies.kotlinexample.R
import sudtechnologies.kotlinexample.util.UtilCore

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private var imageBack: ImageView? = null
    private var buttonRegister: Button? = null
    private var editName: EditText? = null
    private var editLastName: EditText? = null
    private var editPhone: EditText? = null
    private var editEmail: EditText? = null
    private var editPassword: EditText? = null
    private var editConfirmPassword: EditText? = null

    private var stateField = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imageBack = findViewById(R.id.imageBack)
        buttonRegister = findViewById(R.id.buttonRegister)
        editName = findViewById(R.id.editName)
        editLastName = findViewById(R.id.editLastName)
        editPhone = findViewById(R.id.editPhone)
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        editConfirmPassword = findViewById(R.id.editConfirmPassword)

        imageBack!!.setOnClickListener(this)
        buttonRegister!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageBack -> {
                finish()
            }
            R.id.buttonRegister -> {
                if (validateAllFields()) {
                    restAddUser()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    stateField = true
                }
            }
        }
    }

    private fun restAddUser() {
        val que = Volley.newRequestQueue(this@RegisterActivity)
        val stringRequest = object : StringRequest(Request.Method.POST, "http://192.168.1.23:8080//rest/v1/public/user",
                Response.Listener<String> { response ->
                    try {
                        Log.e("SUCCESS", response)

                        //aqui va  el finish y el startActivity
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                },
                Response.ErrorListener { error -> Log.e("ERROR", error?.message.toString()) }){
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["name"] = editName?.text.toString()
                params["lastName"] = editLastName?.text.toString()
                params["email"] = editEmail?.text.toString()
                params["password"] = editPassword?.text.toString()
                params["phone"] = editPhone?.text.toString()
                params["dni"] = "45878206"
                return params
            }
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }
        stringRequest.retryPolicy = DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        que.add(stringRequest)
    }

    private fun validateAllFields(): Boolean {
        if (!UtilCore.validateField(editName?.text.toString())) {
            editName?.error = getString(R.string.fieldName)
            stateField = false
        }
        if (!UtilCore.validateField(editLastName?.text.toString())) {
            editLastName?.error = getString(R.string.fieldLastname)
            stateField = false
        }
        if (!UtilCore.validateField(editPhone?.text.toString())) {
            editPhone?.error = getString(R.string.fieldPhone)
            stateField = false
        }
        if (!UtilCore.validatePhone(editPhone?.text.toString())) {
            editPhone?.error = getString(R.string.str_validate_phone)
            stateField = false
        }
        if (!UtilCore.isEmailValid(editEmail?.text.toString())) {
            editEmail?.error = getString(R.string.fieldEmail)
            stateField = false
        }
        if (!UtilCore.validateField(editPassword?.text.toString())) {
            editPassword?.error = getString(R.string.fieldPassword)
            stateField = false
        }
        if (!UtilCore.validateField(editConfirmPassword?.text.toString())) {
            editConfirmPassword?.error = getString(R.string.fieldConfirmPassword)
            stateField = false
        }
        if (!UtilCore.confirmPassword(editPassword?.text.toString(), editConfirmPassword?.text.toString())) {
            editConfirmPassword?.error = getString(R.string.fieldConfirmPassword)
            stateField = false
        }
        return stateField
    }
}
