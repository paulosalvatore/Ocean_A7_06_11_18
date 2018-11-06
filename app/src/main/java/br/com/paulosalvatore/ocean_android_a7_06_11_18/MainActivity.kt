package br.com.paulosalvatore.ocean_a7_06_11_18

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.paulosalvatore.ocean_a7_06_11_18.db.DatabaseManager
import br.com.paulosalvatore.ocean_a7_06_11_18.db.Posicao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val db = DatabaseManager.getInstancia(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db.limparPosicoes()

        exibirPosicoes()

        val posicao1 = Posicao(10.0, 11.0, "06/11/2018 - 20h57")
        db.criarPosicao(posicao1)
        val posicao2 = Posicao(11.0, 12.0, "06/11/2018 - 20h57")
        db.criarPosicao(posicao2)
        val posicao3 = Posicao(12.0, 13.0, "06/11/2018 - 20h57")
        db.criarPosicao(posicao3)

        exibirPosicoes()

        posicao1.longitude = 20.0
        posicao1.latitude = 21.0
        posicao1.dataHora = "06/11/2018 - 21h45"
        db.editarPosicao(posicao1)

        exibirPosicoes()

        db.removerPosicao(posicao1)

        exibirPosicoes()
    }

    private fun exibirPosicoes() {
        val posicoes = db.obterPosicoes()
        textView.text = ""
        posicoes.forEach { pos ->
            textView.append("${pos.id} - ${pos.latitude} - ${pos.longitude} - ${pos.dataHora}\n")
        }
    }
}
