package com.dequesystems.accessibility101.talkbacksimulation;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dequesystems.accessibility101.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TalkBackSimulationFragment extends Fragment {

    public TalkBackSimulationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_talk_back_simulation, container, false);
    }


}
