package com.ext.tagchip

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TagChipAdapter(
    private val tags: MutableList<TagItem>,
    private val multiSelect: Boolean,
    private val removable: Boolean,
    var style: ChipStyle,
    private val callback: (List<TagItem>) -> Unit
) : RecyclerView.Adapter<TagChipAdapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val root: LinearLayout = view.findViewById(R.id.chipRoot)
        val text: TextView = view.findViewById(R.id.tvText)
        val remove: ImageView = view.findViewById(R.id.ivRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tag_chip, parent, false)
        return VH(view)
    }

    override fun getItemCount() = tags.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val tag = tags[position]
        val isSelected = tag.isSelected

        holder.text.text = tag.text

        // Background color
        val bgColor =
            if (isSelected) style.selectedBgColor
            else style.unselectedBgColor

        // Background config (corner, stroke)
        val bgConfig =
            if (isSelected) style.selectedBackground
            else style.unselectedBackground

        // Text color
        val textColor =
            if (isSelected) style.selectedTextColor
            else style.unselectedTextColor

        // Apply background & text color
        holder.root.background = createBg(bgColor, bgConfig)
        holder.text.setTextColor(textColor)

        // Remove icon visibility
        holder.remove.visibility =
            if (removable && isSelected) View.VISIBLE else View.GONE

        // Chip click
        holder.root.setOnClickListener {
            if (!multiSelect) {
                tags.forEach { it.isSelected = false }
            }
            tag.isSelected = !tag.isSelected
            notifyDataSetChanged()
            callback(tags.filter { it.isSelected })
        }

        // Remove click
        holder.remove.setOnClickListener {
            val adapterPos = holder.bindingAdapterPosition
            if (adapterPos != RecyclerView.NO_POSITION) {
                tags.removeAt(adapterPos)
                notifyItemRemoved(adapterPos)
                callback(tags.filter { it.isSelected })
            }
        }
    }


    fun updateStyle(newStyle: ChipStyle) {
        style = newStyle
        notifyDataSetChanged()
    }


    private fun createBg(
        bgColor: Int,
        bg: ChipBackground
    ): Drawable {
        return GradientDrawable().apply {
            cornerRadius = bg.cornerRadius
            setColor(bgColor)
            setStroke(bg.strokeWidth, bg.strokeColor)
        }
    }

    fun addTags(newTags: List<TagItem>) {
        val start = tags.size
        tags.addAll(newTags)
        notifyItemRangeInserted(start, newTags.size)
    }

}

