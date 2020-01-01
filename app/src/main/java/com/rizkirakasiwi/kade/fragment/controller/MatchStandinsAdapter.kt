package com.rizkirakasiwi.kade.fragment.controller

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.data.list.MatchStandings
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match_standings_ui.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchStandinsAdapter (val matchStandings: MatchStandings):RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.match_standings_ui, parent, false)
    )

    override fun getItemCount() = matchStandings.table.count()

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val view = holder.itemView
        view.txt_match_standings_draw.text = "Draw : "+matchStandings.table[position].draw.toString()
        view.txt_match_standings_goals_against.text = "Goals Against : "+matchStandings.table[position].goalsagainst.toString()
        view.txt_match_standings_goals_difference.text = "Goals Difference : "+matchStandings.table[position].goalsdifference.toString()
        view.txt_match_standings_goals_for.text = "Goals For : "+matchStandings.table[position].goalsfor.toString()
        view.txt_match_standings_loss.text = "Loss : "+matchStandings.table[position].loss.toString()
        view.txt_match_standings_played.text = "Played : "+matchStandings.table[position].played.toString()
        view.txt_match_standings_total.text = "Total : "+matchStandings.table[position].total.toString()
        view.txt_match_standings_win.text = "Win : "+matchStandings.table[position].win.toString()
        view.txt_match_standings_name.text = matchStandings.table[position].name
        GlobalScope.launch(Dispatchers.Main){
            val team = LoadTeam.getTeam(matchStandings.table[position].teamid)
            Picasso.get().load(team.strTeamBadge).into(view.img_match_standings_ui)
        }
    }



}