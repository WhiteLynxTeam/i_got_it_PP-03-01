package site.pnpl.igotit.view.courses.lessons

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.pnpl.igotit.databinding.ItemLessonsBinding
import site.pnpl.igotit.domain.models.Lesson
import site.pnpl.igotit.utils.toTextDateByFormat

class LessonAdapter(
    private val onImgClick: (id: Long) -> Unit,
) : RecyclerView.Adapter<LessonAdapter.InnerLessonViewHolder>() {
    private var lessons: MutableList<Lesson> = mutableListOf()

    inner class InnerLessonViewHolder(binding: ItemLessonsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var root = binding.root
        var id = binding.tvId
        var date = binding.dateLesson
        var title = binding.titleLesson
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerLessonViewHolder {
        val binding = ItemLessonsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerLessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerLessonViewHolder, position: Int) {

        holder.id.text = lessons[position].id.toString()
        holder.date.text = lessons[position].dateMilis.toTextDateByFormat("yyyy-MM-dd")
        holder.title.text = lessons[position].title

        holder.title.setOnClickListener{
            lessons[position].id?.let { it1 -> onImgClick(it1) }
            println("LessonAdapter onImgClick: title id =  ${lessons[position].title}")
        }

        holder.root.setOnClickListener {
            println("LessonAdapter onImgClick root id =  ${lessons[position].id}")
        }
     }

    override fun getItemCount(): Int = lessons.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(images: List<Lesson>) {
        this.lessons = images.toMutableList()
        notifyDataSetChanged()
    }
}