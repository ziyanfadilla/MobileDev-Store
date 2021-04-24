package ziyan.fadila.utsmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.utsmobile.R
import kotlinx.android.synthetic.main.activity_main.*
import ziyan.fadila.utsmobile.utils.Utils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doLogout()
        toBarangActivity()
    }

    private fun doLogout(){
        cardLogout.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java)).also {
                Utils.clearToken(this)
                finish()
            }
        }
    }

    private fun toBarangActivity(){
        cardBarang.setOnClickListener {
            startActivity(Intent(this, BarangActivity::class.java))
        }
    }
}