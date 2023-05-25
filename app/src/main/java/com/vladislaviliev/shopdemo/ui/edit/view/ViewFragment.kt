package com.vladislaviliev.shopdemo.ui.edit.view

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vladislaviliev.shopdemo.R
import com.vladislaviliev.shopdemo.ui.CartVm
import com.vladislaviliev.shopdemo.ui.DaoVmFactory
import com.vladislaviliev.shopdemo.ui.edit.EditFragment

class ViewFragment : EditFragment(), MenuProvider {

    private val vm: ViewVm by viewModels { DaoVmFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_edit, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(this, viewLifecycleOwner)

        view.findViewById<View>(R.id.label_id).visibility = View.VISIBLE
        view.findViewById<View>(R.id.id).visibility = View.VISIBLE
        view.findViewById<View>(R.id.label_date).visibility = View.VISIBLE
        view.findViewById<View>(R.id.date).visibility = View.VISIBLE

        vm.item.observe(viewLifecycleOwner) { onLoaded(it) }
        vm.loading.observe(viewLifecycleOwner) {
            view.findViewById<View>(R.id.loading_group).visibility = if (it) View.VISIBLE else View.GONE
        }
        val navArgs: ViewFragmentArgs by navArgs()
        if (!vm.item.isInitialized) vm.load(navArgs.uid)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_view, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (vm.loading.value!!) {
            Toast.makeText(requireContext(), getString(R.string.loading_please_wait), Toast.LENGTH_LONG).show()
            return true
        }
        val itemId = vm.item.value!!.id
        if (menuItem.itemId == R.id.delete) {
            val cartVm: CartVm by activityViewModels()
            cartVm.removeFromCart(itemId)
            vm.delete(itemId)
            Toast.makeText(requireContext(), getString(R.string.deleted), Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
            return true
        }
        val newItem = constructItemFromViews()
        if (newItem == null) {
            Toast.makeText(requireContext(), getString(R.string.invalid_input), Toast.LENGTH_LONG).show()
            return true
        }
        vm.update(newItem)
        Toast.makeText(requireContext(), getString(R.string.updated), Toast.LENGTH_LONG).show()
        return true
    }
}