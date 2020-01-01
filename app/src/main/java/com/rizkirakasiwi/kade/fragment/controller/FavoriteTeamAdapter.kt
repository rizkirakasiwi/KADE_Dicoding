package com.rizkirakasiwi.kade.fragment.controller

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.data.detail.Team
import com.rizkirakasiwi.kade.fragment.transaction.LoadFavoriteFromLocalDatabase
import com.rizkirakasiwi.kade.fragment.transaction.RemoveFavoriteFromLocalDatabase
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailTeamData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_ui.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteTeamAdapter (val team : MutableList<Team>):RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.team_ui, parent, false
        )
    )

    override fun getItemCount() = team.count()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val view = holder.itemView
        view.txt_team_name.text = team[position].strTeam
        view.txt_team_location.text = team[position].strStadiumLocation
        GlobalScope.launch(Dispatchers.Main){
            val theteam = LoadTeam.getTeam(team[position].idTeam)
            Picasso.get().load(theteam.strTeamBadge).into(view.img_team)
        }

        view.setOnClickListener {
            SaveDetailTeamData.setMatchDetail(team[position])
            it.findNavController().navigate(R.id.action_favorite_to_detailTeam)
        }

        val favoriteTeam = LoadFavoriteFromLocalDatabase(view.context).team()

        if(!favoriteTeam.isNullOrEmpty()) {
            favoriteTeam.forEach { f ->
                if (f.idTeam == team[position].idTeam) {
                    FavoriteImage.change(view.img_team_favorite, true)
                }
            }
        }



        view.img_team_favorite.setOnClickListener {
            AlertDialog.Builder(view.context)
                .setTitle(view.context.getString(R.string.warning))
                .setMessage(view.context.getString(R.string.delete_warning))
                .setPositiveButton("OK"){dialogInterface, i ->
                    RemoveFavoriteFromLocalDatabase(view.context, view.img_team_favorite)
                        .team(team[position].idTeam
                        )
                    team.removeAt(position)
                    notifyItemRemoved(position)
                    notifyDataSetChanged()
                }
                .setNegativeButton("Cancel"){dialogInterface, i ->  }
                .create()
                .show()
        }
    }

}