package com.example.app_crud_api

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        findViewById<Button>(R.id.btCancelar).setOnClickListener { finish() }

        findViewById<Button>(R.id.btSalvar).setOnClickListener { postData()
            finish()}
    }

    fun postData(){
        val retrofit = RetrofitUtils.getRetrofitInstance("https://684ba735ed2578be881bffaf.mockapi.io")
        val endpoint = retrofit.create(EndPoint::class.java)

        val etNome = findViewById<EditText>(R.id.etNome).text.toString()
        val etCidade = findViewById<EditText>(R.id.etCidade).text.toString()
        val etPais = findViewById<EditText>(R.id.etPais).text.toString()

        var user = UserModel()


        val contexto = this

        endpoint.post(user).enqueue(object : Callback<UserModel> {
            override fun onResponse(
                call: Call<UserModel>,
                response: Response<UserModel>
            ){
//                val pessoas = response.body()

                user.name = etNome
                user.cidade = etCidade
                user.pais = etPais
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable){
                Toast.makeText(contexto, "Deu Erro na Api", Toast.LENGTH_SHORT).show()
            }
        })
    }

}