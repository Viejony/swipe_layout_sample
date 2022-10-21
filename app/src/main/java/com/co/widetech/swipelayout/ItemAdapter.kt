package com.co.widetech.swipelayout

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
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

        private val debugTag = ItemAdapter::class.simpleName ?: ""
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.textView.text = "Swipe Item $position: ${items[position]}"

        for (i in 0..10) {

            // Create a view and configure its color
            val randomColor = randomGenerator.nextInt(until = colorsList.size)
            val inflaterSensor = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val element = inflaterSensor.inflate(R.layout.color_item, null) as LinearLayout

            // Add text
            element.findViewById<TextView>(R.id.textView).text = "$i"
            //val element = LinearLayout(context)
            element.setBackgroundColor(context.getColor(colorsList[randomColor]))

            // Config size
            val width = Utils().dp2Px(60, context)
            element.layoutParams = LinearLayout.LayoutParams(width, width)

            // Put element in the container
            holder.itemBinding.horizontalContainer.addView(element)
        }

        // Gesture listener
        val gestureDetector = GestureDetector(context, MyGestureDetector())
        holder.itemBinding.horizontalContainer.setOnTouchListener{ _, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                holder.itemBinding.swipeComponent.isSwipeEnabled = false
            }
            gestureDetector.onTouchEvent(event)
        }

        //val textViewGestureDetector = GestureDetector(context, MyGestureDetector())
        holder.itemBinding.textView.setOnTouchListener{ _, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                holder.itemBinding.swipeComponent.isSwipeEnabled = true
            }
            gestureDetector.onTouchEvent(event)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyGestureDetector: GestureDetector.SimpleOnGestureListener(){

        override fun onDown(e: MotionEvent?): Boolean {
            Log.d(debugTag, "onDown: e = $e")
            return super.onDown(e)
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            Log.d(debugTag, "onSingleTapConfirmed")
            return super.onSingleTapConfirmed(e)
        }

        override fun onLongPress(e: MotionEvent?) {
            Log.d(debugTag, "onLongPress")
            super.onLongPress(e)
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            Log.d(debugTag, "onDoubleTap")
            return super.onDoubleTap(e)
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            Log.d(debugTag, "onScroll: e1 = $e1, e2 = $e2, distanceX = $distanceX, distamnceY = $distanceY")
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.d(debugTag, "onFling")
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }
}