package com.example.aa.user.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aa.R;
import com.example.aa.base.BaseFragment;
import com.example.aa.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContainerFragment extends Fragment {


    public ContainerFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.content_user,new UserFragment())
                .commit();
        return view;
    }
}
