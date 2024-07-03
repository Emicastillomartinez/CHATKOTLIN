package com.example.chat_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chat_kotlin.Fragmentos.FragmentChats
import com.example.chat_kotlin.Fragmentos.FragmentPerfil
import com.example.chat_kotlin.Fragmentos.FragmentUsuarios
import com.example.chat_kotlin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser == null ){
            irOpcionesLogin()
        }
        // ver fragmento por defecto al empezar
        verFragmentoPerfil()

        binding.bottomNV.setOnItemSelectedListener {
            item->
            when(item.itemId){
                R.id.item_perfil ->{
                    //visualizar el fragmento Perfil
                    verFragmentoPerfil()
                    true
                }
                R.id.item_usuarios->{
                    // visualizar el fragmento Usuarios
                    verFragmentoUsuarios()
                    true
                }
                R.id.item_chats->{
                    //visualizar el fragmento chats
                    verFragmentoChats()
                    true
                }
                else->{
                    false
                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun irOpcionesLogin() {

        startActivity(Intent(applicationContext,OpcionesLoginActivity::class.java))
        finishAffinity()

    }

    private fun verFragmentoPerfil(){

        binding.tvTitulo.text = "Perfil"

        val fragment= FragmentPerfil()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentoFL.id,fragment,"Fragment Perfil")
        fragmentTransaction.commit()

    }

    private fun verFragmentoUsuarios(){
        binding.tvTitulo.text ="Usuarios"
        val fragment= FragmentUsuarios()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentoFL.id,fragment,"Fragment Usuarios")
        fragmentTransaction.commit()

    }
    private fun verFragmentoChats(){
        binding.tvTitulo.text ="Chats"
        val fragment= FragmentChats()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentoFL.id,fragment,"Fragment Chats")
        fragmentTransaction.commit()

    }





}