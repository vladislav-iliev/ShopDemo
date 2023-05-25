package com.vladislaviliev.shopdemo.ui.list_widgets;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.vladislaviliev.shopdemo.R;
import com.vladislaviliev.shopdemo.db.ItemShort;

public class AdapterHome extends ListAdapter<ItemShort, ViewHolderAbstract> {

    private final HomeClickListener clickListener;

    public AdapterHome(@NonNull final HomeClickListener clickListener) {
        super(new ListDiff());
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolderAbstract onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new ViewHolderHome(
                LayoutInflater.from(parent.getContext()).inflate((R.layout.item_short_home), parent, false),
                clickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderAbstract holder, final int position) {
        holder.bind(getItem(position));
    }
}
