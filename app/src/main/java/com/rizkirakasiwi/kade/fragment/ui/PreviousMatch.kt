package com.rizkirakasiwi.kade.fragment.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.controller.Loading
import com.rizkirakasiwi.kade.fragment.controller.PreviousMatchAdapter
import com.rizkirakasiwi.kade.fragment.model.PreviousMatchViewModel
import com.rizkirakasiwi.kade.fragment.transaction.LoadFavoriteFromLocalDatabase
import kotlinx.android.synthetic.main.previous_match_fragment.*

class PreviousMatch : Fragment() {

    companion object {
        fun newInstance() =
            PreviousMatch()
    }

    private lateinit var viewModel: PreviousMatchViewModel

    private lateinit var favoriteMatch : List<Event>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.previous_match_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteMatch = listOf()
        favoriteMatch = LoadFavoriteFromLocalDatabase(this.context!!).match("PreviousMatch")
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PreviousMatchViewModel::class.java)
        viewModel.getDetailLeagueData.observe(this, Observer {
            viewModel.loadPreviosMatch(it.leagues[0].idLeague)
        })

        viewModel.previousMatchLiveData.observe(this, Observer {
            if (it.events.isNullOrEmpty()){
                Loading.progress(progress_bar_previousMatch,recycler_previousMatch, false)
            }else {
                Loading.progress(progress_bar_previousMatch,recycler_previousMatch, true)
                recycler_previousMatch.adapter = PreviousMatchAdapter(it)
            }
        })
    }

}
