package ziyan.fadila.utsmobile.presenters

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ziyan.fadila.utsmobile.contracts.BarangActivityContract
import ziyan.fadila.utsmobile.models.Barang
import ziyan.fadila.utsmobile.services.Api
import ziyan.fadila.utsmobile.services.WrappedResponse

class CreateActivityPresenter(v: BarangActivityContract.ViewCreate):BarangActivityContract.InteractionPost{
    private var view: BarangActivityContract.ViewCreate?=v
    private var api = Api.instance()
    override fun validate(name: String, location: String, description: String): Boolean {
        return true
    }

    override fun save(token: String, name: String, location: String, description: String) {
        api.createData(token, name, location, description).enqueue(object :
            Callback<WrappedResponse<Barang>> {
            override fun onFailure(call: Call<WrappedResponse<Barang>>, t: Throwable) {
                view?.toast("koneksi tidak bisa")
            }
            override fun onResponse(
                call: Call<WrappedResponse<Barang>>,
                response: Response<WrappedResponse<Barang>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status){
                        println("body"+body)
                        view?.success("berhasil")
                    }
                }else{
                    view?.toast("ada yang tidak beres")
                }
                view?.isLoading(false)
            }

        })
    }
    override fun destroy() {
        view = null
    }
}