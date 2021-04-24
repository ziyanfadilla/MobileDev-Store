package ziyan.fadila.utsmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.utsmobile.R
import kotlinx.android.synthetic.main.activity_update.*
import ziyan.fadila.utsmobile.contracts.BarangActivityContract
import ziyan.fadila.utsmobile.presenters.UpdateActivityPresenter
import ziyan.fadila.utsmobile.utils.Utils

class UpdateActivity : AppCompatActivity(), BarangActivityContract.ViewEdit {
    private var presenter = UpdateActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        presenter = UpdateActivityPresenter(this)
        etIdBarang.setText(intent.getStringExtra("ID_USER"))
        etName.setText(intent.getStringExtra("NAME"))
        etLocation.setText(intent.getStringExtra("LOCATION"))
        etDescription.setText(intent.getStringExtra("DESCRIPTION"))
        Update()

    }
    private fun Update(){
        btnUpdate.setOnClickListener {
            val token = Utils.getToken(this)
            val id = etIdBarang.text.toString().toInt()
            val name = etName.text.toString().trim()
            val location = etLocation.text.toString().trim()
            val description = etDescription.text.toString().trim();

            if(name.isNotEmpty() && location.isNotEmpty() && description.isNotEmpty()){
                token?.let { it1 -> presenter.update(id, it1, name, location, description) }
            }else{
                toast("Isi semua form")
            }
        }
    }

    override fun success(message: String?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun isLoading(state: Boolean) {
        btnUpdate.isEnabled = !state
    }
}