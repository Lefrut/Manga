package com.dashkevich.choice_genres.adapter

import android.graphics.drawable.Drawable
import com.dashkevich.utility.adapter.AdapterItemDelegate

class GenresItemDelegate(
    val title: String,
    val icon: Drawable
): AdapterItemDelegate {
    override fun id(): Any = title

    override fun content(): Any = this

    override fun payload(newItem: Any): AdapterItemDelegate.Payload {
        if(newItem is GenresItemDelegate){
            if(title != newItem.title){
                return GenresPayloads.TitleChanged(newItem.title)
            }
            else if(icon != newItem.icon){
                return GenresPayloads.IconChanged(newItem.icon)
            }
        }
        return AdapterItemDelegate.Payload.None
    }

    sealed class GenresPayloads: AdapterItemDelegate.Payload {
        class TitleChanged(val title: String) : GenresPayloads()
        class IconChanged(val icon: Drawable) : GenresPayloads()
    }

    override fun equals(other: Any?): Boolean {
        if (other is GenresItemDelegate) {
            return (title == other.title && icon == other.icon)
        }
        return false
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + icon.hashCode()
        return result
    }

}