package com.connectsdk.sampler.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.connectsdk.sampler.R;
import com.connectsdk.sampler.util.TestResponseObject;
import com.connectsdk.service.capability.KeyControl;
import com.connectsdk.service.capability.KeyControl.KeyCode;
import com.connectsdk.service.capability.VolumeControl;
import com.connectsdk.service.command.ServiceCommandError;

public class MyControllerFragment extends BaseFragment{

    public Button[] numberButtons = new Button[10];
    public Button enterButton;
    public Button resetButton;
    public SeekBar mVolumeBar;
    public EditText inputNumber;
    public String inputNumberStr = "";


    public TestResponseObject testResponse;

    public MyControllerFragment() {
        testResponse = new TestResponseObject();

    };

    public MyControllerFragment(Context context)
    {
        super(context);
        testResponse = new TestResponseObject();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_mycontroller, container, false);

        numberButtons[0] = (Button) rootView.findViewById(R.id.btn_0);
        numberButtons[1] = (Button) rootView.findViewById(R.id.btn_1);
        numberButtons[2] = (Button) rootView.findViewById(R.id.btn_2);
        numberButtons[3] = (Button) rootView.findViewById(R.id.btn_3);
        numberButtons[4] = (Button) rootView.findViewById(R.id.btn_4);
        numberButtons[5] = (Button) rootView.findViewById(R.id.btn_5);
        numberButtons[6] = (Button) rootView.findViewById(R.id.btn_6);
        numberButtons[7] = (Button) rootView.findViewById(R.id.btn_7);
        numberButtons[8] = (Button) rootView.findViewById(R.id.btn_8);
        numberButtons[9] = (Button) rootView.findViewById(R.id.btn_9);

        enterButton = (Button) rootView.findViewById(R.id.enterButton);
        resetButton = (Button) rootView.findViewById(R.id.resetButton);
        mVolumeBar = (SeekBar) rootView.findViewById(R.id.volumebar);

        inputNumber = (EditText) rootView.findViewById(R.id.inputnumber);

        inputNumberStr = "";

        return rootView;
    }

    public void updateInput() {
        if(inputNumberStr.equals("")){
            inputNumber.setText("_______");
        }
        else{
            inputNumber.setText(inputNumberStr);
        }
    }

    @Override
    public void enableButtons() {
        super.enableButtons();

        if (getTv().hasAnyCapability(KeyControl.KeyCode)) {
            numberButtons[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.NUM_0, null);
                    inputNumberStr += "0";
                }
            });

            numberButtons[1].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.NUM_1, null);
                    inputNumberStr += "1";
                }
            });

            numberButtons[2].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.NUM_2, null);
                    inputNumberStr += "2";
                }
            });

            numberButtons[3].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.NUM_3, null);
                    inputNumberStr += "3";
                }
            });

            numberButtons[4].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.NUM_4, null);
                    inputNumberStr += "4";
                }
            });

            numberButtons[5].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.NUM_5, null);
                    inputNumberStr += "5";
                }
            });

            numberButtons[6].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.NUM_6, null);
                    inputNumberStr += "6";
                }
            });

            numberButtons[7].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.NUM_7, null);
                    inputNumberStr += "7";
                }
            });

            numberButtons[8].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.NUM_8, null);
                    inputNumberStr += "8";
                }
            });

            numberButtons[9].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.NUM_9, null);
                    inputNumberStr += "9";
                }
            });

            enterButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getKeyControl().sendKeyCode(KeyCode.ENTER, null);
                    inputNumberStr = "";
                }
            });

            resetButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    inputNumberStr = "";
                }
            });

        } else {
            disableButton(numberButtons[0]);
            disableButton(numberButtons[1]);
            disableButton(numberButtons[2]);
            disableButton(numberButtons[3]);
            disableButton(numberButtons[4]);
            disableButton(numberButtons[5]);
            disableButton(numberButtons[6]);
            disableButton(numberButtons[7]);
            disableButton(numberButtons[8]);
            disableButton(numberButtons[9]);
            disableButton(enterButton);
            disableButton(resetButton);
        }
    }
    public SeekBar.OnSeekBarChangeListener volumeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override public void onStopTrackingTouch(SeekBar arg0) { }
        @Override public void onStartTrackingTouch(SeekBar arg0) { }

        @Override
        public void onProgressChanged(SeekBar seekBar, int position, boolean fromUser) {
            if (fromUser)
                getVolumeControl().setVolume((float) mVolumeBar.getProgress() / 100.0f, null);
        }
    };

    public VolumeControl.VolumeListener getVolumeListener = new VolumeControl.VolumeListener() {

        @Override
        public void onError(ServiceCommandError error) {
            Log.d("LG", "Error getting Volume: " + error);
        }

        @Override
        public void onSuccess(Float object) {
            mVolumeBar.setProgress((int) (object * 100.0f));
        }
    };
}
