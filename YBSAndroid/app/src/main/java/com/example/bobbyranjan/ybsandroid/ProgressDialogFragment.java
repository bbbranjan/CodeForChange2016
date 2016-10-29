package com.example.bobbyranjan.ybsandroid;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;


public class ProgressDialogFragment extends DialogFragment {
    private static final String KEY_MESSAGE = "MESSAGE";
    private static final String KEY_TITLE = "TITLE";
    private static final String KEY_CANCELLABLE = "CANCELLABLE";

    public static ProgressDialogFragment newInstance(String title, String message, boolean isCancellable) {
        ProgressDialogFragment frag = new ProgressDialogFragment();
        frag.setCancelable(false);
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putString(KEY_MESSAGE, message);
        args.putBoolean(KEY_CANCELLABLE, isCancellable);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity(), getTheme());
        Bundle arguments = getArguments();
        dialog.setTitle(arguments.getString(KEY_TITLE));
        dialog.setMessage(arguments.getString(KEY_MESSAGE));
        dialog.setIndeterminate(true);
        boolean cancelable = arguments.getBoolean(KEY_CANCELLABLE);
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelable);
        return dialog;
    }
}