package com.doulaize.flagapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static com.doulaize.flagapp.patterns.PatternInterface.patternTypeEnum.BORDER;
import static com.doulaize.flagapp.patterns.PatternInterface.patternTypeEnum.CROSS;
import static com.doulaize.flagapp.patterns.PatternInterface.patternTypeEnum.FESS;
import static com.doulaize.flagapp.patterns.PatternInterface.patternTypeEnum.GREEK_CROSS;
import static com.doulaize.flagapp.patterns.PatternInterface.patternTypeEnum.PALE;
import static com.doulaize.flagapp.patterns.PatternInterface.patternTypeEnum.PALL;
import static com.doulaize.flagapp.patterns.PatternInterface.patternTypeEnum.QUADRISECTION;
import static com.doulaize.flagapp.patterns.PatternInterface.patternTypeEnum.SALTIRE;

/**
 * Created by rdeleuze on 2/27/2017
 */

public class NewLayerDialogFragment extends DialogFragment {
    SelectFlagPatternListener selectFlagPatternListener;

    static NewLayerDialogFragment newInstance() {
        return new NewLayerDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setTitle(getResources().getString(R.string.text_select_new_layer_pattern));
        View v = inflater.inflate(R.layout.add_layer_fragment, container, false);

        ImageView imageViewBorder = (ImageView) v.findViewById(R.id.pattern_border);
        imageViewBorder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (selectFlagPatternListener != null) {
                    selectFlagPatternListener.OnPatternSelected(BORDER);
                }
                dismiss();
            }
        });

        ImageView imageViewCross = (ImageView) v.findViewById(R.id.pattern_cross);
        imageViewCross.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (selectFlagPatternListener != null) {
                    selectFlagPatternListener.OnPatternSelected(CROSS);
                }
                dismiss();
            }
        });

        ImageView imageViewFess = (ImageView) v.findViewById(R.id.pattern_fess);
        imageViewFess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (selectFlagPatternListener != null) {
                    selectFlagPatternListener.OnPatternSelected(FESS);
                }
                dismiss();
            }
        });

        ImageView imageViewGreekCross = (ImageView) v.findViewById(R.id.pattern_greekcross);
        imageViewGreekCross.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (selectFlagPatternListener != null) {
                    selectFlagPatternListener.OnPatternSelected(GREEK_CROSS);
                }
                dismiss();
            }
        });

        ImageView imageViewPale = (ImageView) v.findViewById(R.id.pattern_pale);
        imageViewPale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (selectFlagPatternListener != null) {
                    selectFlagPatternListener.OnPatternSelected(PALE);
                }
                dismiss();
            }
        });

        ImageView imageViewPall = (ImageView) v.findViewById(R.id.pattern_pall);
        imageViewPall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (selectFlagPatternListener != null) {
                    selectFlagPatternListener.OnPatternSelected(PALL);
                }
                dismiss();
            }
        });

        ImageView imageViewQuadri = (ImageView) v.findViewById(R.id.pattern_quadrisection);
        imageViewQuadri.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (selectFlagPatternListener != null) {
                    selectFlagPatternListener.OnPatternSelected(QUADRISECTION);
                }
                dismiss();
            }
        });

        ImageView imageViewSatire = (ImageView) v.findViewById(R.id.pattern_saltire);
        imageViewSatire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (selectFlagPatternListener != null) {
                    selectFlagPatternListener.OnPatternSelected(SALTIRE);
                }
                dismiss();
            }
        });

        return v;
    }

    public void setSelectorListener(SelectFlagPatternListener selectFlagPatternListener) {
        this.selectFlagPatternListener = selectFlagPatternListener;
    }

}
