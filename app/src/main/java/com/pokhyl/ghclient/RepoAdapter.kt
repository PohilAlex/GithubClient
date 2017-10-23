package com.pokhyl.ghclient

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pokhyl.ghclient.model.Repo
import java.util.*

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    var repoList: List<Repo> = ArrayList<Repo>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_repo_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo : Repo = repoList[position]
        holder.name.text = repo.name
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView

        init {
            name = itemView.findViewById(R.id.repo_name)
        }
    }
}
