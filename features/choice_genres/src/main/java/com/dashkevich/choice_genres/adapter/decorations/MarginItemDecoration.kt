package com.dashkevich.choice_genres.adapter.decorations

import android.graphics.Rect
import android.print.PrintAttributes.Margins
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MarginItemDecoration(
    private val orientation: Int = StaggeredGridLayoutManager.VERTICAL,
//    val startMargins: Margins,
//    val endMargins: Margins,
//    vararg spanMargin: Margins
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val itemPosition = parent.getChildAdapterPosition(view)
            if(orientation != VERTICAL || itemPosition == NO_POSITION){
                return
            }
        }
    }

}