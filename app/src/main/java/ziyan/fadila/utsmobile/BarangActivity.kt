package ziyan.fadila.utsmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsmobile.R
import kotlinx.android.synthetic.main.activity_barang.*
import ziyan.fadila.utsmobile.adapter.BarangAdapter
import ziyan.fadila.utsmobile.contracts.BarangActivityContract
import ziyan.fadila.utsmobile.models.Barang
import ziyan.fadila.utsmobile.presenters.BarangActivityPresenter
import ziyan.fadila.utsmobile.utils.Utils

class BarangActivity : AppCompatActivity(), BarangActivityContract.View {
    private var presenter = BarangActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barang)
    }
    override fun onResume() {
        super.onResume()
        getData()
        checkIsLoggedIn()
        toCreateActivity()
    }

    private fun toCreateActivity(){
        fab.setOnClickListener {
            startActivity(Intent(this, CreateBarangActivity::class.java))
        }
    }

    private fun checkIsLoggedIn(){
        val token = Utils.getToken(this)
        if (token == null || token.equals("UNDEFINED")){
            startActivity(Intent(this,LoginActivity::class.java)).also { finish() }
        }
    }

    private fun getData(){
        Utils.getToken(this)?.let { presenter.allUser(it) }
    }

    override fun attachToRecycle(barang: List<Barang>) {
        rvBarang.apply {
            layoutManager = LinearLayoutManager(this@BarangActivity)
            adapter = BarangAdapter(barang,this@BarangActivity)
        }
    }

    override fun toast(message: String?) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {

    }

    override fun notConnect(message: String?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}