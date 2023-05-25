package com.vladislaviliev.shopdemo.ui.purchase

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.vladislaviliev.shopdemo.R
import com.vladislaviliev.shopdemo.ui.DaoVmFactory
import com.vladislaviliev.shopdemo.ui.list_widgets.AdapterPurchase
import com.vladislaviliev.shopdemo.ui.CartVm
import com.vladislaviliev.shopdemo.ui.list_widgets.PurchaseClickListener

class PurchaseFragment : Fragment(), MenuProvider,
    PurchaseClickListener {

    private val cartVm: CartVm by activityViewModels()
    private val purchaseVm: PurchaseVm by viewModels { DaoVmFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_purchase, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(this, viewLifecycleOwner)

        val adapter = AdapterPurchase(this)
        view.findViewById<RecyclerView>(R.id.purchase_recycler).adapter = adapter

        cartVm.cart.observe(viewLifecycleOwner) { purchaseVm.getShorts(it) }
        purchaseVm.loading.observe(viewLifecycleOwner) {
            view.findViewById<View>(R.id.loading_group).visibility = if (it) View.VISIBLE else View.GONE
        }
        purchaseVm.shorts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            view.findViewById<TextView>(R.id.purchase_price).text = it.sumOf { i -> i.price }.toPlainString()
            view.findViewById<View>(R.id.purchase_empty).visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_purchase, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (purchaseVm.loading.value!! || cartVm.cart.value!!.isEmpty()) {
            Toast.makeText(context, getString(R.string.cart_empty), Toast.LENGTH_LONG).show()
            return true
        }
        NavHostFragment.findNavController(this).navigate(PurchaseFragmentDirections.actionPurchaseFragmentToCredentialsDialog())
        return true
    }

    override fun onRemoveClicked(id: Int) {
        cartVm.removeFromCart(id)
    }
}