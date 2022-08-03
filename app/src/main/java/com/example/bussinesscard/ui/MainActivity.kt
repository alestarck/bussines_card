package com.example.bussinesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bussinesscard.data.BusinessCard
import com.example.bussinesscard.data.BusinessCardApplication
import com.example.bussinesscard.databinding.ActivityAddBusinessCardBinding
import com.example.bussinesscard.databinding.ActivityMainBinding
import com.example.bussinesscard.ui.MainActivity.Business.lista
import com.example.bussinesscard.util.BusinessAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //criando a lista de contatos
    object Business{
        var lista= mutableListOf<BusinessCard>()// lista criada para função geraLista
    }
    // abre visualização
    private var adapter: BusinessAdapter?=null// variárel criada do tipo da classe Adapter

    private fun onClickItemRecyclerView(index: Int){
        val chamar = Intent(this,ActivityAddBusinessCardBinding::class.java)
        chamar.putExtra("index", index)
        startActivity(chamar)
    }
    private fun setupListView(){
        rv_card.layoutManager = LinearLayoutManager(this)// nome da activity xml
        adapter = BusinessAdapter(this, lista) {onClickItemRecyclerView(it)}
        rv_card.adapter = adapter// a adapter é o nome da variavel
    }
    //fecha visualização

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListView()
        setTitle("Business")// nome que no superior do app
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)// barra de navegação de VOLTAR
        binding.adicionar.setOnClickListener{ adicionar() }
        buscar()


    }


    override fun onResume() {
        super.onResume()
        buscar()
    }


    private fun adicionar(){
        val chamar= Intent(this, AddBusinessCard::class.java )
        startActivity(chamar)
    }

    // inicialização de busca SELECT
    private fun buscar(){
        var dentro : List<BusinessCard> = mutableListOf()
        try {
            dentro= BusinessCardApplication.instance.helperDB?.buscarCard()?: mutableListOf()

        }catch (e: Exception){
            e.printStackTrace()
        }
        rv_card.layoutManager = LinearLayoutManager(this)// nome da activity xml
        adapter = BusinessAdapter(this,dentro) {onClickItemRecyclerView(it)}
        rv_card.adapter = adapter
        //
    }
    /*@SuppressLint("ResourceType")
    private fun editarCard(){
        val chamar = layoutInflater.inflate(R.layout.item_business_card,null,false)
        chamar.content.setOnClickListener {
            Toast.makeText(this, "funcionou",Toast.LENGTH_LONG).show()
            adicionar()
        }

    }*/

}