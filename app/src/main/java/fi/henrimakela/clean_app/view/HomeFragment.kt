package fi.henrimakela.clean_app.view

import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fi.henrimakela.clean_app.R
import fi.henrimakela.clean_app.adapter.ChordProgressionAdapter
import fi.henrimakela.clean_app.adapter.ProgressionSelectionListener
import fi.henrimakela.clean_app.viewmodel.ChordViewModel
import fi.henrimakela.domain.ChordProgression
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), ProgressionSelectionListener {

    private lateinit var adapter: ChordProgressionAdapter
    private lateinit var viewModel: ChordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(ChordViewModel::class.java)

        viewModel.progressions.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })

    }

    private fun setupRecyclerView() {
        adapter = ChordProgressionAdapter(emptyList(), this)
        progressions_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        progressions_recycler_view.adapter = adapter
    }

    override fun addToFavourites(progression: ChordProgression): Boolean {
        viewModel.addProgressionToFavorites(progression)
        return true
    }

    override fun progressionSelected(progression: ChordProgression){
        Toast.makeText(requireContext(), progression.progression, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.chord_progression_list_menu, menu)
        val favoritesCheckBox = menu.findItem(R.id.showFavorites).actionView as CheckBox
        favoritesCheckBox.setButtonDrawable(R.drawable.heart)

        favoritesCheckBox.setOnCheckedChangeListener { _, checked ->

            if(checked){
                viewModel.favourites.value?.let {
                    adapter.setItems(it)
                }
            }

            else{
                viewModel.progressions.observe(this, Observer {
                    adapter.setItems(it)
                })
            }
        }

    }



}