package site.pnpl.igotit.view.home.favorites_courses

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.pnpl.igotit.databinding.ItemFavoritesCoursesBinding
import site.pnpl.igotit.domain.models.FavoritesCourses

class FavoritesCoursesAdapter(
    private val onImgClick: (id: Long) -> Unit,
) : RecyclerView.Adapter<FavoritesCoursesAdapter.InnerFavoritesCoursesViewHolder>() {
    private var favoritesCourses: MutableList<FavoritesCourses> = mutableListOf()

    inner class InnerFavoritesCoursesViewHolder(binding: ItemFavoritesCoursesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var root = binding.root
        var title = binding.title
        var description = binding.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerFavoritesCoursesViewHolder {
        val binding = ItemFavoritesCoursesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return InnerFavoritesCoursesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerFavoritesCoursesViewHolder, position: Int) {
        holder.title.text = favoritesCourses[position].title
        holder.description.text = favoritesCourses[position].description

        holder.title.setOnClickListener {
            println("FavoritesCoursesAdapter onImgClick: id = ${favoritesCourses[position].title}")
        }
    }

    override fun getItemCount(): Int  = favoritesCourses.size


    @SuppressLint("NotifyDataSetChanged")
    fun setData(title: List<FavoritesCourses>){
        this.favoritesCourses = title.toMutableList()
        notifyDataSetChanged()
    }
}
