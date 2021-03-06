package org.d3if4080.assesment1_muhamaddaffa

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.DialogInterface
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import org.d3if4080.assesment1_muhammaddaffa.R
import org.d3if4080.assesment1_muhammaddaffa.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var selectedUnit: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        SwitchDark.setOnCheckedChangeListener { CompoundButton, isChecked ->
        when(isChecked) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


                }
                false ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }
    }
}


        val df = DecimalFormat("#.##")//Decimal formatter
        selectedUnit = "Fahrenheit"

        binding.selectType.setOnClickListener() {
            showAlertDialog()

        }

        binding.editInput.addTextChangedListener() {
            val resultText: String
            var inputVal = binding.editInput.text.toString()
            if (inputVal.isNotEmpty()) {
                var doubleInput = inputVal.toDouble()
                if(selectedUnit == "Fahrenheit"){
                    resultText = df.format((doubleInput - 32) * 5 / 9)
                    binding.textResultType.text = "Celsius"
                }else{
                    //(0°C × 9/5) + 32
                    resultText = df.format((doubleInput *9/5)+32)
                    binding.textResultType.text = "Fahrenheit"
                }

                binding.textResult.text = resultText
            }

        }

    }

    private fun showAlertDialog() {
        val alertDialog: Builder = Builder(this@MainActivity)
        alertDialog.setTitle("Select Unit") //Setting title for alertBox
        val items = arrayOf("Fahrenheit", "Celsius")
        val checkedItem = -1
        alertDialog.setSingleChoiceItems(items, checkedItem,
            DialogInterface.OnClickListener { dialog, which ->
                selectedUnit = items[which]
                binding.textType.setText(items[which])
            })
        alertDialog.setPositiveButton(
            android.R.string.ok,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}


