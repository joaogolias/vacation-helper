package com.example.joaogolias.vacationhelper

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.tab_item.view.*
import android.support.v4.content.ContextCompat.getSystemService



class TabItem(context: Context, private val attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var icon: Drawable? = null

    init  {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.tab_item, this, true)

        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.TabItem)
        styledAttributes?.let {
            for(index in 0..(it.length() - 1)) {
                println("it.length: ${it.length()}")
                val iconInteger = styledAttributes.getIndex(index)
                println("iconInteger: $iconInteger")
                if(iconInteger.equals(R.styleable.TabItem_icon)) {
                    val iconReference = styledAttributes.getDrawable(R.styleable.TabItem_icon)
                    this.icon = iconReference
                    icon_image_view?.let {
                        icon_image_view.setImageDrawable(iconReference)
                    }
                }
            }
            it.recycle()
        }

    }
}