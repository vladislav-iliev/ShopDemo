package com.vladislaviliev.shopdemo.ui.edit.create

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import com.vladislaviliev.shopdemo.App
import com.vladislaviliev.shopdemo.R
import com.vladislaviliev.shopdemo.db.Category
import com.vladislaviliev.shopdemo.db.Item
import com.vladislaviliev.shopdemo.ui.DaoVmFactory
import com.vladislaviliev.shopdemo.ui.edit.EditFragment
import java.math.BigDecimal
import java.time.LocalDate

class CreateFragment : EditFragment(), MenuProvider {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_edit, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
        onLoaded(Item(0, "", Category.CASUAL, "", "", BigDecimal.ZERO, LocalDate.now(), App.getImageUri("casual.jpg")))
        view.findViewById<View>(R.id.loading_group).visibility = View.GONE
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_create, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        val newItem = constructItemFromViews()
        if (newItem == null) {
            Toast.makeText(requireContext(), getString(R.string.invalid_input), Toast.LENGTH_LONG).show()
            return true
        }
        val vm: CreateVm by viewModels { DaoVmFactory() }
        vm.create(newItem)
        Toast.makeText(requireContext(), getString(R.string.created), Toast.LENGTH_LONG).show()
        return true
    }
}