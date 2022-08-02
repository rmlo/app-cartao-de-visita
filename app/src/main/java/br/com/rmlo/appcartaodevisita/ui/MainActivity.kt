package br.com.rmlo.appcartaodevisita.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.rmlo.appcartaodevisita.App
import br.com.rmlo.appcartaodevisita.databinding.ActivityMainBinding
import br.com.rmlo.appcartaodevisita.util.image


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    //viewmodel respondendo nossa view!
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy {businessCardAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCartoes.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener() {
        binding.fabAddCartao.setOnClickListener {
            val intent = Intent(this, AddCartaoDeVisitaActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = { card ->
            image.share(this,card)
        }
    }

    //metodo para ficar notificando as alterações dos cartoes de visita
    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this) { businessCard ->
            adapter.submitList(businessCard)
        }
    }

}