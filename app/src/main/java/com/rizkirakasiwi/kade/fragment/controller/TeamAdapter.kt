package com.rizkirakasiwi.kade.fragment.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.data.list.Teams
import com.rizkirakasiwi.kade.fragment.transaction.LoadFavoriteFromLocalDatabase
import com.rizkirakasiwi.kade.fragment.transaction.RemoveFavoriteFromLocalDatabase
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailTeamData
import com.rizkirakasiwi.kade.fragment.transaction.SaveFavoriteToLocalDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_ui.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamAdapter (val team:Teams):RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.team_ui, parent, false
        )
    )

    override fun getItemCount() = team.teams.count()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val favoritePosition = arrayListOf<Int>()
        val view = holder.itemView
        view.txt_team_name.text = team.teams[position].strTeam
        view.txt_team_location.text = team.teams[position].strStadiumLocation
        GlobalScope.launch(Dispatchers.Main){
            val theteam = LoadTeam.getTeam(team.teams[position].idTeam)
            Picasso.get().load(theteam.strTeamBadge).into(view.img_team)
        }

        view.setOnClickListener {
            SaveDetailTeamData.setMatchDetail(team.teams[position])
            it.findNavController().navigate(R.id.action_leagueDetail_to_detailTeam)
        }

        val favoriteTeam = LoadFavoriteFromLocalDatabase(view.context).team()

        if(!favoriteTeam.isNullOrEmpty()) {
            favoriteTeam.forEach { f ->
                if (f.idTeam == team.teams[position].idTeam) {
                    FavoriteImage.change(view.img_team_favorite, true)
                    favoritePosition.add(position)
                }
            }
        }


        var countclick = 0
        view.img_team_favorite.setOnClickListener {
            countclick += 1
            if(!favoritePosition.isEmpty()) {
                if (countclick == 1) {
                    RemoveFavoriteFromLocalDatabase(view.context, view.img_team_favorite)
                        .team(team.teams[position].idTeam
                        )

                } else {
                    SaveFavoriteToLocalDatabase(view.context, view.img_team_favorite)
                        .team(team.teams[position])
                    countclick = 0
                }
            }else{
                if (countclick == 1) {
                    SaveFavoriteToLocalDatabase(view.context, view.img_team_favorite)
                        .team(team.teams[position])
                } else {
                    RemoveFavoriteFromLocalDatabase(view.context, view.img_team_favorite)
                        .team(team.teams[position].idTeam
                        )
                    countclick = 0
                }
            }
        }





    }


}