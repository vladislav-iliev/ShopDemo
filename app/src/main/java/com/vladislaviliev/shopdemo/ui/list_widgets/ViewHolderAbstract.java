package com.vladislaviliev.shopdemo.ui.list_widgets;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vladislaviliev.shopdemo.R;
import com.vladislaviliev.shopdemo.db.ItemShort;

public abstract class ViewHolderAbstract extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ImageView img;
    private final TextView id;
    private final TextView name;
    private final TextView category;
    private final TextView description;
    private final TextView price;
    protected int itemId;

    ViewHolderAbstract(@NonNull final View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.item_img);
        id = itemView.findViewById(R.id.item_id);
        name = itemView.findViewById(R.id.item_name);
        category = itemView.findViewById(R.id.item_category);
        description = itemView.findViewById(R.id.item_description);
        price = itemView.findViewById(R.id.item_price);
    }

    void bind(@NonNull final ItemShort item) {
        itemId = item.getId();
        Glide.with(itemView).load(item.getImgUri()).placeholder(R.drawable.ic_image).into(img);
        id.setText(String.valueOf(item.getId()));
        name.setText(item.getName());
        category.setText(item.getCategory().toString());
        description.setText(item.getShortDescription());
        price.setText(item.getPrice().toPlainString());
    }
}