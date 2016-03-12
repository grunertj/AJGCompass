package com.example.werner_jensgrunert.ajgcompass;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DestinationFragment extends Fragment {
    EditText editTextDestinationLatitude, editTextDestinationLongitude;
    Button buttonDestinationManual, buttonDestinationOsmand, buttonDestinationReset;
    Preferences preferences;

    public DestinationFragment() {
        // Required empty public constructor
    }

    protected void hideSoftKeyboard(EditText top, EditText bottom) {
        top.setInputType(1);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(top.getWindowToken(), 0);
        bottom.setInputType(1);
        imm.hideSoftInputFromWindow(bottom.getWindowToken(), 0);
    }

    public void manual_coordinates() {
        preferences.setDestination(editTextDestinationLatitude.getText().toString(), editTextDestinationLongitude.getText().toString());
        hideSoftKeyboard(editTextDestinationLatitude, editTextDestinationLongitude);
        editTextDestinationLatitude.clearFocus();
        editTextDestinationLongitude.clearFocus();
    }

    public void reset_coordinates() {
        preferences.clear();
        editTextDestinationLatitude.setText(preferences.getLatitudeDestination(Preferences.RETURN_AS_STRING));
        editTextDestinationLongitude.setText(preferences.getLongitudeDestination(Preferences.RETURN_AS_STRING));
        editTextDestinationLatitude.clearFocus();
        editTextDestinationLongitude.clearFocus();
    }

    public boolean isPackageExisted(String targetPackage) {
        PackageManager pm = getActivity().getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    public void osmand_coordinates() {
        if (isPackageExisted("net.osmand.plus")) {
            Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("net.osmand.plus");
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(launchIntent);
            getActivity().finish();
        } else if (isPackageExisted("net.osmand")) {
            Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("net.osmand");
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(launchIntent);
            getActivity().finish();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.fragment_destination, container, false);

        preferences = new Preferences(getActivity());

        editTextDestinationLatitude = (EditText) view.findViewById(R.id.editTextDestinationLatitude);
        editTextDestinationLongitude = (EditText) view.findViewById(R.id.editTextDestinationLongitude);
        // buttonDestinationManual = (Button) view.findViewById(R.id.buttonDestinationManual);
        buttonDestinationOsmand = (Button) view.findViewById(R.id.buttonDestinationOsmand);
        buttonDestinationReset = (Button) view.findViewById(R.id.buttonDestinationReset);

        editTextDestinationLatitude.setText(preferences.getLatitudeDestination(Preferences.RETURN_AS_STRING));
        editTextDestinationLongitude.setText(preferences.getLongitudeDestination(Preferences.RETURN_AS_STRING));
        editTextDestinationLatitude.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        editTextDestinationLongitude.setRawInputType(InputType.TYPE_CLASS_NUMBER);

        /*
        buttonDestinationManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manual_coordinates();
            }
        });
        */

        buttonDestinationReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_coordinates();
            }
        });

        buttonDestinationOsmand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                osmand_coordinates();
            }
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && isResumed()) {

        } else if (isResumed()) {
            preferences.setDestination(editTextDestinationLatitude.getText().toString(), editTextDestinationLongitude.getText().toString());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        preferences.setDestination(editTextDestinationLatitude.getText().toString(), editTextDestinationLongitude.getText().toString());
    }
}
