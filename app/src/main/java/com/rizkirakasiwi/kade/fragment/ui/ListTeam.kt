package com.rizkirakasiwi.kade.fragment.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.model.ListTeamViewModel

class ListTeam : Fragment() {

    companion object {
        fun newInstance() = ListTeam()
    }

    private lateinit var viewModel: ListTeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_team_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListTeamViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
