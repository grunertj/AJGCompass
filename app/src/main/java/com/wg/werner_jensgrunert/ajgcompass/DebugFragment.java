package com.wg.werner_jensgrunert.ajgcompass;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DebugFragment extends Fragment {
    TextView textViewDebug1,textViewDebug2, textViewDebug3;
    DebugInfo debugInfo;


    public DebugFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.fragment_debug, container, false);

        textViewDebug1 = (TextView) view.findViewById(R.id.textViewDebug1);
        textViewDebug2 = (TextView) view.findViewById(R.id.textViewDebug2);
        textViewDebug3 = (TextView) view.findViewById(R.id.textViewDebug3);

        debugInfo = new DebugInfo();


        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            textViewDebug1.setText("jens: "+DebugInfo.jens);
            textViewDebug2.setText("provider: "+DebugInfo.provider);
            textViewDebug3.setText("accuracy: "+DebugInfo.accuracy);
        } else if (isResumed()) {

        }
    }
}
