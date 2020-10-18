package com.dikiy.workshop.ui.views.custom.barGraphic.ui

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import java.util.ArrayList
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Paint
import com.dikiy.workshop.R

//Bar Graph Example
class GraphicsView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    private val contentWidthSize = 0
    private val contentHeightSize = 0
    private val cells: MutableList<RectF> = ArrayList()
    private val values: MutableList<Int> = ArrayList()
    private val paint = Paint()

    private var finalWidthSize = 0
    private var finalHeightSize = 0
    private var paddingBetweenValuesAssigned = 0f

    private lateinit var color: ColorStateList

    init {
        Log.d("Logs", "Constructor is called")

        val paddingBetweenValuesDefault = 0f
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.GraphicsView)

        paddingBetweenValuesAssigned =
                if (typedArray.hasValue(R.styleable.GraphicsView_paddingsBetweenValues))
                    typedArray.getDimension(R.styleable.GraphicsView_paddingsBetweenValues, paddingBetweenValuesDefault)
                else paddingBetweenValuesDefault
        Log.d("Logs", "Padding between graphic's values is: $paddingBetweenValuesAssigned")

        if (typedArray.hasValue(R.styleable.GraphicsView_barColor))
            color = typedArray.getColorStateList(R.styleable.GraphicsView_barColor)!!

        typedArray.recycle()

        paint.color = color.defaultColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d("Logs", "OnMeasure method is called")
        finalWidthSize = calculateSize(contentWidthSize, widthMeasureSpec)
        finalHeightSize = calculateSize(contentHeightSize, heightMeasureSpec)
        setMeasuredDimension(finalWidthSize, finalHeightSize)
        calculateGraphics(values)
    }

    override fun onDraw(canvas: Canvas) {
        for (i in cells.indices) canvas.drawRect(cells[i], paint)
    }

    private fun calculateSize(contentSize: Int, measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        return when (mode) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.AT_MOST ->
                if (contentSize < specSize) contentSize
                else specSize
            MeasureSpec.UNSPECIFIED -> contentSize
            else -> contentSize
        }
    }

    fun setValues(values: List<Int>) {
        this.values.addAll(values)
    }

    private fun calculateGraphics(values: List<Int>) {
        //Ширина ячейки с учётом отступов справа, слева и между ячейками
        val cellWidth = (finalWidthSize - paddingRight - paddingLeft
                - paddingBetweenValuesAssigned * (values.size - 1)) / values.size
        val cellHeightRatio = (finalHeightSize - paddingBottom - paddingTop) / values.max()!!
        for (i in values.indices)
            cells.add(RectF(
                    paddingRight + (i * (cellWidth + paddingBetweenValuesAssigned)),
                    (finalHeightSize - values[i] * cellHeightRatio).toFloat() - paddingBottom,
                    paddingRight + (i * (cellWidth + paddingBetweenValuesAssigned) + cellWidth),
                    finalHeightSize.toFloat() - paddingBottom)
            )
        invalidate()
    }
}
