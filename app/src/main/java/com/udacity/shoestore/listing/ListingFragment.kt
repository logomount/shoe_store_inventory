package com.udacity.shoestore.listing

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentListingBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ListingFragment : Fragment() {

    lateinit var binding : FragmentListingBinding
    val model: ShoeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)
        setHasOptionsMenu(true)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
           activity?.finish()
        }

        model.shoes.observe(viewLifecycleOwner, Observer {shoes ->
            shoes.forEach{
                addShoeItem(inflater, container, it)
            }
        })

        binding.addShoeFab.setOnClickListener{
            findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailsFragment())
        }

        return binding.root
    }

    fun addShoeItem(inflater: LayoutInflater, container: ViewGroup?,  shoe : Shoe){
        val itemBinding : ItemShoeBinding = DataBindingUtil.inflate(inflater, R.layout.item_shoe, container, false)
        itemBinding.itemShoeName.text = shoe.name
        itemBinding.itemCompany.text = shoe.company
        itemBinding.itemDescription.text = shoe.description
        itemBinding.itemSize.text = shoe.size.toString()
        binding.shoeListLinearLayout.addView(itemBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                findNavController().navigate(ListingFragmentDirections.actionListingFragmentToLoginFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}