package com.studio.hub.table4app.app.ui.activity

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.studio.hub.table4app.R

import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso
import android.graphics.Bitmap
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import com.squareup.picasso.Target
import com.studio.hub.table4app.app.ui.component.reservation_date.ToggleExpandLayout
import com.studio.hub.table4app.app.ui.utils.BlurImage
import kotlinx.android.synthetic.main.content_detail_restaurant.*
import kotlinx.android.synthetic.main.activity_detail_restaurant.*
import android.R.attr.duration
import com.studio.hub.table4app.R.id.view
import android.animation.ObjectAnimator
import android.support.design.widget.CoordinatorLayout
import com.studio.hub.table4app.app.ui.utils.LogUtils


class DetailRestaurantActivity : AppCompatActivity() {
    private val BLUR_PRECENTAGE = 10
    private val IMAGE_URL = "https://www.appgoby.com/wp-content/uploads/2018/05/1-1.jpg"

    var dateReservation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_restaurant)
        setSupportActionBar(toolbar)
        initUI()
    }


    fun initUI() {
        initPickerReserve()
        testImageBluer()

        collapsing_toolbar.title = "Holi"
        collapsing_toolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsing_toolbar.setCollapsedTitleTextAppearance(R.style.ColapseAppBar)

        app_bar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            Log.v(" DETAIL", verticalOffset.toString())
            Log.v(" DETAIL -- ", "" + collapsing_toolbar.height)
            Log.v(" DETAIL toolbar -- ", "" + toolbar.height)
            Log.v(" DETAIL toolbar * -- ", "" + toolbar.height*2)
            if (verticalOffset <= -(toolbar.height*2.7)) {
                tviTileToolbar.visibility = View.VISIBLE
            } else if (verticalOffset >= -(toolbar.height*2.7)) {
                tviTileToolbar.visibility = View.GONE
            }
        }

        toolbar.setNavigationOnClickListener { onBackPressed() }

        llaDateReservation.setOnClickListener {
            if (!dateReservation){
                app_bar.setExpanded(false, true)
                dateReservation = true
                rlaReservationDate.open()
            }else{
                dateReservation = false
                rlaReservationDate.close()
            }
        }

        llaContent.setOnClickListener {
            if (dateReservation){
                dateReservation = false
                rlaReservationDate.close()
            }
        }

        rlaReservationDate.setOnToggleTouchListener(object : ToggleExpandLayout.OnToggleTouchListener {
            override fun onStartOpen(height: Int, originalHeight: Int) {}

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onOpen() {
                val childCount = rlaReservationDate.childCount
                for (i in 0 until childCount) {
                    val view: View = rlaReservationDate.getChildAt(i)
                    view.elevation = dp2px(1f)
                }
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onStartClose(height: Int, originalHeight: Int) {
                val childCount = rlaReservationDate.childCount
                for (i in 0 until childCount) {
                    val view = rlaReservationDate.getChildAt(i)
                    view.elevation = dp2px(i.toFloat())
                }
            }

            override fun onClosed() {

            }
        })

    }

    fun dp2px(dp: Float): Float {
        val scale = resources.displayMetrics.density
        return dp * scale + 0.5f
    }

    fun testImageBluer(){

        val target = object : Target {
            override  fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                iviBackground.setImageBitmap(BlurImage().fastblur(bitmap, 1f, BLUR_PRECENTAGE))
            }

            override fun onBitmapFailed(errorDrawable: Drawable) {
                iviBackground.setImageResource(R.mipmap.ic_launcher)

            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable) {

            }
        }

        iviBackground.tag = target
        Picasso.with(this)
            .load(R.drawable.test_restaurante_img)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(target)

    }


    /**
     * Datepicker Reserva
     */
    val values = arrayOf("2 per.", "3 per.", "4 per.", "5 per.", "6 per.")
    val valuesDay = arrayOf("Hoy.", "Mañana", "Lunes ", "Martes", "Miercoles")
    val valuesHours = arrayOf("12:00", "12:30", "13:00 ", "13:30", "14:00")
    fun initPickerReserve(){
        npiPerson.minValue = 0
        npiPerson.maxValue = values.size-1
        npiPerson.displayedValues = values
        npiPerson.wrapSelectorWheel = false
        npiPerson.setOnValueChangedListener { picker, oldVal, newVal ->
            tviReserveDate.text = "Mesa para ${values[newVal]} hoy a las 20:00"
            LogUtils().v(" Picker_", values[newVal])
        }

        npiDay.minValue = 0
        npiDay.maxValue = valuesDay.size-1
        npiDay.displayedValues = valuesDay
        npiDay.wrapSelectorWheel = false
        npiDay.setOnValueChangedListener { picker, oldVal, newVal ->

        }

        npiHour.minValue = 0
        npiHour.maxValue = valuesHours.size-1
        npiHour.displayedValues = valuesHours
        npiHour.wrapSelectorWheel = false
        npiHour.setOnValueChangedListener { picker, oldVal, newVal ->

        }

    }
}
