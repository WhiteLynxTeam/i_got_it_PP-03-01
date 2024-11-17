package site.pnpl.igotit.view.mylesson

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.ItemLessonsBinding
import site.pnpl.igotit.domain.DATE_PATTERN_LESSON_IGOTIT
import site.pnpl.igotit.domain.DATE_PATTERN_WITH_ZONE
import site.pnpl.igotit.domain.models.Lesson
import site.pnpl.igotit.utils.toTextDateByFormat

class MyLessonAdapter(
    private val context: Context,
    private val onClick: (id: Long) -> Unit,
) : RecyclerView.Adapter<MyLessonAdapter.InnerLessonViewHolder>() {
    private var lessons: MutableList<Lesson> = mutableListOf()

    inner class InnerLessonViewHolder(binding: ItemLessonsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var root = binding.root
        var id = binding.tvId
        var date = binding.dateLesson
        var title = binding.titleLesson
        var comment = binding.comment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerLessonViewHolder {
        val binding = ItemLessonsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerLessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerLessonViewHolder, position: Int) {

        with(holder) {
            id.text = lessons[position].id.toString()
//            date.text = lessons[position].dateMilis.toTextDateByFormat("yyyy-MM-dd")
            date.text = lessons[position].dateMilis.toTextDateByFormat(DATE_PATTERN_LESSON_IGOTIT)
            title.text = lessons[position].title
            if (lessons[position].isActive) {
                comment.text = "Предстоящее"
                comment.isEnabled = true

                date.setTextColor(ContextCompat.getColor(context, R.color.active_lesson))
                title.setTextColor(ContextCompat.getColor(context, R.color.active_lesson))
            } else {
                comment.text = "Нет задания"
                comment.isEnabled = false

                date.setTextColor(ContextCompat.getColor(context, R.color.no_active_lesson))
                title.setTextColor(ContextCompat.getColor(context, R.color.no_active_lesson))
            }

            title.setOnClickListener {
                lessons[position].id?.let { it1 -> onClick(it1) }
                println("LessonAdapter onImgClick: title id =  ${lessons[position].title}")
            }

            root.setOnClickListener {
                println("LessonAdapter onImgClick root id =  ${lessons[position].id}")
            }
        }
    }

    override fun getItemCount(): Int = lessons.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(lessons: List<Lesson>) {
        this.lessons = lessons.toMutableList()
        notifyDataSetChanged()
    }
}