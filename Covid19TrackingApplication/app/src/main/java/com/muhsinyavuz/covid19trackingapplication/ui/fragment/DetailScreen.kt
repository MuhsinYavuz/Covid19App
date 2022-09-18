
package com.muhsinyavuz.covid19trackingapplication.ui.fragment

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.muhsinyavuz.covid19trackingapplication.R
import com.muhsinyavuz.covid19trackingapplication.databinding.FragmentDetailScreenBinding
import com.muhsinyavuz.covid19trackingapplication.databinding.FragmentMainScreenBinding
import com.muhsinyavuz.covid19trackingapplication.ui.viewmodel.DetailScreenViewModel
import com.muhsinyavuz.covid19trackingapplication.ui.viewmodel.MainScreenViewModel

class DetailScreen : Fragment() {
    private lateinit var binding: FragmentDetailScreenBinding

    var countryName : String?= null
    var continent_ : String?= null
    var totalTest_ : Int? =null
    var newDeath_ : String? =null
    var critical_ : Int? =null
    var activeCase_ : Int? =null
    private var recovered_: Int?=null
    var totalDeath_ : Int?=null
    var population_ :Int? = null
    var time_ : String?= null
    var popTestH_ : String? =null
    var casePop_ : String? =null
    var caseNew_ : String? =null
    var deathPop_ : String? =null

    private lateinit var viewModel: DetailScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailScreenViewModel::class.java]

        arguments?.let {
            countryName = DetailScreenArgs.fromBundle(it).name?.toUpperCase()
            continent_  = DetailScreenArgs.fromBundle(it).continent?.toUpperCase()
            totalTest_ = DetailScreenArgs.fromBundle(it).totalTest
            newDeath_  = DetailScreenArgs.fromBundle(it).newDeath
            critical_ = DetailScreenArgs.fromBundle(it).critical
            activeCase_ = DetailScreenArgs.fromBundle(it).activeCase
            recovered_ = DetailScreenArgs.fromBundle(it).recovered
            totalDeath_ = DetailScreenArgs.fromBundle(it).totalDeath
            population_ = DetailScreenArgs.fromBundle(it).population
            time_ = DetailScreenArgs.fromBundle(it).time
            popTestH_ = DetailScreenArgs.fromBundle(it).testPopH
            casePop_ = DetailScreenArgs.fromBundle(it).casePop
            caseNew_ = DetailScreenArgs.fromBundle(it).newCase
            deathPop_ = DetailScreenArgs.fromBundle(it).deathsPop
        }

        with(binding){
            name.text = countryName.orEmpty()
            continent.text = continent_.orEmpty()
            totalTest.text = getString(R.string.totalTest) +totalTest_.toString().orEmpty()
            newDeath.text =getString(R.string.newDeath)+ newDeath_.orEmpty()
            critical.text =getString(R.string.critical) + critical_.toString().orEmpty()
            activeCase.text =getString(R.string.activeCase)+ activeCase_.toString().orEmpty()
            recovered.text =  getString(R.string.recovered) +recovered_.toString().orEmpty()
            totalDeath.text = getString(R.string.totalDeath)+ totalDeath_.toString().orEmpty()
        }

        if (totalDeath_ == null){
            binding.totalDeath.text = binding.totalDeath.text.toString()+"-"
        }

        if(totalTest_ == null){
            binding.totalTest.text = binding.totalTest.text.toString()+"-"
        }

        if(newDeath_ == null){
            binding.newDeath.text =  binding.newDeath.text.toString()+"-"
        }

        if(critical_== null){
            binding.critical.text =  binding.critical.text.toString()+"-"
        }

        if(activeCase_ == null){
            binding.activeCase.text =  binding.activeCase.text.toString()+"-"
        }

        if(recovered_ == null){
            binding.recovered.text =  binding.recovered.text.toString()+"-"
        }

        binding.toHistory.setOnClickListener {
            val action = DetailScreenDirections.actionDetailScreenToHistoryScreen(countryName,continent_,totalTest_!!,totalDeath_!!,newDeath_,critical_!!,
            activeCase_!!,recovered_!!,population_!!,time_,casePop_,caseNew_,deathPop_,popTestH_)
            Navigation.findNavController(it).navigate(action)
        }

        binding.backToMainScreen.setOnClickListener{
            val action = DetailScreenDirections.actionDetailScreenToMainScreen()
            Navigation.findNavController(it).navigate(action)
        }

        binding.share.setOnClickListener {
            var message : String? = null
            message = ": $countryName " + "${getString(R.string.statisticsdata).toString()} "+
                    "${getString(R.string.totalDeath) + totalDeath_.toString().orEmpty()} " +
                    "${getString(R.string.totalTest) +totalTest_.toString().orEmpty()} " +
                    "${getString(R.string.totalDeath) + totalDeath_.toString().orEmpty()} "+
                    "${getString(R.string.newDeath) + newDeath_.toString().orEmpty()} "+
                    "${getString(R.string.critical) + critical_.toString().orEmpty()} " +
                    "${getString(R.string.activeCase) + activeCase_.toString().orEmpty()} " +
                    "${getString(R.string.recovered) + recovered_.toString().orEmpty()} " +
                    "${getString(R.string.population) + population_.toString().orEmpty()} "+
                    "${getString(R.string.time) + time_.toString().orEmpty()} " +
                    "${getString(R.string.popCase) + casePop_.toString().orEmpty()} "+
                    "${getString(R.string.newCases) + caseNew_.toString().orEmpty()} " +
                    "${getString(R.string.popDeath) + deathPop_.toString().orEmpty()} " +
                    "${getString(R.string.popTest) + popTestH_.toString().orEmpty()} "

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,message)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent,"Share to : "))

        }

    }


}


