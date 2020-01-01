package com.rizkirakasiwi.kade.fragment.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.controller.Loading
import com.rizkirakasiwi.kade.fragment.controller.MatchStandinsAdapter
import com.rizkirakasiwi.kade.fragment.model.MatchStandingsViewModel
import kotlinx.android.synthetic.main.match_standings_fragment.*

class MatchStandings : Fragment() {

    companion object {
        fun newInstance() = MatchStandings()
    }

    private lateinit var viewModel: MatchStandingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_standings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MatchStandingsViewModel::class.java)
        viewModel.getDetailLeagueData.observe(this, Observer {
            viewModel.loadMatchStangings(it.leagues[0].idLeague)
        })

        viewModel.MatchStandingsLiveData.observe(this, Observer {
            if (it.table.isNullOrEmpty()){
                Loading.progress(progress_bar_match_standings,recycler_match_standings, false)
            }else {
                Loading.progress(progress_bar_match_standings,recycler_match_standings, true)
                recycler_match_standings.adapter = MatchStandinsAdapter(it)
            }
        })
    }

}
