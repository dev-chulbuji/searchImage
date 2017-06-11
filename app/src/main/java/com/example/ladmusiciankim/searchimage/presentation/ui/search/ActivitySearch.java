package com.example.ladmusiciankim.searchimage.presentation.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseActivity;
import com.example.ladmusiciankim.searchimage.presentation.ui.main.MainContract;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivitySearch extends BaseActivity implements MainContract.View {
    private static final String TAG = ActivitySearch.class.getSimpleName();

    @BindView(R.id.search_txt) EditText txtSearch;
    @BindView(R.id.search_submit) TextView btnSubmit;

    @OnClick({R.id.search_submit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_submit:
                Intent returnIntent = new Intent();
                returnIntent.putExtra(getString(R.string.arg_search_query), txtSearch.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
    }


}
