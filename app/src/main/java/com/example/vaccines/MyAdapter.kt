package com.example.vaccines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val vaccinesList: ArrayList<VaccineModel>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var vaccineImage: ImageView
        lateinit var vaccineTitle: TextView

        init {
            vaccineImage = itemView.findViewById(R.id.imageView)
            vaccineTitle = itemView.findViewById(R.id.text1)

            itemView.setOnClickListener {
                val vaccine = vaccinesList[adapterPosition]
                val fragment = VaccineDetailsFragment.newInstance(vaccine.name, vaccine.img, vaccine.description)

                val fragmentManager = itemView.context.let { context ->
                    if (context is FragmentActivity) {
                        context.supportFragmentManager
                    } else {
                        throw IllegalStateException("The context is not an instance of FragmentActivity.")
                    }
                }

                fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_layout, parent, false)

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.vaccineTitle.text = vaccinesList[position].name
        holder.vaccineImage.setImageResource(vaccinesList[position].img)
    }

    override fun getItemCount(): Int {
        return vaccinesList.size
    }
}