package ziyan.fadila.utsmobile.utils

import android.content.Context

class Utils {
    companion object{
        var API_ENDPOINT = "https://rest-mobile.herokuapp.com/"

        fun getToken(context: Context) : String? {
            val token = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            return token?.getString("TOKEN",null)
        }

        fun setToken(context: Context, token: String, user_id: String) {
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            pref.edit().apply(){
                putString("TOKEN", token)
                putString("USER_ID", user_id)
                apply()
            }
        }
        fun clearToken(context: Context){
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            pref.edit().clear().apply()
        }
        fun isValidPassword(password: String) = password != null;
    }
}