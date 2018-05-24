package sudtechnologies.kotlinexample.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import sudtechnologies.kotlinexample.R
import sudtechnologies.kotlinexample.util.UtilCore

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var editUser: EditText? = null
    private var editPassword: EditText? = null
    private var buttonLogin: Button? = null
    private var stateField = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editUser = findViewById(R.id.editUser)
        editPassword = findViewById(R.id.editPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonLogin!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonLogin -> {
                if (!UtilCore.isEmailValid(editUser?.text.toString())) {
                    editUser?.error = getString(R.string.str_unknow_rest_error)
                    stateField = false
                }
                if (!UtilCore.validateField(editPassword?.text.toString())) {
                    editPassword?.error = getString(R.string.search_menu_title)
                    stateField = false
                }
                if (stateField) {
                    val que = Volley.newRequestQueue(this@LoginActivity)
                    val stringRequest = object : StringRequest(Request.Method.POST, "http://192.168.1.23:8080/oauth/token",
                            Response.Listener<String> { response ->
                                try {
                                    val obj = JSONObject(response)
                                    Log.e("SUCCESS", response)
                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                }

                            },
                            object : Response.ErrorListener{
                                override fun onErrorResponse(error: VolleyError?) {
                                    Log.e("ERROR", error?.message.toString())
                                }
                            }){
                        @Throws(AuthFailureError::class)
                        override fun getParams(): Map<String, String> {
                            val params = HashMap<String, String>()
                            params.put("username", editUser?.text.toString())
                            params.put("password", editPassword?.text.toString())
                            params.put("grant_type", "password")
                            return params
                        }
                        @Throws(AuthFailureError::class)
                        override fun getHeaders(): Map<String, String> {
                            val headers = HashMap<String, String>()
                            headers["Content-Type"] = "application/x-www-form-urlencoded"
                            headers["Authorization"] = "Basic YW5kcm9pZF9jbGllbnQ6MTIzNDU2"
                            return headers
                        }
                    }
                    stringRequest.retryPolicy = DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
                    //req.setRetryPolicy(new DefaultRetryPolicy(RestConstant.TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    //DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    que.add(stringRequest)

                } else {
                    stateField = true
                }

            }
        }
    }
}

