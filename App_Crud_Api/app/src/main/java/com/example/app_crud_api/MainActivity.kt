package com.example.app_crud_api

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btIncluir).setOnClickListener {
            val intent = Intent(this, Cadastro::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        loadDatas()

        var adapter = UserAdapter(this, ArrayList<UserModel>())

        var rvUser = findViewById<RecyclerView>(R.id.rvPessoas)
        rvUser.adapter = adapter



    }

    fun loadDatas(){
        val retrofit = RetrofitUtils.getRetrofitInstance("https://684ba735ed2578be881bffaf.mockapi.io")
        val endpoint = retrofit.create(EndPoint::class.java)

        val contexto = this

        endpoint.get().enqueue(object : Callback<ArrayList<UserModel>>{
            override fun onResponse(
                call: Call<ArrayList<UserModel>>,
                response: Response<ArrayList<UserModel>>
            ){
              val pessoas = response.body()

              if(pessoas != null) {
                  showOnList(pessoas)
              } else {
                  Toast.makeText(contexto, "Não foi possível carregar os dados.", Toast.LENGTH_SHORT).show()
              }
            }

            override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable){
                Toast.makeText(contexto, "Deu Erro na Api", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun showOnList(lista: ArrayList<UserModel>){
        val recycler = findViewById<RecyclerView>(R.id.rvPessoas)

        val adapter = UserAdapter(this, lista)

        recycler.adapter = adapter
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
    }
}