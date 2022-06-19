package com.olvera.stores_mvvm

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.olvera.stores_mvvm.databinding.FragmentEditStoreBinding
import com.olvera.stores_mvvm.mainModule.MainActivity

class EditStoreFragment : Fragment() {

    private lateinit var binding: FragmentEditStoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditStoreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as? MainActivity
        activity!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.title = getString(R.string.edit_store_title_add)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}