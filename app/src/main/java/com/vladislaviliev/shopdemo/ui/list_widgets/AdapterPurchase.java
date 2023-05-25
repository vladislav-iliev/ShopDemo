package com.vladislaviliev.shopdemo.ui.list_widgets;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.vladislaviliev.shopdemo.R;
import com.vladislaviliev.shopdemo.db.ItemShort;

public class AdapterPurchase extends ListAdapter<ItemShort, ViewHolderAbstract> {

    private final PurchaseClickListener clickListener;

    public AdapterPurchase(@NonNull final PurchaseClickListener clickListener) {
        super(new ListDiff());
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolderAbstract onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new ViewHolderPurchase(
                LayoutInflater.from(parent.getContext()).inflate((R.layout.item_short_purchase), parent, false),
                clickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderAbstract holder, final int position) {
        holder.bind(getItem(position));
    }
}
