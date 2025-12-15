package com.ext.tagchipselector

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ext.tagchip.ChipBackground
import com.ext.tagchip.ChipStyle
import com.ext.tagchip.TagChipView
import com.ext.tagchip.TagItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tagView = findViewById<TagChipView>(R.id.tagChipView)

        tagView.setTags(
            listOf(
                TagItem(1, "Android"),
                TagItem(2, "Kotlin"),
                TagItem(3, "Jetpack"),
                TagItem(4, "Compose")
            )
        ) { selected ->
            Log.d("TAG", "Selected: $selected")
        }
        tagView.updateStyle(
            ChipStyle(
                selectedBgColor = Color.parseColor("#6200EE"),
                unselectedBgColor = Color.LTGRAY,
                selectedTextColor = Color.WHITE,
                unselectedTextColor = Color.BLACK,
                selectedBackground = ChipBackground(
                    cornerRadius = 30f,
                    strokeWidth = 2,
                    strokeColor = Color.WHITE
                ),
                unselectedBackground = ChipBackground(
                    cornerRadius = 60f,
                    strokeWidth = 2,
                    strokeColor = Color.DKGRAY
                )
            )
        )


    }
}