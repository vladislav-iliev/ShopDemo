package com.vladislaviliev.shopdemo.ui.home

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.vladislaviliev.shopdemo.R
import com.vladislaviliev.shopdemo.ui.DaoVmFactory
import com.vladislaviliev.shopdemo.ui.list_widgets.AdapterHome
import com.vladislaviliev.shopdemo.ui.CartVm
import com.vladislaviliev.shopdemo.ui.list_widgets.HomeClickListener

class HomeFragment : Fragment(), MenuProvider, HomeClickListener {

    private val vm: HomeVm by viewModels { DaoVmFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(this, viewLifecycleOwner)

        val adapter = AdapterHome(this)
        view.findViewById<RecyclerView>(R.id.home_recycler).adapter = adapter
        vm.allShorts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            requireView().findViewById<View>(R.id.loading_group).visibility = View.GONE
        }
        vm.getAllShorts()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_home, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.add) {
            NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToCreateFragment())
            return true
        }
        NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToPurchaseFragment())
        return true
    }

    override fun onDetailsClicked(id: Int) {
        NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToViewFragment(id))
    }

    override fun onAddToCartClicked(id: Int) {
        val cartVm: CartVm by activityViewModels()
        cartVm.addToCart(id)
    }
}