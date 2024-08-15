package com.sahil.shah

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.R
import com.google.android.material.snackbar.Snackbar
import com.sahil.shah.databinding.ActivityMainBinding
import com.sahil.shah.entity.CompanyStock
import com.sahil.shah.viewmodel.CompanyStockViewModel
import com.sahil.shah.viewmodel.CompanyStockViewModelFactory
import kotlinx.coroutines.launch


// Student Number - 300727537

class SahilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val companyStockViewModel:CompanyStockViewModel by viewModels{
        CompanyStockViewModelFactory((application as MyApplication).repository)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInsert.setOnClickListener {
            lifecycleScope.launch {

                //Inserting company stock information to the database
                companyStockViewModel.insertCompanyStock(CompanyStock("Google", 162.77, 160.44))
                companyStockViewModel.insertCompanyStock(CompanyStock("Amazon", 176.16, 170.4))
                companyStockViewModel.insertCompanyStock(CompanyStock("Samsung Electronics", 44.44, 50.4))

                // Storing the company stock information in a variable
                val company1:CompanyStock? = companyStockViewModel.getPriceByStockName("Google")
                val company2:CompanyStock? = companyStockViewModel.getPriceByStockName("Amazon")
                val company3:CompanyStock? = companyStockViewModel.getPriceByStockName("Samsung Electronics")

                //Hardcoding to populate radio button text
                binding.rbCompany1.setText(company1?.companyName.toString())
                binding.rbCompany2.setText(company2?.companyName.toString())
                binding.rbCompany3.setText(company3?.companyName.toString())
            }
        }

        binding.btnDisplay.setOnClickListener {
            lifecycleScope.launch {

                // Retrieving the company name from the selection radio button
                val checkedRbId = binding.rgCompanies.checkedRadioButtonId
                val companyName = findViewById<RadioButton>(checkedRbId).text.toString()

                //Retrieving company stock information from the database
                val company:CompanyStock? = companyStockViewModel.getPriceByStockName(companyName)

                //Display information in the text view
                val tvDisplay = binding.tvDisplay
                tvDisplay.text = "Stock Info received:\nCompany Name: ${company?.companyName}\nOpening Price: ${company?.openingPrice}\nClosing Price: ${company?.closingPrice}"

                //Custom Toast using snackbar
                val snackbar = Snackbar.make(
                    binding.root,
                    "Stock Info received:\nCompany Name: ${company?.companyName}\nOpening Price: ${company?.openingPrice}\nClosing Price: ${company?.closingPrice}",
                    Snackbar.LENGTH_LONG
                )
                val snackbarView = snackbar.view
                val textView = snackbarView.findViewById<View>(R.id.snackbar_text) as TextView
                textView.maxLines = 5
                snackbar.show()
            }

        }

    }
}

