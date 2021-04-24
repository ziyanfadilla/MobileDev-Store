package ziyan.fadila.utsmobile.presenters

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ziyan.fadila.utsmobile.contracts.BarangActivityContract
import ziyan.fadila.utsmobile.models.Barang
import ziyan.fadila.utsmobile.services.Api
import ziyan.fadila.utsmobile.services.WrappedListResponse

class BarangActivityPresenter(v: BarangActivityContract.View?):BarangActivityContract.Interaction{
    private var view : BarangActivityContract.View? = v
    private  var api = Api.instance()
    override fun allUser(token: String) {
        val request = api.get(token)
        request.enqueue(object : Callback<WrappedListResponse<Barang>> {
            override fun onFailure(call: Call<WrappedListResponse<Barang>>, t: Throwable) {
                println("Log: ${t.message} ")
                view?.toast("Cannot connect to server")
            }
            override fun onResponse(
                    call: Call<WrappedListResponse<Barang>>,
                    response: Response<WrappedListResponse<Barang>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status){
                        view?.attachToRecycle(body.data)
                    }else{
                        view?.toast("Something went wrong, try again later")
                    }
                }
            }

        })
    }

    override fun destroy() {
        view = null
    }
}