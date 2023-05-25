package com.vladislaviliev.shopdemo.ui.edit

import android.view.View
import android.widget.*
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.vladislaviliev.shopdemo.App
import com.vladislaviliev.shopdemo.R
import com.vladislaviliev.shopdemo.db.Category
import com.vladislaviliev.shopdemo.db.Item
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

abstract class EditFragment : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        if (parent.id != R.id.spinner_img) return
        val rootView = requireView()
        val imgUri = App.getImageUri(parent.getItemAtPosition(position) as String)
        Glide.with(rootView).load(imgUri).placeholder(R.drawable.ic_image).into(rootView.findViewById(R.id.img))
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }

    protected fun constructItemFromViews(): Item? {
        val root = requireView()
        val id = root.findViewById<TextView>(R.id.id).text.toString().toInt()
        val name = root.findViewById<EditText>(R.id.name).text.toString()
        if (name.isBlank()) return null
        val category = Category.valueOf(root.findViewById<Spinner>(R.id.spinner_category).selectedItem as String)
        val descShort = root.findViewById<EditText>(R.id.descr_short).text.toString()
        if (descShort.isBlank()) return null
        val descLong = root.findViewById<EditText>(R.id.descr_long).text.toString()
        if (descLong.isBlank()) return null
        val priceText = root.findViewById<EditText>(R.id.price).text.toString()
        if (priceText.isBlank()) return null
        val price = try {
            BigDecimal(priceText).setScale(2, RoundingMode.HALF_EVEN)
        } catch (e: Exception) {
            null
        } ?: return null
        if (price.signum() < 0) return null

        val date = LocalDate.parse(root.findViewById<TextView>(R.id.date).text)
        val imgName = root.findViewById<Spinner>(R.id.spinner_img).selectedItem as String
        val imgUri = App.getImageUri(imgName)
        return Item(id, name, category, descShort, descLong, price, date, imgUri)
    }

    protected fun onLoaded(item: Item) {
        val root = requireView()
        Glide.with(root).load(item.imgUri).placeholder(R.drawable.ic_image).into(root.findViewById(R.id.img))
        root.findViewById<TextView>(R.id.id).text = item.id.toString()
        root.findViewById<EditText>(R.id.name).setText(item.name)
        root.findViewById<EditText>(R.id.descr_short).setText(item.shortDescription)
        root.findViewById<EditText>(R.id.descr_long).setText(item.longDescription)
        root.findViewById<EditText>(R.id.price).setText(item.price.toPlainString())
        root.findViewById<TextView>(R.id.date).text = item.date.toString()
        setSpinner(R.id.spinner_img, App.imagesEnum, item.imgUri.lastPathSegment!!)
        setSpinner(R.id.spinner_category, Category.values().map { it.toString() }.toTypedArray(), item.category.toString())
    }

    private fun setSpinner(@IdRes spinnerViewId: Int, items: Array<String>, toPreSelectFromArray: String) {
        val spinner = requireView().findViewById<Spinner>(spinnerViewId)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
            .apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        spinner.adapter = adapter
        spinner.setSelection(adapter.getPosition(toPreSelectFromArray))
        spinner.onItemSelectedListener = this
    }
}