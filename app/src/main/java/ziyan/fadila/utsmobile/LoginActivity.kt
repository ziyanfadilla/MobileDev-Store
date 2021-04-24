package ziyan.fadila.utsmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.utsmobile.R
import kotlinx.android.synthetic.main.activity_login.*
import ziyan.fadila.utsmobile.contracts.LoginActivityContract
import ziyan.fadila.utsmobile.presenters.LoginActivityPresenter
import ziyan.fadila.utsmobile.utils.Utils

class LoginActivity : AppCompatActivity(),LoginActivityContract.View {
    private var presenter = LoginActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        doLogin()
    }

    private fun doLogin(){
        btnLogin.setOnClickListener {
            val email = etId.text.toString().trim()
            val password = etPass.text.toString().trim()

            if (email.isNotEmpty()&& password.isNotEmpty()){
                presenter.login(email,password)
            }else{
                toast("isi semua form")
            }
        }
    }

    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun success(token: String, user_id: String) {
        Utils.setToken(this,token,user_id)
        startActivity(Intent(this,MainActivity::class.java))
            .also {
                finish()
            }
    }

    override fun isLoading(state: Boolean) {
        btnLogin.isEnabled=!state
    }

    override fun isError(err: String?) {
        inId.error=err
    }

    override fun passwordError(err: String?) {
        inPass.error=err
    }

    private fun isLoggedIn(){
        val token = Utils.getToken(this)
        if (token != null){
            startActivity(Intent(this,MainActivity::class.java)).also { finish() }
        }
    }
}