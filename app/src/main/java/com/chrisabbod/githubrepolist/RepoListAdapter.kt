package com.chrisabbod.githubrepolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chrisabbod.githubrepolist.data.Data
import com.chrisabbod.githubrepolist.databinding.ItemRepoBinding

class RepoListAdapter(private val items: List<Data>?) :
    RecyclerView.Adapter<RepoListAdapter.RepoListViewHolder>() {

    inner class RepoListViewHolder(itemBinding: ItemRepoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val tvId = itemBinding.tvId
        val tvName = itemBinding.tvName
        val tvDescription = itemBinding.tvDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        return RepoListViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder.tvId.text = items?.get(position)?.id.toString()
        holder.tvName.text = items?.get(position)?.name
        holder.tvDescription.text = items?.get(position)?.description
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}