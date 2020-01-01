package com.rizkirakasiwi.kade.fragment.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.controller.Loading
import com.rizkirakasiwi.kade.fragment.controller.NextMatchAdapter
import com.rizkirakasiwi.kade.fragment.controller.TeamAdapter
import com.rizkirakasiwi.kade.fragment.model.TeamViewModel
import kotlinx.android.synthetic.main.next_match_fragment.*
import kotlinx.android.synthetic.main.team_fragment.*

class Team : Fragment() {

    companion object {
        fun newInstance() = Team()
    }

    private lateinit var viewModel: TeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.team_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)
        viewModel.getDetailLeagueData.observe(this, Observer {
            viewModel.loadTeam(it.leagues[0].idLeague)
        })

        viewModel.teamLiveData.observe(this, Observer {
            if (it.teams.isNullOrEmpty()){
                Loading.progress(progress_bar_team,recycler_team, false)
            }else {
                Loading.progress(progress_bar_team,recycler_team, true)
                recycler_team.adapter = TeamAdapter(it)
            }
        })
    }

}
