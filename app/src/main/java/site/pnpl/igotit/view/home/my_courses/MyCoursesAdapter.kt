package site.pnpl.igotit.view.home.my_courses

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.pnpl.igotit.databinding.ItemMyCoursesBinding
import site.pnpl.igotit.domain.models.Clubs
import java.util.UUID

class MyCoursesAdapter(
    private val onClick: (uuidCourse: UUID) -> Unit,
) : RecyclerView.Adapter<MyCoursesAdapter.InnerMyCoursesViewHolder>() {
    private var myCourses: MutableList<Clubs> = mutableListOf()
//    private var myCourses: MutableList<MyCourses> = mutableListOf()

    inner class InnerMyCoursesViewHolder(binding: ItemMyCoursesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var root = binding.root
        var title = binding.titleMyCourses
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerMyCoursesViewHolder {
        val binding = ItemMyCoursesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerMyCoursesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerMyCoursesViewHolder, position: Int) {

        holder.title.text = myCourses[position].title

        holder.root.setOnClickListener {
            println("MyCoursesAdapter onClick: title = ${myCourses[position].title}")
            myCourses[position].uuid?.let { uuidCourse -> onClick(uuidCourse) }
        }
    }

    override fun getItemCount(): Int = myCourses.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(title:List<Clubs>){
        this.myCourses = title.filter { it.isMyCourse }.toMutableList()
        notifyDataSetChanged()
    }
}