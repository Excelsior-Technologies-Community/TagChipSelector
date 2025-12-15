package com.ext.tagchip

data class ChipStyle(
    val selectedBgColor: Int,
    val unselectedBgColor: Int,
    val selectedTextColor: Int,
    val unselectedTextColor: Int,
    val selectedBackground: ChipBackground = ChipBackground(),
    val unselectedBackground: ChipBackground = ChipBackground()
)