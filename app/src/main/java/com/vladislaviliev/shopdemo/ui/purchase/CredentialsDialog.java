package com.vladislaviliev.shopdemo.ui.purchase;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.vladislaviliev.shopdemo.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CredentialsDialog extends DialogFragment implements DialogInterface.OnClickListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        return new MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.enter_credentials))
                .setView(R.layout.dialog_credentials)
                .setPositiveButton(android.R.string.ok, this)
                .setNegativeButton(android.R.string.cancel, this)
                .create();
    }

    @Override
    public void onClick(@NonNull final DialogInterface dialog, final int which) {
        if (which == DialogInterface.BUTTON_NEGATIVE) {
            dismiss();
            return;
        }
        Toast.makeText(requireContext(), getString(R.string.checkout_flow), Toast.LENGTH_SHORT).show();
    }
}
