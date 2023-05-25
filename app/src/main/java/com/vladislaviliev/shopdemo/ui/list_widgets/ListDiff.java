package com.vladislaviliev.shopdemo.ui.list_widgets;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.vladislaviliev.shopdemo.db.ItemShort;

class ListDiff extends DiffUtil.ItemCallback<ItemShort> {
    @Override
    public boolean areItemsTheSame(@NonNull final ItemShort o, @NonNull final ItemShort n) {
        return o.getId() == n.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull final ItemShort o, @NonNull final ItemShort n) {
        return o.getCategory().equals(n.getCategory())
                && o.getName().equals(n.getName())
                && o.getShortDescription().equals(n.getShortDescription())
                && o.getImgUri().equals(n.getImgUri())
                && o.getPrice().equals(n.getPrice());
    }
}