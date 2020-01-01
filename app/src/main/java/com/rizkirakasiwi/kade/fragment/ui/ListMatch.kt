package com.rizkirakasiwi.kade.fragment.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.model.ListMatchViewModel

class ListMatch : Fragment() {

    companion object {
        fun newInstance() = ListMatch()
    }

    private lateinit var viewModel: ListMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListMatchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
