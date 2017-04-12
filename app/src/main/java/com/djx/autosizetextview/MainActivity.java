package com.djx.autosizetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mAutoTv;
    private TextView mAutoTvPreset;
    private TextView mTarget;
    private float mChangeStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChangeStep = getResources().getDisplayMetrics().density * 2;

        CheckBox isPreset = (CheckBox) findViewById(R.id.preset);
        Button plus = (Button) findViewById(R.id.plus);
        Button minus = (Button) findViewById(R.id.minus);
        mAutoTv = (TextView) findViewById(R.id.auto_textView);
        mAutoTvPreset = (TextView) findViewById(R.id.auto_textView_preset_sizes);

        updateTvVisibility(isPreset.isChecked());

        isPreset.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateTvVisibility(b);
            }
        });
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
    }

    private void updateTvVisibility(boolean isPreset) {
        if (isPreset) {
            mAutoTvPreset.setVisibility(View.VISIBLE);
            mAutoTv.setVisibility(View.GONE);
            mTarget = mAutoTvPreset;
        } else {
            mAutoTvPreset.setVisibility(View.GONE);
            mAutoTv.setVisibility(View.VISIBLE);
            mTarget = mAutoTv;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.plus:
            {
                FrameLayout.LayoutParams ll = (FrameLayout.LayoutParams) mTarget.getLayoutParams();
                ll.width += mChangeStep;
                ll.height += mChangeStep;
                mTarget.setLayoutParams(ll);
            }
            break;
            case R.id.minus:
            {
                FrameLayout.LayoutParams ll = (FrameLayout.LayoutParams) mTarget.getLayoutParams();
                ll.width -= mChangeStep;
                ll.height -= mChangeStep;
                mTarget.setLayoutParams(ll);
            }
            break;
        }
    }
}
