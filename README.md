## TagChipSelector — Android Tag / Chip Selector Library
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![API](https://img.shields.io/badge/API-24%2B-orange)](#)
---

**TagChipSelector** is a lightweight, flexible, XML-driven Tag / Chip selector for Android with:

✅ Multi-select / single-select

✅ Removable chips

✅ Dynamic colors

✅ Fully customizable background (radius, stroke)

✅ API / pagination friendly

✅ Kotlin & XML support

✅ No Material dependency

---

## Preview

---

## XML Attributes Reference

### Behavior Attributes

| Attribute | Type | Description |
|----------|------|-------------|
| `chipMultiSelect` | boolean | Allow multiple chips to be selected (default: `true`) |
| `chipRemovable` | boolean | Show remove (❌) icon on selected chips (default: `true`) |

---

### Color Attributes

| Attribute | Type | Description |
|----------|------|-------------|
| `chipSelectedBgColor` | color | Background color of selected chip (default: `#2196F3`) |
| `chipUnselectedBgColor` | color | Background color of unselected chip (default: `#E0E0E0`) |
| `chipSelectedTextColor` | color | Text color of selected chip (default: `#FFFFFF`) |
| `chipUnselectedTextColor` | color | Text color of unselected chip (default: `#000000`) |

---

### Common Background Attributes  
_Applies to both selected & unselected chips_

| Attribute | Type | Description |
|----------|------|-------------|
| `chipCornerRadius` | dimension | Corner radius for chips (default: `20dp`) |
| `chipStrokeWidth` | dimension | Border width (default: `0dp`) |
| `chipStrokeColor` | color | Border color (default: `Transparent`) |

---

### Selected Chip Background Overrides

| Attribute | Type | Description |
|----------|------|-------------|
| `chipSelectedCornerRadius` | dimension | Corner radius of selected chip (default: `chipCornerRadius`) |
| `chipSelectedStrokeWidth` | dimension | Border width of selected chip (default: `chipStrokeWidth`) |
| `chipSelectedStrokeColor` | color | Border color of selected chip (default: `chipStrokeColor`) |

---

### Unselected Chip Background Overrides

| Attribute | Type | Description |
|----------|------|-------------|
| `chipUnselectedCornerRadius` | dimension | Corner radius of unselected chip (default: `chipCornerRadius`) |
| `chipUnselectedStrokeWidth` | dimension | Border width of unselected chip (default: `chipStrokeWidth`) |
| `chipUnselectedStrokeColor` | color | Border color of unselected chip (default: `chipStrokeColor`) |

---

## Attribute Priority Rule (Important)

Specific attributes override common ones.

1. chipSelected* / chipUnselected*
2. chipCornerRadius / chipStroke*
3. Default values

**Example**
```
app:chipCornerRadius="20dp"
app:chipSelectedCornerRadius="40dp"
// Selected chip uses 40dp
 Unselected chip uses 20dp
```
---

## Basic Usage

1️⃣ Add TagChipView in XML

```xml
<com.ext.tagchip.TagChipView
    android:id="@+id/tagChipView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

2️⃣ Set Tag Data from Kotlin (Required)

```kotlin
tagView.setTags(
    listOf(
        TagItem(1, "Android"),
        TagItem(2, "Kotlin"),
        TagItem(3, "Jetpack")
    )
) { selected ->
    // Selected tags callback
}
```

## Data Coming from API (MOST IMPORTANT)

**Correct Way (Recommended)**

Keep data outside the library (ViewModel / Activity).
```
private val tagList = mutableListOf<TagItem>()
```
**On API response:**
```
fun onApiSuccess(apiTags: List<TagItem>) {
    tagList.addAll(apiTags)

    tagView.setTags(tagList) { selected ->
        Log.d("TAG", "Selected: $selected")
    }
}
```

## Pagination / Infinite Data

**Initial load:**
```kotlin
tagView.setTags(emptyList()) { selected -> }
```
**Load more:**
```kotlin
tagView.addTags(nextPageFromApi)
```
---

## Getting Selected Tags Anytime

**From callback (recommended):**
```
tagView.setTags(tags) { selected ->
    val selectedIds = selected.map { it.id }
}
```

## Sharing Selected Tags to Another Activity

**Best Practice: Pass IDs Only**

```
val selectedIds = selected.map { it.id }

val intent = Intent(this, SummaryActivity::class.java)
intent.putIntegerArrayListExtra(
    "selected_ids",
    ArrayList(selectedIds)
)
startActivity(intent)
```

**In next activity:**
```
val selectedIds =
    intent.getIntegerArrayListExtra("selected_ids") ?: emptyList()
```

## Storing Selection in Database

**SharedPreferences**
```
val selectedIds = selected.map { it.id }

prefs.edit()
    .putString("selected_tags", selectedIds.joinToString(","))
    .apply()
```

**Room**
```
@Entity
data class SelectedTagEntity(
    @PrimaryKey val tagId: Int
)
```
---

## Runtime Style Update (Optional)

**Overrides XML at runtime:**

```
tagView.updateStyle(
    ChipStyle(
        selectedBgColor = Color.RED,
        unselectedBgColor = Color.LTGRAY,
        selectedTextColor = Color.WHITE,
        unselectedTextColor = Color.BLACK
    )
)
```

## License

```
MIT License

Copyright (c) 2025 Excelsior Technologies 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```





