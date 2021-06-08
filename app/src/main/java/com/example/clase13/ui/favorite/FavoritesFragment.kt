package com.example.clase13.ui.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clase13.OnClickListener
import com.example.clase13.R
import com.example.clase13.model.CovidCaseModel

import com.example.clase13.ui.covidCase.CovidRecyclerViewAdapter
import org.koin.android.ext.android.inject

class FavoritesFragment : Fragment(), OnClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CovidRecyclerViewAdapter
    private val viewModel: FavoritesViewModel by inject()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_covid_casses, container, false)
        adapter = CovidRecyclerViewAdapter(this)
        recyclerView = view.findViewById<RecyclerView>(R.id.covid_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
//        viewModel.favoritesCountries.observe(viewLifecycleOwner, Observer {
//            adapter.set(it)
//        })
        return view
    }

    override fun onClickItem(item: Any) {
        if (item is CovidCaseModel) {
//            viewModel.selectCase(item)
//            viewModel.navigator.navigateToDetail(item.country)
        }
    }

}