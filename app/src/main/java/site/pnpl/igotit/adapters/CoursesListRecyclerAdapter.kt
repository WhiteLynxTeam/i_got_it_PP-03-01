package site.pnpl.igotit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import site.pnpl.igotit.R
import site.pnpl.igotit.holders.CoursesViewHolder
import site.pnpl.igotit.items.Courses

class CoursesListRecyclerAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Courses>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CoursesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.courses_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CoursesViewHolder -> {
                holder.bind(items[position])
                //Обрабатываем нажатие на весь элемент целиком
                holder.itemView.findViewById<FrameLayout>(R.id.courses_conteiner)
                    .setOnClickListener {
                        clickListener.click(items[position])
                    }
            }
        }

    }
    fun addItems(list: List<Courses>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(courses: Courses)
    }
}