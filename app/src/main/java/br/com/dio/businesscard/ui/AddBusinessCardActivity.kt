package br.com.dio.businesscard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.businesscard.App
import br.com.dio.businesscard.R
import br.com.dio.businesscard.data.BusinessCard
import br.com.dio.businesscard.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun setColorCard(color: String): String{
        var colorResult = ""

        when (true){
            color.uppercase().equals("PRETO") || color.uppercase().equals("BLACK")-> colorResult = "#000000"
            color.uppercase().equals("ROXO") || color.uppercase().equals("PURPLE")-> colorResult = "#4B0082"
            color.uppercase().equals("AZUL") || color.uppercase().equals("BLUE")-> colorResult = "#0000FF"
            color.uppercase().equals("ROSA") || color.uppercase().equals("PINK")-> colorResult = "#FF1493"
            color.uppercase().equals("VERMELHO") || color.uppercase().equals("RED")-> colorResult = "#FF0000"
            color.uppercase().equals("LARANJA") || color.uppercase().equals("ORANGE")-> colorResult = "#FFA500"
            color.uppercase().equals("AMARELO") || color.uppercase().equals("YELLOW")-> colorResult = "#FFFF00"
            color.uppercase().equals("VERDE") || color.uppercase().equals("GREEN")-> colorResult = "#008000"
            color.uppercase().equals("MARROM") || color.uppercase().equals("BROWN")-> colorResult = "#8B4513"
            else -> colorResult = "#FFFFFF"
        }
        return colorResult
    }

    private fun insertListeners() {
        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = setColorCard(binding.tilCor.editText?.text.toString())
            )

            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}
