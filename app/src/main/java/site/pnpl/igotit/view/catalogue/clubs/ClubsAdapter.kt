package site.pnpl.igotit.view.catalogue.clubs
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.pnpl.igotit.databinding.ItemCoursesCatalogueBinding
import site.pnpl.igotit.domain.models.Clubs

class ClubsAdapter(
    private val onImgClick: (title:String) -> Unit,
): RecyclerView.Adapter<ClubsAdapter.InnerClubsViewHolder>() {
    private var clubs: MutableList<Clubs> = mutableListOf()

    inner class InnerClubsViewHolder(binding: ItemCoursesCatalogueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var root = binding.root
        var title = binding.titleCourse
        var level = binding.level
        var numberClasses = binding.numberClasses
        var perWeek = binding.perWeek
        var duration = binding.duration
        var totalQuantity = binding.totalQuantity
        var description = binding.descriptionCourse
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerClubsViewHolder {
        val binding = ItemCoursesCatalogueBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return InnerClubsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerClubsViewHolder, position: Int) {
        holder.title.text = clubs[position].title
        holder.level.text = clubs[position].level
        holder.numberClasses.text = clubs[position].numberClasses
        holder.perWeek.text = clubs[position].perWeek
        holder.duration.text = clubs[position].duration
        holder.totalQuantity.text = clubs[position].totalQuantity
        holder.description.text = clubs[position].description

        holder.root.setOnClickListener {
            onImgClick(clubs[position].title)
        }

    }

    override fun getItemCount(): Int  = clubs.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(title: List<Clubs>){
        this.clubs = title.toMutableList()
        notifyDataSetChanged()
    }
}