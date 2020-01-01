package com.rizkirakasiwi.kade.fragment.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.data.list.DetailLeague
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailLeagueData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_league_ui.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListLeagueRecyclerviewAdapter (val listLeague: List<DetailLeague>)
    :RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view =
            MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_league_ui, parent, false
                )
            )
        val size = calculateSizeOfView(view.itemView.context)

        val margin = 8 * 3
        val layoutParams = GridLayout.LayoutParams(ViewGroup.LayoutParams(size - margin, size + (size-(margin*2)))) // width and height

        layoutParams.bottomMargin = 8
        layoutParams.topMargin = 8
        layoutParams.leftMargin = 8
        layoutParams.rightMargin = 8

        view.itemView.layoutParams = layoutParams

        return view
    }


    override fun getItemCount(): Int = listLeague.count()


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val view = holder.itemView
        view.img_list_league_grid.load(listLeague[position].leagues[0].logo)
        view.txt_name_league_grid.text = listLeague[position].leagues[0].leagueName
        view.setOnClickListener {
            SaveDetailLeagueData.save(listLeague[position])
            it.findNavController().navigate(R.id.action_listLeague_to_leagueDetail)
        }


    }

    private fun ImageView.load(url:String)= GlobalScope.launch(Dispatchers.Main){
        Picasso.get().load(url).into(this@load)
    }

    fun calculateSizeOfView(context: Context): Int {

        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels
        return (dpWidth / 3)
    }

}
