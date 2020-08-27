package com.sharkbuyers.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.sharkbuyers.R;

public class UtilsDialog {
    public static Dialog ShowDialog (Context context) {

        final Dialog dialog;
        dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.layout_custom_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        return dialog;
    }
}
