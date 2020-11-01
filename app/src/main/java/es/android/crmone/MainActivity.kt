package es.android.crmone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import es.android.crmone.models.User
import es.android.crmone.repository.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var tvUsers: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvUsers = findViewById(R.id.tvUsers)
    }

    override fun onResume() {
        RetrofitService.endpoints.getPeople().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful){
                    val body = response.body()
                    if (body!=null) {
                        val listaUsuarios = body
                        Log.d("AAAAAABBBBBCCCC", listaUsuarios.toString())
                        val builder = StringBuilder("")
                        for (usuario in listaUsuarios) {
                            builder.append(usuario.title)
                            builder.append("\n")
                        }
                        tvUsers.text = builder.toString()

                    } else {
                        //mostrar mensaje de error
                    }
                } else {
                    val httpCode = response.code()
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }
        })
        super.onResume()
    }
}