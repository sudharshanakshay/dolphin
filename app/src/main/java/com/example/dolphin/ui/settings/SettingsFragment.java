package com.example.dolphin.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dolphin.Auth.Login;
import com.example.dolphin.R;
import com.example.dolphin.session.SessionManager;

public class SettingsFragment extends Fragment {

    private Button logout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        logout = root.findViewById(R.id.logout);
        final SessionManager sessionManager = new SessionManager();
        sessionManager.setSharedPreference(getContext());



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                sessionManager.logoutUser();
            }
        });

        return root;
    }
}