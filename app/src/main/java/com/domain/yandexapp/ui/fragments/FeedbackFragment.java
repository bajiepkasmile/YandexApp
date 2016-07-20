package com.domain.yandexapp.ui.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.domain.yandexapp.R;
import com.domain.yandexapp.ui.activities.MainActivity;
import com.domain.yandexapp.utils.ToastManager;

public class FeedbackFragment extends Fragment {

    public static final String TAG = "FeedbackFragment";

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        view.findViewById(R.id.tv_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto", getString(R.string.feedback_email), null));
                intent.putExtra(Intent.EXTRA_EMAIL, R.string.feedback_email);

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    ToastManager.showToast(getActivity(), R.string.message_no_such_activity, Toast.LENGTH_LONG);
                }
            }
        });

        getActivity().setTitle(R.string.feedback);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }

}
