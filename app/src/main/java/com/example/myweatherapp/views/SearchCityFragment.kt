package com.example.myweatherapp.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.myweatherapp.MainActivity
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentSearchCityBinding


class SearchCityFragment : BaseFragment() {

    private val binding by lazy {
        FragmentSearchCityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.searchButton.setOnClickListener(View.OnClickListener {
            val cityName = binding.enterCityName.text.toString()
            Log.d("search fragment", cityName)
            myViewModel.setCityName(cityName)
            findNavController().navigate(R.id.action_searchFragment_to_forecastFragment)
            Log.d("search fragment nav", "transiton to forecast")
        })
    }

    companion object {
        fun newInstance() = SearchCityFragment()
    }
}