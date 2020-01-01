package com.rizkirakasiwi.kade.fragment.controller

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.data.list.Match
import com.rizkirakasiwi.kade.fragment.transaction.LoadFavoriteFromLocalDatabase
import com.rizkirakasiwi.kade.fragment.transaction.RemoveFavoriteFromLocalDatabase
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailMatchData
import com.rizkirakasiwi.kade.fragment.transaction.SaveFavoriteToLocalDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match_layout_ui.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchAdapter(val match:Match):RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.match_layout_ui, parent, false)
    )

    override fun getItemCount() = match.events.count()

    private fun ImageView.load(url:String){
        Picasso.get().load(url).into(this@load)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val view = holder.itemView
        view.img_teamA_matchLayout.load(match.events[position].idHomeTeam!!)
        GlobalScope.launch(Dispatchers.Main){
            val teamA = LoadTeam.getTeam(match.events[position].idHomeTeam!!)
            val teamB = LoadTeam.getTeam(match.events[position].idAwayTeam!!)

            view.img_teamA_matchLayout.load(teamA.strTeamBadge)
            view.img_teamB_matchLayout.load(teamB.strTeamBadge)

            view.txt_centerText_matchLayout.text =
                if(match.events[position].intHomeScore.isNullOrEmpty())
                    view.context.getString(R.string.vs)
                else "${match.events[position].intHomeScore} - ${match.events[position].intAwayScore}"

            view.txt_place_nextMatch.text = "At ${teamA.strStadium} - ${teamA.strTeam}"
            view.txt_time_nextMatch.text = "${match.events[position].dateEvent} - ${match.events[position].strTime}"

            view.txt_teamA_name_matchLayout.text = teamA.strTeam
            view.txt_teamB_name_matchLayout.text = teamB.strTeam

            val favoriteMatch =
                if(match.events[position].intHomeScore.isNullOrEmpty())
                    LoadFavoriteFromLocalDatabase(view.context).match("NextMatch")
                else LoadFavoriteFromLocalDatabase(view.context).match("PreviousMatch")
            val favoritePosition = arrayListOf<Int>()

            if(!favoriteMatch.isNullOrEmpty()) {
                favoriteMatch.forEach { f ->
                    if (f.idEvent == match.events[position].idEvent) {
                        FavoriteImage.change(view.img_favorite_matchLayout, true)
                        favoritePosition.add(position)
                    }
                }
            }

            view.setOnClickListener {
                SaveDetailMatchData.setMatchDetail(match.events[position])
                it.findNavController().navigate(R.id.action_search_to_detailMatch)
            }

            var countclick = 0
            view.img_favorite_matchLayout.setOnClickListener {
                countclick += 1
                if(!favoritePosition.isEmpty()) {
                    if (countclick == 1) {
                        if(match.events[position].intHomeScore.isNullOrEmpty()) {
                            RemoveFavoriteFromLocalDatabase(
                                view.context,
                                view.img_favorite_matchLayout
                            )
                                .match(match.events[position].idEvent!!, "NextMatch")
                        }else{
                            RemoveFavoriteFromLocalDatabase(
                                view.context,
                                view.img_favorite_matchLayout
                            )
                                .match(match.events[position].idEvent!!, "PreviousMatch")
                        }

                    } else {
                        if(match.events[position].intHomeScore.isNullOrEmpty()) {
                            SaveFavoriteToLocalDatabase(view.context, view.img_favorite_matchLayout)
                                .match(match.events[position], "NextMatch")
                        }else{
                            SaveFavoriteToLocalDatabase(view.context, view.img_favorite_matchLayout)
                                .match(match.events[position], "PreviousMatch")
                        }
                        countclick = 0
                    }
                }else{
                    if (countclick == 1) {
                        if(match.events[position].intHomeScore.isNullOrEmpty()) {
                            SaveFavoriteToLocalDatabase(view.context, view.img_favorite_matchLayout)
                                .match(match.events[position], "NextMatch")
                        }else{
                            SaveFavoriteToLocalDatabase(view.context, view.img_favorite_matchLayout)
                                .match(match.events[position], "PreviousMatch")
                        }
                    } else {
                        if(match.events[position].intHomeScore.isNullOrEmpty()) {
                            RemoveFavoriteFromLocalDatabase(
                                view.context,
                                view.img_favorite_matchLayout
                            )
                                .match(match.events[position].idEvent!!, "NextMatch")
                        }else{
                            RemoveFavoriteFromLocalDatabase(
                                view.context,
                                view.img_favorite_matchLayout
                            )
                                .match(match.events[position].idEvent!!, "PreviousMatch")
                        }
                        countclick = 0
                    }
                }
            }
        }
    }

}