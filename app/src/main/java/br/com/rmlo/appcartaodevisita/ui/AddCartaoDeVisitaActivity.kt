package br.com.rmlo.appcartaodevisita.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.rmlo.appcartaodevisita.App
import br.com.rmlo.appcartaodevisita.R
import br.com.rmlo.appcartaodevisita.data.BusinessCard
import br.com.rmlo.appcartaodevisita.databinding.ActivityAddCartaoDeVisitaBinding

class AddCartaoDeVisitaActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddCartaoDeVisitaBinding.inflate(layoutInflater) }

    //viewmodel respondendo nossa view!
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListener()
    }

    private fun insertListener(){
        binding.ibBtnClose.setOnClickListener {
        finish()
        }

        binding.btnConfirmar.setOnClickListener {
        val businesscard = BusinessCard(
            nome = binding.tilNome.editText?.text.toString(),
            empresa = binding.tilEmpresa.editText?.text.toString(),
            telefone = binding.tilTelefone.editText?.text.toString(),
            email = binding.tilEmail.editText?.text.toString(),
            fundoPersonalizado = binding.tilCor.editText?.text.toString()
            )
            mainViewModel.insert(businesscard)
            Toast.makeText(this, R.string.label_show_sucess, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}