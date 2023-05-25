package com.vladislaviliev.shopdemo.ui.list_widgets;

import android.view.View;

import androidx.annotation.NonNull;

class ViewHolderPurchase extends ViewHolderAbstract {

    private final PurchaseClickListener clickListener;

    ViewHolderPurchase(@NonNull final View itemView, @NonNull final PurchaseClickListener clickListener) {
        super(itemView);
        this.clickListener = clickListener;
        itemView.findViewById(android.R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull final View v) {
        clickListener.onRemoveClicked(itemId);
    }
}