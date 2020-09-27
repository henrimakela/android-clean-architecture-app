package fi.henrimakela.clean_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.henrimakela.clean_app.R
import fi.henrimakela.domain.ChordProgression

class ChordProgressionAdapter(private var progressions: List<ChordProgression>, private val listener: ProgressionSelectionListener) : RecyclerView.Adapter<ChordProgressionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.progression_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return progressions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(progressions[position], listener)
    }

    fun setItems(progressions: List<ChordProgression>){
        this.progressions = progressions
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)

        fun bind(chordProgression: ChordProgression, listener: ProgressionSelectionListener){
            title.text = chordProgression.style
            subtitle.text = chordProgression.progression

            itemView.setOnClickListener{
                listener.progressionSelected(chordProgression)
            }

            itemView.setOnLongClickListener {
                listener.addToFavourites(chordProgression)
            }
        }
    }
}