package com.andreina.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        // ----------------------------------------------------------------------------- //
        //Valores Números e Ponto
        txt_ponto.setOnClickListener{AcrescentarExmpressão(".", true)}
        txt_zero.setOnClickListener{AcrescentarExmpressão("0", true)}
        txt_um.setOnClickListener{AcrescentarExmpressão("1", true)}
        txt_dois.setOnClickListener{AcrescentarExmpressão("2", true)}
        txt_tres.setOnClickListener{AcrescentarExmpressão("3", true)}
        txt_quatro.setOnClickListener{AcrescentarExmpressão("4", true)}
        txt_cinco.setOnClickListener{AcrescentarExmpressão("5", true)}
        txt_seis.setOnClickListener{AcrescentarExmpressão("6", true)}
        txt_sete.setOnClickListener{AcrescentarExmpressão("7", true)}
        txt_oito.setOnClickListener{AcrescentarExmpressão("8", true)}
        txt_nove.setOnClickListener{AcrescentarExmpressão("9", true)}

        // ----------------------------------------------------------------------------- //
        //Operadores Adição / Subtração / Multiplicação / Divisão
        txt_adicao.setOnClickListener{AcrescentarExmpressão("+", false)}
        txt_subtracao.setOnClickListener{AcrescentarExmpressão("-", false)}
        txt_multiplicacao.setOnClickListener{AcrescentarExmpressão("x", false)}
        txt_divisao.setOnClickListener{AcrescentarExmpressão("/", false)}

        // ----------------------------------------------------------------------------- //
        //Ações calculadora: Limpar / Apagar / Igual
        txt_limpar.setOnClickListener{
            txt_expressao.text = ""
            txt_resultado.text = ""
        }

        txt_apagar.setOnClickListener{
            val expressao = txt_expressao.text.toString()
            if (expressao.isNotBlank()){
                txt_expressao.text = expressao.substring(0, expressao.length -1)
            }
            txt_resultado.text = ""
        }

        txt_igual.setOnClickListener {
            try{
                val expressao = ExpressionBuilder(txt_expressao.text.toString()).build()
                val resultado = expressao.evaluate()
                val longResultado = resultado.toLong()

                if (resultado == longResultado.toDouble()) {
                    txt_resultado.text = longResultado.toString()
                }else{
                    txt_resultado.text = resultado.toString()
                }

            }catch (e: Exception){
                txt_resultado.text = "Error"
            }
        }
        // ----------------------------------------------------------------------------- //

    }

    fun AcrescentarExmpressão(string: String, limpar_dados: Boolean){

        // Resultado Não Vazio = Vazio
        if(txt_resultado.text.isNotEmpty()){
            txt_expressao.text = ""
        }

        // True = Expressão Append String / Resultado = Vazio
        // False = Expressão Resultado Append String  / Resultado = Vazio
        if(limpar_dados){
            txt_resultado.text = ""
            txt_expressao.append(string)
        }else{
            txt_expressao.append(txt_resultado.text)
            txt_expressao.append(string)
            txt_resultado.text = ""
        }

    }
}