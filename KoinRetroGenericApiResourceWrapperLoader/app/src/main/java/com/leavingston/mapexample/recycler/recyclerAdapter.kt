package com.leavingston.mapexample.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.leavingston.mapexample.R
import com.leavingston.mapexample.models.ModelClass
import com.leavingston.mapexample.models.Resource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_view_holder.view.*

class recyclerAdapter(context : Context) : RecyclerView.Adapter<recyclerAdapter.viewHolder>() {

    private var items = ArrayList<ModelClass>()
    private val context = context

    inner class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_holder , parent , false)

        return  viewHolder(itemView )

    }



    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val currentItem = items[position]

        holder.itemView.modelClassId.text = currentItem.id.toString()
        Picasso.get().load(currentItem.url).into(holder.itemView.modelClassImage)

//        holder.itemView.modelClassImage.setImageResource(R.mipmap.ic_launcher)




    }


    override fun getItemCount(): Int = items.size

    fun clearData(){
        this.items.clear()
        notifyDataSetChanged()
    }


    fun setData(items : Resource<ArrayList<ModelClass>>){
        this.items.clear()
        this.items.addAll(items.data!!)
        notifyDataSetChanged()
    }

}

