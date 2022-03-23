package com.example.myweatherapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweatherapp.MainActivity
import com.example.myweatherapp.R
import com.example.myweatherapp.adapter.WeatherAdapter
import com.example.myweatherapp.databinding.FragmentForecastBinding
import com.example.myweatherapp.viewmodel.ResultState


class ForecastFragment : BaseFragment() {

    private val binding by lazy {
        FragmentForecastBinding.inflate(layoutInflater)
    }

    private val weatherAdapter by lazy {
        WeatherAdapter(onForecastClicked = {forecast ->
            myViewModel.setForecast(forecast)
            findNavController().navigate(R.id.action_forecastFragment_to_detailsFragment)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        Log.d("forecast fragent", "on create view")
        binding.myRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = weatherAdapter
        }

        myViewModel.cityForecast.observe(viewLifecycleOwner, ::handleState)
        myViewModel.cityName.observe(viewLifecycleOwner, Observer {
            Log.d("forecast fragment observer", it)
        })

        val cityName = myViewModel.getCityName()
        Log.d("forecast fragment", cityName)
        binding.cityForecast.text = "$cityName forecast"
        myViewModel.getForecast(cityName)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.returnToSearch.setOnClickListener {
            findNavController().navigate(R.id.action_forecastFragment_to_searchFragment)
        }
    }

    private fun handleState(resultState: ResultState) {
        when (resultState) {
            is ResultState.LOADING -> {
                Toast.makeText(requireContext(), "LOADING ....", Toast.LENGTH_LONG).show()
            }
            is ResultState.SUCCESS -> {
                weatherAdapter.setForecast(resultState.results.list)
            }
            is ResultState.ERROR -> {
                Toast.makeText(requireContext(),
                    resultState.error.localizedMessage,
                    Toast.LENGTH_LONG).show()
                Log.e("****", resultState.error.localizedMessage)
            }
        }
    }


    companion object {
        fun newInstance() = ForecastFragment()
    }
}