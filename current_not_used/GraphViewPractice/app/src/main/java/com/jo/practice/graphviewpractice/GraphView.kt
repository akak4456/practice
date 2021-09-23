package com.jo.practice.graphviewpractice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class GraphView @JvmOverloads constructor(context: Context, attrs:AttributeSet? =null, defStyleAttr:Int = 0) : View(context,attrs,defStyleAttr) {
    var graphLineColor:Int = -1
    var dateTextColor:Int = -1
    var dateTextSize:Float = 0.0f
    var dateTextMarginBottom:Float = 0.0f
    var dateTextList = ArrayList<String>()
    val circleColors = ArrayList<Int>()
    var entireDate = 0
    var dotData = ArrayList<Pair<Int,Int>>()
    var graphColor = -1
    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.GraphView,
            0,0
        ).apply {
            try{
                graphLineColor = getColor(R.styleable.GraphView_graphLineColor,-1)
                dateTextColor = getColor(R.styleable.GraphView_dateTextColor,-1)
                dateTextSize = getDimension(R.styleable.GraphView_dateTextSize,0.0f)
                dateTextMarginBottom = getDimension(R.styleable.GraphView_dateTextMarginBottom,0.0f)
                circleColors.add(getColor(R.styleable.GraphView_firstCircleColor,-1))
                circleColors.add(getColor(R.styleable.GraphView_secondCircleColor,-1))
                circleColors.add(getColor(R.styleable.GraphView_thirdCircleColor,-1))
                circleColors.add(getColor(R.styleable.GraphView_fourthCircleColor,-1))
                circleColors.add(getColor(R.styleable.GraphView_fifthCircleColor,-1))
                graphColor = getColor(R.styleable.GraphView_graphColor,-1)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val width = measuredWidth + 0.0f
        val height = measuredHeight + 0.0f

        val graphLineHeight = height * 0.7f

        val graphLineMarginTop = dpToPx(context,10.0f)

        val graphLinePaint = Paint()
        graphLinePaint.color = graphLineColor
        graphLinePaint.strokeWidth = dpToPx(context,1.0f)
        graphLinePaint.style = Paint.Style.STROKE

        val dateTextPaint = Paint()
        dateTextPaint.color = dateTextColor
        dateTextPaint.textSize = dateTextSize
        dateTextPaint.textAlign = Paint.Align.CENTER

        val lineStartXPos = width * 0.14f
        val lineEndXPos = width * 0.86f

        for(i in 0 until dateTextList.size){
            val betweenWidthLineStartAndLineEnd = lineEndXPos - lineStartXPos
            val lineAdditionalXPos = betweenWidthLineStartAndLineEnd / 6.0f * i
            canvas?.drawLine(lineStartXPos+lineAdditionalXPos,graphLineMarginTop,lineStartXPos+lineAdditionalXPos,graphLineMarginTop+graphLineHeight,graphLinePaint)
            canvas?.drawText(dateTextList[i],lineStartXPos+lineAdditionalXPos,height-dateTextMarginBottom,dateTextPaint)
        }


        val circleStartY = graphLineMarginTop + graphLineHeight * 0.1f
        val circleEndY = graphLineMarginTop+graphLineHeight * 0.9f
        for(i in 0 until circleColors.size){
            val circlePaint = Paint()
            circlePaint.style = Paint.Style.FILL
            circlePaint.color = circleColors[i]
            val extraYPos = (circleEndY-circleStartY) / 4.0f * i
            canvas?.drawCircle(width*0.08f,circleStartY+extraYPos,dpToPx(context,5.0f),circlePaint)
        }

        val dotXArr = ArrayList<Float>()
        val dotYArr = ArrayList<Float>()

        if(entireDate != 0){
            for((idx,dot) in dotData.withIndex()){
                val dotX = lineStartXPos + (lineEndXPos - lineStartXPos) / ((entireDate-1) * 1.0f) * (dot.first-1)
                val dotY = graphLineMarginTop + graphLineHeight * (dot.second/100.0f)

                dotXArr.add(dotX)
                dotYArr.add(dotY)


            }
            for(i in 0 until dotData.size - 1){
                val linePaint = Paint()
                linePaint.color = graphColor
                linePaint.strokeWidth = dpToPx(context,2.0f)
                linePaint.style = Paint.Style.STROKE

                canvas?.drawLine(dotXArr[i],dotYArr[i],dotXArr[i+1],dotYArr[i+1],linePaint)
            }

            for((idx,dot) in dotData.withIndex()){
                val dotX = lineStartXPos + (lineEndXPos - lineStartXPos) / ((entireDate-1) * 1.0f) * (dot.first-1)
                val dotY = graphLineMarginTop + graphLineHeight * (dot.second/100.0f)

                val circlePaint = Paint()
                circlePaint.style = Paint.Style.STROKE
                circlePaint.strokeWidth = dpToPx(context,2.0f)
                circlePaint.color = graphColor

                val circleFillPaint = Paint()
                circleFillPaint.style = Paint.Style.FILL
                circleFillPaint.color = Color.WHITE
                canvas?.drawCircle(dotX,dotY,dpToPx(context,2.5f),circleFillPaint)
                canvas?.drawCircle(dotX,dotY,dpToPx(context,2.5f),circlePaint)
            }
        }
    }

    fun dpToPx(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
    }

    fun addAllDateTextList(list:ArrayList<String>){
        dateTextList.clear()
        dateTextList.addAll(list)
    }

    fun putGraphDotOnGraph(entireDate:Int,dotData:ArrayList<Pair<Int,Int>>){
        this.entireDate = entireDate
        this.dotData.clear()
        this.dotData.addAll(dotData)
    }
}