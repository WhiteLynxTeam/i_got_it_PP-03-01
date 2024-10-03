package site.pnpl.igotit.holders

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.pnpl.igotit.R
import site.pnpl.igotit.items.Courses

//В конструктор класс передается layout, который мы создали(courses_item.xml)
class CoursesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    //Привязываем view из layout к переменным
    private val courses_title = itemView.findViewById<TextView>(R.id.courses_title)

    //В этом методе кладем данные из courses в наши view
    @SuppressLint("CheckResult")
    fun bind(courses: Courses){
        courses_title.text = courses.courses_title
        Glide.with(itemView)
            .load(courses.courses_title)
    }
}