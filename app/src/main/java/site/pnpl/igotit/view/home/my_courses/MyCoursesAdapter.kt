package site.pnpl.igotit.view.home.my_courses

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.pnpl.igotit.databinding.ItemMyCoursesBinding
import site.pnpl.igotit.domain.models.MyCourses

class MyCoursesAdapter(
    private val onImgClick: (id: Long) -> Unit,
) : RecyclerView.Adapter<MyCoursesAdapter.InnerMyCoursesViewHolder>() {
    private var myCourses: MutableList<MyCourses> = mutableListOf()

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

        holder.title.setOnClickListener {
            println("MyCoursesAdapter onImgClick: id = ${myCourses[position].title}")
        }
    }

    override fun getItemCount(): Int = myCourses.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(title:List<MyCourses>){
        this.myCourses = title.toMutableList()
        notifyDataSetChanged()
    }
}