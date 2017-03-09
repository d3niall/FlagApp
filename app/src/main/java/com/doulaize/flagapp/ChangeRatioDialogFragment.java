package com.doulaize.flagapp;

import com.doulaize.flagapp.common.Constants;
import com.doulaize.flagapp.listener.NewRatioListener;
import com.doulaize.flagapp.views.RatioDialogFlagDrawingView;

import android.app.DialogFragment;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by rdeleuze on 3/7/2017
 */

public class ChangeRatioDialogFragment extends DialogFragment {

    public static String FRAGMENT_TRANSACTION_ID = "NewLayerDialogFragment";

    NewRatioListener newRatioListener;

    NumberPicker np_l;
    NumberPicker np_r;
    TextView ratioText;
    ColorStateList oldColors;

    Button okButton;
    Button cancelButton;

    TextView ratioWarningTextView;

    RatioDialogFlagDrawingView mRatioDialogFlagDrawingView;

    Integer initialValue_l = 1;
    Integer initialValue_r = 1;

    static ChangeRatioDialogFragment newInstance() {
        return new ChangeRatioDialogFragment();
    }

    public static long gcm(long a, long b) {
        return b == 0 ? a : gcm(b, a % b);
    }

    public static String asFraction(long a, long b) {
        long gcm = gcm(a, b);
        return String.format(Locale.ENGLISH, Constants.RATIO_TEXT_SUMMARY, (a / gcm), (b / gcm));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setTitle(getResources().getString(R.string.change_ratio_dialog_title));
        View v = inflater.inflate(R.layout.change_ratio_fragment, container, false);

        np_l = (NumberPicker) v.findViewById(R.id.ratio_dialog_number_picker_left);
        np_r = (NumberPicker) v.findViewById(R.id.ratio_dialog_number_picker_right);
        np_l.setMaxValue(Constants.RATIO_FRACTION_MAX_VALUE_TOP);
        np_r.setMaxValue(Constants.RATIO_FRACTION_MAX_VALUE_BOTTOM);
        np_l.setMinValue(Constants.RATIO_FRACTION_MIN_VALUE_TOP);
        np_r.setMinValue(Constants.RATIO_FRACTION_MIN_VALUE_BOTTOM);

        np_l.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                valueSpinnerChanged();
            }
        });

        np_r.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                valueSpinnerChanged();
            }
        });

        ratioText = (TextView) v.findViewById(R.id.ratio_dialog_summary_text);
        oldColors = ratioText.getTextColors();

        okButton = (Button) v.findViewById(R.id.ratio_dialog_button_ok);
        cancelButton = (Button) v.findViewById(R.id.ratio_dialog_button_cancel);

        ratioWarningTextView = (TextView) v.findViewById(R.id.change_ratio_warning);

        np_l.setValue(initialValue_l);
        np_r.setValue(initialValue_r);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != newRatioListener) {
                    newRatioListener.onNewRatioSelected(np_l.getValue(), np_r.getValue());
                }
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mRatioDialogFlagDrawingView = (RatioDialogFlagDrawingView) v.findViewById(R.id.ratio_dialog_flag_drawing_view);

        valueSpinnerChanged();
        return v;
    }

    public void setInitialRatioValue(Integer width, Integer height) {

        initialValue_l = width;
        initialValue_r = height;

        if (np_l != null && np_r != null && ratioText != null) {
            np_l.setValue(initialValue_l);
            np_r.setValue(initialValue_r);
            valueSpinnerChanged();
        }
    }

    public void valueSpinnerChanged() {
        if (np_l == null || np_r == null || ratioText == null)
            throw new IllegalStateException();

        int l = np_l.getValue();
        int r = np_r.getValue();

        ratioText.setText(asFraction(l, r));

        if (l * 1.0 / r > Constants.RATIO_MAX_VALUE || l * 1.0 / r < Constants.RATIO_MIN_VALUE) {
            ratioText.setTextColor(Color.RED);
            okButton.setEnabled(false);
            ratioWarningTextView.setVisibility(View.VISIBLE);
            if (l * 1.0 / r > Constants.RATIO_MAX_VALUE)
                ratioWarningTextView.setText(getResources().getString(R.string.change_ratio_warning_too_big));
            else
                ratioWarningTextView.setText(getResources().getString(R.string.change_ratio_warning_too_small));

        } else {
            ratioText.setTextColor(oldColors);
            okButton.setEnabled(true);
            ratioWarningTextView.setVisibility(View.GONE);

        }

        if (mRatioDialogFlagDrawingView != null) {
            mRatioDialogFlagDrawingView.setFlagHeight(r);
            mRatioDialogFlagDrawingView.setFlagWidth(l);
            mRatioDialogFlagDrawingView.invalidate();
        }
    }

    public void setNewRatioListener(NewRatioListener newRatioListener) {
        this.newRatioListener = newRatioListener;
    }
}
