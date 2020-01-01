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
import com.rizkirakasiwi.kade.fragment.model.NextMatchViewModel
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailLeagueData
import kotlinx.android.synthetic.main.next_match_fragment.*

class NextMatch : Fragment() {

    companion object {
        fun newInstance() = NextMatch()
    }

    private lateinit var viewModel: NextMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.next_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NextMatchViewModel::class.java)
        viewModel.getDetailLeagueData.observe(this, Observer {
            viewModel.loadNextMatch(it.leagues[0].idLeague)
        })

        viewModel.nextMatchLiveData.observe(this, Observer {
            if (it.events.isNullOrEmpty()){
                Loading.progress(progress_bar_nextMatch,recycler_nextMatch, false)
            }else {
                Loading.progress(progress_bar_nextMatch,recycler_nextMatch, true)
                recycler_nextMatch.adapter = NextMatchAdapter(it)
            }
        })
    }

}
