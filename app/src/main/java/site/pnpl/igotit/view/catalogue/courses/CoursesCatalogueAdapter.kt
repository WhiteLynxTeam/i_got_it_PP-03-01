package site.pnpl.igotit.view.catalogue.courses

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.pnpl.igotit.databinding.ItemCoursesCatalogueBinding
import site.pnpl.igotit.domain.models.CoursesCatalogue

class CoursesCatalogueAdapter() :
    RecyclerView.Adapter<CoursesCatalogueAdapter.InnerCoursesCatalogueViewHolder>() {
    private var coursesCatalogue: MutableList<CoursesCatalogue> = mutableListOf()

    inner class InnerCoursesCatalogueViewHolder(binding: ItemCoursesCatalogueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var title = binding.titleCourse
        var level = binding.level
        var numberClasses = binding.numberClasses
        var perWeek = binding.perWeek
        var duration = binding.duration
        var totalQuantity = binding.totalQuantity
        var description = binding.descriptionCourse

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerCoursesCatalogueViewHolder {
        val binding = ItemCoursesCatalogueBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return InnerCoursesCatalogueViewHolder(binding)
    }


    override fun onBindViewHolder(holder: InnerCoursesCatalogueViewHolder, position: Int) {
        holder.title.text = coursesCatalogue[position].title
        holder.level.text = coursesCatalogue[position].level
        holder.numberClasses.text = coursesCatalogue[position].numberClasses
        holder.perWeek.text = coursesCatalogue[position].perWeek
        holder.duration.text = coursesCatalogue[position].duration
        holder.totalQuantity.text = coursesCatalogue[position].totalQuantity
        holder.description.text = coursesCatalogue[position].description

    }

    override fun getItemCount(): Int = coursesCatalogue.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(title:List<CoursesCatalogue>){
        this.coursesCatalogue = title.toMutableList()
        notifyDataSetChanged()
    }
}