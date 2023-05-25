package com.vladislaviliev.shopdemo.ui.list_widgets;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vladislaviliev.shopdemo.R;

class ViewHolderHome extends ViewHolderAbstract {

    private final HomeClickListener clickListener;

    ViewHolderHome(@NonNull final View itemView, @NonNull final HomeClickListener clickListener) {
        super(itemView);
        this.clickListener = clickListener;
        itemView.findViewById(android.R.id.button1).setOnClickListener(this);
        itemView.findViewById(android.R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull final View v) {
        if (v.getId() == android.R.id.button1) {
            clickListener.onDetailsClicked(itemId);
            return;
        }
        clickListener.onAddToCartClicked(itemId);
    }
}