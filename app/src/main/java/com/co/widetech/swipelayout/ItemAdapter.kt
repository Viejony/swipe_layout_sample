package com.co.widetech.swipelayout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.co.widetech.swipelayout.databinding.SwipeItemBinding
import kotlin.random.Random

class ItemAdapter(private val context: Context, private val items: List<String>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    companion object{
        val colorsList = listOf(
            R.color.purple_200,
            R.color.white,
            R.color.teal_200,
            R.color.cyan,
            R.color.blue,
            R.color.green,
            R.color.magenta,
            R.color.red,
            R.color.green,
            R.color.orange,
            R.color.yellow,
        )
    }

    private val randomGenerator = Random(12)

    inner class ViewHolder(val itemBinding: SwipeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = SwipeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.text.text = "Swipe Item $position: ${items[position]}"


        for (i in 0..10) {
            val randomColor = randomGenerator.nextInt(until = colorsList.size)
            val inflaterSensor = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val element = inflaterSensor.inflate(R.layout.color_item, null) as LinearLayout
            element.findViewById<TextView>(R.id.text).text = "$i"
            //val element = LinearLayout(context)
            element.setBackgroundColor(context.getColor(colorsList[randomColor]))

            val width = Utils().dp2Px(60, context)
            element.layoutParams = LinearLayout.LayoutParams(width, width)
            holder.itemBinding.horizontalContainer.addView(element)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}