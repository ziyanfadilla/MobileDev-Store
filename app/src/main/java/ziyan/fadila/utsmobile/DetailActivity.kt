package ziyan.fadila.utsmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.utsmobile.R
import kotlinx.android.synthetic.main.activity_detail.*
import ziyan.fadila.utsmobile.contracts.BarangActivityContract
import ziyan.fadila.utsmobile.models.Barang
import ziyan.fadila.utsmobile.presenters.DeleteActivityPresenter
import ziyan.fadila.utsmobile.utils.Utils

class DetailActivity : AppCompatActivity(), BarangActivityContract.ViewDelete {
    private var presenter = DeleteActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter = DeleteActivityPresenter(this)
        User()
        Delete()
        editData()
    }

    private  fun editData(){
        btnEdit.setOnClickListener {
            val user = getUser()
            val intent = Intent(this,UpdateActivity::class.java)
            println("user"+user)
            intent.putExtra("ID_USER", user?.id)
            intent.putExtra("NAME", user?.name)
            intent.putExtra("LOCATION", user?.location)
            intent.putExtra("DESCRIPTION", user?.description)

            startActivity(intent)
        }
    }
    private fun User(){
        val user = getUser()
        val nama = findViewById<TextView>(R.id.namabarang)
        val lokasi = findViewById<TextView>(R.id.location)
        val keterangan = findViewById<TextView>(R.id.deskripsi)

        user?.let {
            nama.text = it.name
            lokasi.text = it.location
            keterangan.text = it.description
        }
    }

    private fun Delete(){
        btnDelete.setOnClickListener {
            val data = getUser();
            val id = data?.id.toString().toInt()
            val token = Utils.getToken(this)
            token?.let { it1 ->presenter.delete(id, it1) }
        }
    }
    private fun getUser()= intent.getParcelableExtra<Barang>("barang")

    override fun success(message: String?) {
        Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun isLoading(state: Boolean) {
        btnDelete.isEnabled = !state
    }
}