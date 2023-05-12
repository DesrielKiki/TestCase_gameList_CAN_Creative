package desriel.kiki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListGameAdapter(private val listGame: ArrayList<Game>): RecyclerView.Adapter<ListGameAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_gameName)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_gamePhoto)
        val tvRelease: TextView = itemView.findViewById(R.id.tv_releaseDate)
        val tvRating: TextView = itemView.findViewById(R.id.tv_rating)
        val tvDevDet: TextView = itemView.findViewById(R.id.tv_developerDetail)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_deskripsiGame)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_games,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listGame.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo, release, rating, developer, description) = listGame[position]
        holder.tvName.text = name
        holder.imgPhoto.setImageResource(photo)
        holder.tvRelease.text = release
        holder.tvRating.text = rating //double
        holder.tvDevDet.text = developer
        holder.tvDescription.text = description
    }
}