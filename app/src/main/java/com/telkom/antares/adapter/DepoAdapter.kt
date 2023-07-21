package com.telkom.antares.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.telkom.antares.data.dao.depo.DepoData
import com.telkom.antares.databinding.DepoListBinding

class DepoAdapter(private var onClick : DepoAdapter.DeposInterface) : RecyclerView.Adapter<DepoAdapter.ViewHolder>() {

    private var diffCallback = object : DiffUtil.ItemCallback<DepoData>(){

        override fun areItemsTheSame(oldItem: DepoData, newItem: DepoData): Boolean {
            return oldItem.id_depo == newItem.id_depo
        }

        override fun areContentsTheSame(oldItem: DepoData, newItem: DepoData): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class ViewHolder (private val binding: DepoListBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(Depos: DepoData){
            binding.apply {
                dataDepo = Depos

                btnNext.setOnClickListener {
                    onClick.detailDepo(Depos)
                }
            }
        }
    }

    interface DeposInterface {
        fun detailDepo(Depos: DepoData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    fun setData(data : List<DepoData>){
        differ.submitList(data)
    }
}