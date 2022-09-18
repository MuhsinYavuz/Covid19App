
package com.muhsinyavuz.covid19trackingapplication.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhsinyavuz.covid19trackingapplication.databinding.FragmentMainScreenBinding
import com.muhsinyavuz.covid19trackingapplication.model.Statistic
import com.muhsinyavuz.covid19trackingapplication.ui.adapter.CountryAdapter
import com.muhsinyavuz.covid19trackingapplication.ui.viewmodel.MainScreenViewModel


class MainScreen : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var viewModel: MainScreenViewModel
    private var countryAdapter = CountryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]
        viewModel.refreshData()
        binding.countryList.layoutManager = LinearLayoutManager(context)
        binding.countryList.adapter = countryAdapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.countryList.visibility = View.GONE
            binding.countryError.visibility = View.GONE
            binding.countryListLoadingBar.visibility = View.VISIBLE
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false // sistemdeki swipeRes kapattık

        }

        binding.countrySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                countryAdapter.filter.filter(p0)
                return false
            }
        })

        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateMovieList(countries.statistics)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    binding.countryError.visibility = View.VISIBLE
                } else {
                    binding.countryError.visibility = View.GONE
                }
            }
        })
        viewModel.countrLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    binding.countryListLoadingBar.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE
                    binding.countryError.visibility = View.GONE
                } else {
                    binding.countryListLoadingBar.visibility = View.GONE
                }
            }
        }
        )
    }
}