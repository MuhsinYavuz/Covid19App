package com.muhsinyavuz.covid19trackingapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.muhsinyavuz.covid19trackingapplication.R
import com.muhsinyavuz.covid19trackingapplication.databinding.FragmentHistoryScreenBinding


class HistoryScreen : Fragment() {
    private lateinit var binding: FragmentHistoryScreenBinding

    var countryName_ : String?= null
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            countryName_ = HistoryScreenArgs.fromBundle(it!!).name
            continent_ = HistoryScreenArgs.fromBundle(it!!).continent
            totalTest_ = HistoryScreenArgs.fromBundle(it!!).totalTest
            newDeath_= HistoryScreenArgs.fromBundle(it!!).newDeath
            critical_ = HistoryScreenArgs.fromBundle(it!!).critical
            activeCase_= HistoryScreenArgs.fromBundle(it!!).activeCase
            recovered_= HistoryScreenArgs.fromBundle(it!!).recovered
            totalDeath_= HistoryScreenArgs.fromBundle(it!!).totalDeath
            population_= HistoryScreenArgs.fromBundle(it!!).population
            time_= HistoryScreenArgs.fromBundle(it!!).time
            popTestH_= HistoryScreenArgs.fromBundle(it!!).testPopH
            casePop_ = HistoryScreenArgs.fromBundle(it!!).casePop
            caseNew_= HistoryScreenArgs.fromBundle(it!!).newCase
            deathPop_ = HistoryScreenArgs.fromBundle(it!!).deathsPop
        }

        with(binding){
            countryName.text = countryName_.orEmpty()
            continentName.text = continent_.orEmpty()
            testTotal.text = getString(R.string.totalTest).toString() + totalTest_.toString().orEmpty()
            newDeath.text = getString(R.string.newDeath).toString() + newDeath_.toString().orEmpty()
            critical.text = getString(R.string.critical).toString()+critical_.toString().orEmpty()
            activeCase.text = getString(R.string.activeCase).toString()+activeCase_.toString().orEmpty()
            recovered.text = getString(R.string.recovered).toString()+recovered_.toString().orEmpty()
            totalDeath.text = getString(R.string.totalDeath).toString()+totalDeath_.toString().orEmpty()
            population.text = getString(R.string.population).toString()+population_.toString().orEmpty()
            time.text = getString(R.string.time).toString()  +time_.toString().orEmpty()
            popTest.text = getString(R.string.popTest).toString() +popTestH_.orEmpty()
            casePop.text = getString(R.string.popCase).toString()+casePop_.orEmpty()
            newCase.text = getString(R.string.newCases).toString()+caseNew_.orEmpty()
            popDeath.text = getString(R.string.popDeath).toString() + deathPop_.orEmpty()
        }

        binding.toDetailScreen.setOnClickListener{
            val action = HistoryScreenDirections.actionHistoryScreenToDetailScreen(countryName_,continent_,totalTest_!!,totalDeath_!!,newDeath_,critical_!!,
            activeCase_!!,recovered_!!,population_!!,time_!!,casePop_,caseNew_,deathPop_,popTestH_)
            Navigation.findNavController(it).navigate(action)
        }
    }

}