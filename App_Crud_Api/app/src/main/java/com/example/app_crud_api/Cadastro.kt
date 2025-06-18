package com.example.app_crud_api

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Cadastro : AppCompatActivity() {
    private var userId: Int = -1
    private var isEditMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        findViewById<Button>(R.id.btCancelar).setOnClickListener { finish() }
        findViewById<Button>(R.id.btCancelar2).setOnClickListener { finish() }
        findViewById<Button>(R.id.btDeletar).setOnClickListener { deleteData()
            finish()
        }
        findViewById<Button>(R.id.btAlterar).setOnClickListener { upadateData()
        finish()
        }

        findViewById<Button>(R.id.btSalvar).setOnClickListener { postData() }

        userId = intent.getIntExtra("userId", -1)
        isEditMode = userId != -1

        if (isEditMode) {
            loadUserData()
        }
    }

    private fun loadUserData() {
        val retrofit = RetrofitUtils.getRetrofitInstance("https://684ba735ed2578be881bffaf.mockapi.io")
        val endpoint = retrofit.create(EndPoint::class.java)
        val contexto = this

        endpoint.getPessoaId(userId).enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                val user = response.body()
                if (user != null) {
                    findViewById<EditText>(R.id.etNome).setText(user.name)
                    findViewById<EditText>(R.id.etCidade).setText(user.cidade)
                    findViewById<EditText>(R.id.etPais).setText(user.pais)
                    findViewById<LinearLayout>(R.id.lyBtsPadrao).visibility = LinearLayout.GONE
                    findViewById<LinearLayout>(R.id.lyBtsAlterar).visibility = LinearLayout.VISIBLE
                    findViewById<TextView>(R.id.tvTitle).text = "Alterar Usuário"
                } else {
                    Toast.makeText(contexto, "Erro ao carregar dados do usuário", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(contexto, "Deu Erro na Api", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun postData(){

        if (!validaCampos())
        {
            return
        }

        val retrofit = RetrofitUtils.getRetrofitInstance("https://684ba735ed2578be881bffaf.mockapi.io")
        val endpoint = retrofit.create(EndPoint::class.java)

        val etNome = findViewById<EditText>(R.id.etNome).text.toString()
        val etCidade = findViewById<EditText>(R.id.etCidade).text.toString()
        val etPais = findViewById<EditText>(R.id.etPais).text.toString()

        var user = UserModel()
        user.name = etNome
        user.cidade = etCidade
        user.pais = etPais

        val contexto = this

        endpoint.post(user).enqueue(object : Callback<UserModel> {
            override fun onResponse(
                call: Call<UserModel>,
                response: Response<UserModel>
            ){
                if (response.isSuccessful) {
                    Toast.makeText(contexto, "Usuário Cadastrado Com Sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }else{
                    Toast.makeText(contexto, "Ocorreu um Erro ao Cadastrar o Usuário!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            override fun onFailure(call: Call<UserModel>, t: Throwable){
                Toast.makeText(contexto, "Deu Erro na Api", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun deleteData(){

        if (userId == -1) {
            Toast.makeText(this, "ID do usuário inválido para exclusão", Toast.LENGTH_SHORT).show()
            return
        }

        val retrofit = RetrofitUtils.getRetrofitInstance("https://684ba735ed2578be881bffaf.mockapi.io")
        val endpoint = retrofit.create(EndPoint::class.java)
        val contexto = this

        endpoint.delete(userId).enqueue(object : Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(contexto, "Usuário Excluido Com Sucesso!", Toast.LENGTH_SHORT)
                        .show()
                }else{
                    Toast.makeText(contexto, "Ocorreu um Erro ao Excluir o Usuário!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(contexto, "Deu Erro na Api", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun upadateData(){
        if (userId == -1) {
            Toast.makeText(this, "ID do usuário inválido para exclusão", Toast.LENGTH_SHORT).show()
            return
        }
        val retrofit = RetrofitUtils.getRetrofitInstance("https://684ba735ed2578be881bffaf.mockapi.io")
        val endpoint = retrofit.create(EndPoint::class.java)
        val contexto = this

        val etNome = findViewById<EditText>(R.id.etNome).text.toString()
        val etCidade = findViewById<EditText>(R.id.etCidade).text.toString()
        val etPais = findViewById<EditText>(R.id.etPais).text.toString()

        var user = UserModel()
        user.name = etNome
        user.cidade = etCidade
        user.pais = etPais

        endpoint.update(userId, user).enqueue(object : Callback<UserModel> {
            override fun onResponse(
                call: Call<UserModel>,
                response: Response<UserModel>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(contexto, "Usuário Atualizado Com Sucesso!", Toast.LENGTH_SHORT)
                        .show()
                }else{
                    Toast.makeText(contexto, "Ocorreu um Erro ao Atualizar o Usuário!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(contexto, "Deu Erro na Api", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun validaCampos(): Boolean {
        val etNome = findViewById<EditText>(R.id.etNome)
        val etCidade = findViewById<EditText>(R.id.etCidade)
        val etPais = findViewById<EditText>(R.id.etPais)

        if (etNome.text.toString() == "" ||
            etCidade.text.toString() == "" ||
            etPais.text.toString() == "") {
            Toast.makeText(this, "Preencha todos os campos Obrigatorios!", Toast.LENGTH_SHORT).show()
            return false
        } else { return true }
    }

}