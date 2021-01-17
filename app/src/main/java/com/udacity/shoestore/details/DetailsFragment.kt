package com.udacity.shoestore.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    val model: ShoeViewModel by activityViewModels()
    lateinit var binding: FragmentDetailsBinding
    lateinit var detailsViewModel: DetailsViewModel
    private var canSaveShoe: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        binding.detailsViewModel = detailsViewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)

        detailsViewModel.canSaveShoe.observe(viewLifecycleOwner, Observer {
            if(it == true){
                canSaveShoe = true
                activity?.invalidateOptionsMenu()
            }
        })

        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item: MenuItem = menu.findItem(R.id.save_shoe);
        if (canSaveShoe) {
            item.setVisible(true);
        } else {
            item.setVisible(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_shoe -> {
                model.addShoe(detailsViewModel.getShoe())
                findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToListingFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}