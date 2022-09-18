
package com.muhsinyavuz.covid19trackingapplication.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.Display
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.muhsinyavuz.covid19trackingapplication.R
import com.muhsinyavuz.covid19trackingapplication.databinding.ItemRowBinding
import com.muhsinyavuz.covid19trackingapplication.model.History
import com.muhsinyavuz.covid19trackingapplication.model.Model
import com.muhsinyavuz.covid19trackingapplication.model.Statistic
import com.muhsinyavuz.covid19trackingapplication.ui.fragment.MainScreenDirections
import java.util.*
import kotlin.collections.ArrayList


class CountryAdapter
    : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), Filterable{
    private var countryList = arrayListOf<Statistic>()
    private var newlisttFiltered = arrayListOf<Statistic>()
    private var boolean = false

     inner class CountryViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    init {
        newlisttFiltered = countryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        if (!boolean) {
           countryList.sortBy { it.country }
            newlisttFiltered.sortBy { it.country }
            boolean = true
        }

        holder.itemView.setOnClickListener {

            val action = MainScreenDirections.actionMainScreenToDetailScreen(newlisttFiltered[position].country,newlisttFiltered[position].continent
            ,newlisttFiltered[position].tests.total,newlisttFiltered[position].deaths.total,newlisttFiltered[position].deaths.new,
                newlisttFiltered[position].cases.critical,newlisttFiltered[position].cases.active,newlisttFiltered[position].cases.recovered,
                newlisttFiltered[position].population,newlisttFiltered[position].time,newlisttFiltered[position].cases.mPop,
                newlisttFiltered[position].cases.new,newlisttFiltered[position].deaths.mPop,newlisttFiltered[position].tests.mPop
            )
            Navigation.findNavController(it).navigate(action)
        }


        holder.binding.name.text=  newlisttFiltered[position].country.toUpperCase().toString()

        if (newlisttFiltered[position].cases.new == null){
            holder.binding.newCases.text =  holder.itemView.context.getString(R.string.newCases)  + "-"
        }else{
           holder.binding.newCases.text = holder.itemView.context.getString(R.string.newCases)  + newlisttFiltered[position].cases.new.toString()
        }

        if(newlisttFiltered[position].tests.mPop == null){
            holder.binding.confirmedTests.text = holder.itemView.context.getString(R.string.confirmedTests)  +"-"
        }else{
            holder.binding.confirmedTests.text = holder.itemView.context.getString(R.string.confirmedTests)  + newlisttFiltered[position].tests.mPop

        }

        if(newlisttFiltered[position].deaths.new == null){

                holder.binding.death.text =  holder.itemView.context.getString(R.string.newDeath)  + "-"

        }else{

                holder.binding.death.text = holder.itemView.context.getString(R.string.newDeath) + newlisttFiltered[position].deaths.new

        }

    }

    override fun getItemCount(): Int {
        return newlisttFiltered.size
    }

    fun updateMovieList(newCountryList: List<Statistic>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
        boolean = false

    }

    override fun getFilter(): Filter {

        countryList.forEach { println(it.country) }
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {

                val charString = p0.toString()
                newlisttFiltered.clear()

                if(charString.isEmpty()){
                    newlisttFiltered = countryList
                }

                else{

                    val resultList = ArrayList<Statistic>()
                    for(row in countryList){

                        if(row.country.toString().toLowerCase(Locale.ROOT).contains(charString.toLowerCase(Locale.ROOT)))
                        {
                            resultList.add(row)
                        }

                    }
                    newlisttFiltered = resultList
                }

                val filterResult = FilterResults()
                filterResult.values = newlisttFiltered
                return filterResult
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                newlisttFiltered = p1?.values as ArrayList<Statistic> /* = java.util.ArrayList<com.muhsinyavuz.covid19trackingapplication.model.Model.Statistic> */
                notifyDataSetChanged()
            }

        }
    }


}


