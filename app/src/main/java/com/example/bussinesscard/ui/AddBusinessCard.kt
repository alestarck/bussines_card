package com.example.bussinesscard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bussinesscard.data.BusinessCard
import com.example.bussinesscard.data.BusinessCardApplication
import com.example.bussinesscard.databinding.ActivityAddBusinessCardBinding
import kotlinx.android.synthetic.main.activity_add_business_card.*

class AddBusinessCard : AppCompatActivity() {
    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.close.setOnClickListener{
            finish()
        }
        supportActionBar?.hide()// deixar a tela cheia
        binding.confirmar.setOnClickListener{
            val card = BusinessCard(
                nome = "${label_nome.text.toString()}",
                empresa = "${label_empresa.text.toString()}",
                telefone = "${label_telefone.text.toString()}",
                email = "${label_email.text.toString()}",
                fundoPersonalisado = "${label_cor.text.toString()}")
            if (label_nome.text.toString().equals("")){
                finish()
            }else{
                // adcionando no banco de dado
                BusinessCardApplication.instance.helperDB?.salvarCard(card)
            }
            Toast.makeText(this,"Novo cartão criado com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        }

            binding.apagar.setOnClickListener{
                val card = BusinessCard(
                    nome = "${label_nome.text.toString()}",
                    empresa = "${label_empresa.text.toString()}",
                    telefone = "${label_telefone.text.toString()}",
                    email = "${label_email.text.toString()}",
                    fundoPersonalisado = "${label_cor.text.toString()}")
                BusinessCardApplication.instance.helperDB?.apagarCard(card)
            }




    }
}