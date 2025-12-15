package com.ext.tagchip

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager

class TagChipView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private var multiSelect = true
    private var removable = true
    private lateinit var chipStyle: ChipStyle

    init {
        layoutManager = FlexboxLayoutManager(context)
        parseAttributes(context, attrs)
    }

    private fun parseAttributes(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.TagChipView)

        // Behavior
        multiSelect = ta.getBoolean(
            R.styleable.TagChipView_chipMultiSelect,
            true
        )

        removable = ta.getBoolean(
            R.styleable.TagChipView_chipRemovable,
            true
        )

        // Colors
        val selectedBgColor = ta.getColor(
            R.styleable.TagChipView_chipSelectedBgColor,
            Color.parseColor("#2196F3")
        )

        val unselectedBgColor = ta.getColor(
            R.styleable.TagChipView_chipUnselectedBgColor,
            Color.parseColor("#E0E0E0")
        )

        val selectedTextColor = ta.getColor(
            R.styleable.TagChipView_chipSelectedTextColor,
            Color.WHITE
        )

        val unselectedTextColor = ta.getColor(
            R.styleable.TagChipView_chipUnselectedTextColor,
            Color.BLACK
        )

        // Common background defaults
        val defaultRadius = dpToPx(context, 10f)

        val commonCorner = ta.getDimension(
            R.styleable.TagChipView_chipCornerRadius,
            defaultRadius
        )

        val commonStrokeWidth = ta.getDimensionPixelSize(
            R.styleable.TagChipView_chipStrokeWidth,
            0
        )

        val commonStrokeColor = ta.getColor(
            R.styleable.TagChipView_chipStrokeColor,
            Color.TRANSPARENT
        )

        // Selected background
        val selectedBackground = ChipBackground(
            cornerRadius = ta.getDimension(
                R.styleable.TagChipView_chipSelectedCornerRadius,
                commonCorner
            ),
            strokeWidth = ta.getDimensionPixelSize(
                R.styleable.TagChipView_chipSelectedStrokeWidth,
                commonStrokeWidth
            ),
            strokeColor = ta.getColor(
                R.styleable.TagChipView_chipSelectedStrokeColor,
                commonStrokeColor
            )
        )

        // Unselected background
        val unselectedBackground = ChipBackground(
            cornerRadius = ta.getDimension(
                R.styleable.TagChipView_chipUnselectedCornerRadius,
                commonCorner
            ),
            strokeWidth = ta.getDimensionPixelSize(
                R.styleable.TagChipView_chipUnselectedStrokeWidth,
                commonStrokeWidth
            ),
            strokeColor = ta.getColor(
                R.styleable.TagChipView_chipUnselectedStrokeColor,
                commonStrokeColor
            )
        )

        chipStyle = ChipStyle(
            selectedBgColor = selectedBgColor,
            unselectedBgColor = unselectedBgColor,
            selectedTextColor = selectedTextColor,
            unselectedTextColor = unselectedTextColor,
            selectedBackground = selectedBackground,
            unselectedBackground = unselectedBackground
        )

        ta.recycle()
    }

    fun setTags(
        list: List<TagItem>,
        onSelectionChanged: (List<TagItem>) -> Unit
    ) {
        adapter = TagChipAdapter(
            list.toMutableList(),
            multiSelect,
            removable,
            chipStyle,
            onSelectionChanged
        )
    }

    fun updateStyle(newStyle: ChipStyle) {
        chipStyle = newStyle
        (adapter as? TagChipAdapter)?.updateStyle(newStyle)
    }

    private fun dpToPx(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

}
