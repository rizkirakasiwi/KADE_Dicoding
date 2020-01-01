package com.rizkirakasiwi.kade.fragment.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.controller.LoadTeam
import com.rizkirakasiwi.kade.fragment.controller.Loading
import com.rizkirakasiwi.kade.fragment.model.DetailMatchViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_match_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailMatch : Fragment() {

    companion object {
        fun newInstance() = DetailMatch()
    }

    private lateinit var viewModel: DetailMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_match_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.detail_match)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailMatchViewModel::class.java)
        viewModel.detailMatch.observe(this, Observer {event ->
           GlobalScope.launch(Dispatchers.Main){
               val teamA = LoadTeam.getTeam(event.idHomeTeam!!)
               val teamB = LoadTeam.getTeam(event.idAwayTeam!!)
               Picasso.get().load(teamA.strTeamBadge).into(img_detail_teamA)
               Picasso.get().load(teamB.strTeamBadge).into(img_detail_teamB)

               if(img_detail_teamA.drawable == null && img_detail_teamB.drawable == null){
                 Loading.progress(progress_bar_detailMatch, linear_detailMatch, false)
               }else{
                   Loading.progress(progress_bar_detailMatch, linear_detailMatch, true)
                   txt_detail_teamAName.text = event.strHomeTeam
                   txt_detail_teamBName.text = event.strAwayTeam
                   txt_detail_LocationMatch.text = teamA.strStadiumLocation
                   txt_detail_timeMatch.text = "${event.dateEvent} / ${event.strTime}"
                   txt_detail_teamARedCard.text =
                       if(event.strHomeRedCards.isNullOrEmpty()) "${event.strHomeTeam}\nNo Data"
                       else "${event.strHomeTeam}\n${event.strHomeRedCards}"
                   txt_detail_teamBRedCard.text =
                       if(event.strAwayRedCards.isNullOrEmpty()) "${event.strAwayTeam}\nNo Data"
                       else "${event.strAwayTeam}\n${event.strAwayRedCards}"
                   txt_detail_teamAFormation.text =
                       if(event.strHomeFormation.isNullOrEmpty()) "${event.strHomeTeam}\nNo Data"
                       else "${event.strHomeTeam}\n${event.strHomeFormation}"
                   txt_detail_teamBFormation.text =
                       if(event.strAwayFormation.isNullOrEmpty()) "${event.strAwayTeam}\nNo Data"
                       else "${event.strAwayTeam}\n${event.strAwayFormation}"
                   txt_detail_teamAYellowCard.text =
                       if(event.strHomeYellowCards.isNullOrEmpty()) "${event.strHomeTeam}\nNo Data"
                       else "${event.strHomeTeam}\n${event.strHomeYellowCards}"
                   txt_detail_teamBYellowCard.text =
                       if(event.strAwayYellowCards.isNullOrEmpty()) "${event.strHomeTeam}\nNo Data"
                       else "${event.strHomeTeam}\n${event.strAwayYellowCards}"
                   txt_detail_center.text =
                       if(event.intAwayScore.isNullOrEmpty() || event.intHomeScore == "null") "VS"
                       else "${event.intHomeScore} - ${event.intAwayScore}"
               }



           }
        })
    }

}
