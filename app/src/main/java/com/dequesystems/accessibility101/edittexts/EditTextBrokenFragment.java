package com.dequesystems.accessibility101.edittexts;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dequesystems.accessibility101.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditTextBrokenFragment extends Fragment {

    EditText mEditText;
    TextView mTextView;

    public EditTextBrokenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_text_broken, container, false);

        mEditText = (EditText) view.findViewById(R.id.aac_editTextBroken_editText1);
        mTextView = (TextView) view.findViewById(R.id.aac_editTextBroken_textView1);
        /*
        // #DEMO: Associate the EditText with it's visible label using the labelFor attribute.
        mTextView.setLabelFor(mEditText.getId());
        */
        return view;
    }


}
