package campus.tech.kakao.map.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.map.R
import campus.tech.kakao.map.utility.Profile

class Adapter(private val profiles: MutableList<Profile>) : RecyclerView.Adapter<Adapter.ProfileViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(name: String, address: String, latitude: String, longitude: String)
    }

    var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        val tvType: TextView = itemView.findViewById(R.id.tvType)


        init {
            itemView.setOnClickListener {
                bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let { position ->
                    val profile = profiles[position]
                    listener?.onItemClick(profile.name, profile.address, profile.latitude, profile.longitude)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_view, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profiles[position]
        holder.tvName.text = profile.name
        holder.tvAddress.text = profile.address
        holder.tvType.text = profile.type
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    fun updateProfiles(newProfiles: List<Profile>) {
        profiles.clear()
        profiles.addAll(newProfiles)
        notifyDataSetChanged()
    }
}
