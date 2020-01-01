package com.rizkirakasiwi.kade.fragment.controller

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.transaction.LoadFavoriteFromLocalDatabase
import com.rizkirakasiwi.kade.fragment.transaction.RemoveFavoriteFromLocalDatabase
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailMatchData
import com.rizkirakasiwi.kade.fragment.transaction.SaveFavoriteToLocalDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match_layout_ui.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteMatchAdapter (val match:MutableList<Event>, val isNext:Boolean = true):RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.match_layout_ui, parent, false)
    )

    override fun getItemCount() = match.count()

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val view = holder.itemView
        view.img_teamA_matchLayout.load(match[position].idHomeTeam!!)
        GlobalScope.launch(Dispatchers.Main){
            val teamA = LoadTeam.getTeam(match[position].idHomeTeam!!)
            val teamB = LoadTeam.getTeam(match[position].idAwayTeam!!)

            view.img_teamA_matchLayout.load(teamA.strTeamBadge)
            view.img_teamB_matchLayout.load(teamB.strTeamBadge)

            view.txt_centerText_matchLayout.text =
                if (isNext) view.context.getString(R.string.vs)
                else "${match[position].intHomeScore} - ${match[position].intAwayScore}"

            view.txt_place_nextMatch.text = "At ${teamA.strStadium} - ${teamA.strTeam}"
            view.txt_time_nextMatch.text = "${match[position].dateEvent} - ${match[position].strTime}"

            view.txt_teamA_name_matchLayout.text = teamA.strTeam
            view.txt_teamB_name_matchLayout.text = teamB.strTeam

            val favoriteMatch =
                if (isNext) LoadFavoriteFromLocalDatabase(view.context).match("NextMatch")
                else LoadFavoriteFromLocalDatabase(view.context).match("PreviousMatch")

            if(!favoriteMatch.isNullOrEmpty()) {
                favoriteMatch.forEach { f ->
                    if (f.idEvent == match[position].idEvent) {
                        FavoriteImage.change(view.img_favorite_matchLayout, true)
                    }
                }
            }

            view.setOnClickListener {
                SaveDetailMatchData.setMatchDetail(match[position])
                it.findNavController().navigate(R.id.action_favorite_to_detailMatch)
            }

            view.img_favorite_matchLayout.setOnClickListener {
                AlertDialog.Builder(view.context)
                    .setTitle(view.context.getString(R.string.warning))
                    .setMessage(view.context.getString(R.string.delete_warning))
                    .setPositiveButton("Ok") { _, _ ->
                        if(isNext) RemoveFavoriteFromLocalDatabase(view.context, view.img_favorite_matchLayout)
                            .match(match[position].idEvent!!, "NextMatch")
                        else RemoveFavoriteFromLocalDatabase(view.context, view.img_favorite_matchLayout)
                            .match(match[position].idEvent!!, "PreviousMatch")
                        match.removeAt(position)
                        notifyItemRemoved(position)
                        notifyDataSetChanged()
                    }
                    .setNegativeButton("Cancel"){ _, _ ->  }
                    .create()
                    .show()
            }
        }
    }

    private fun ImageView.load(url:String){
        Picasso.get().load(url).into(this)
    }

}