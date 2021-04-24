package ziyan.fadila.utsmobile.contracts

interface LoginActivityContract {
    interface View{
        fun toast(message: String)
        fun success(token: String, user_id: String)
        fun isLoading(state : Boolean)
        fun isError(err: String?)
        fun passwordError(err: String?)
    }

    interface Interactions{
        fun validate(id: String, password: String) : Boolean
        fun login(email: String, password: String)
        fun destroy()
    }
}