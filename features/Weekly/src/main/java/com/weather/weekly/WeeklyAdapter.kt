package com.weather.weekly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.weather.models.Icon
import com.weather.weekly.viewmodel.state.DailyWeatherState
import kotlinx.android.synthetic.main.item_day.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class WeeklyAdapter(private val weather: List<DailyWeatherState>) : RecyclerView.Adapter<WeeklyDayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyDayViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_day, parent, false)

        return WeeklyDayViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: WeeklyDayViewHolder, position: Int) {
        viewHolder.update(this.weather[position])
    }

    override fun getItemCount() = weather.size
}

class WeeklyDayViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
    private val tv_title = layout.tv_weekly_item_title
    private val tv_subtitle = layout.tv_weekly_item_subtitle
    private val iv_icon = layout.iv_weekly_item_icon

    private val format = SimpleDateFormat("EEEE d", Locale.FRENCH)

    fun update(weatherDay: DailyWeatherState) {
        tv_title.text = format.format(weatherDay.date)
        tv_subtitle.text = weatherDay.summary

        val icon = when (weatherDay.icon) {
            Icon.CLEAR_DAY -> R.drawable.ic_clear_day
            Icon.CLEAR_NIGHT -> R.drawable.ic_clear_night
            Icon.RAIN -> R.drawable.ic_cloud_rain
            Icon.SNOW -> R.drawable.ic_snow
            Icon.SLEET -> R.drawable.ic_snow
            Icon.WIND -> R.drawable.ic_wind
            Icon.FOG -> R.drawable.ic_fog
            Icon.CLOUDY -> R.drawable.ic_cloud
            Icon.PARTLY_CLOUDY_DAY -> R.drawable.ic_partly_cloudy
            Icon.PARTLY_CLOUDY_NIGHT -> R.drawable.ic_partly_cloudy_night
            else -> null
        }

        icon?.let { drawableId ->
            iv_icon.setImageDrawable(ContextCompat.getDrawable(iv_icon.context, drawableId))
        }
    }

}