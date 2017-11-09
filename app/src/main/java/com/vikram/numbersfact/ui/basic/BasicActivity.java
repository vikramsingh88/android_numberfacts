package com.vikram.numbersfact.ui.basic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vikram.numbersfact.R;
import com.vikram.numbersfact.dataservice.RetroResponse;
import com.vikram.numbersfact.ui.advance.AdvanceActivity;

public class BasicActivity extends AppCompatActivity implements IBasicView {

    private IBasicPresenter basicPresenter;

    private Spinner mCategorySpinner;
    private Button mSubmitButton;
    private TextView mDetailsLabelTextView;
    private TextView mDetailsTextView;
    private ProgressBar mProgressBar;

    private String mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        mCategorySpinner = (Spinner) findViewById(R.id.spin_category);
        mSubmitButton = (Button) findViewById(R.id.btn_submit);
        mDetailsLabelTextView = (TextView) findViewById(R.id.txt_lbl_details);
        mDetailsTextView = (TextView) findViewById(R.id.txt_details);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        basicPresenter = new BasicPresenter(this);

        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mCategory = parent.getItemAtPosition(position).toString().toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCategory != null) {
                    basicPresenter.getNumberFacts(mCategory);
                } else {
                    Toast.makeText(BasicActivity.this, "Please select a category" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSuccess(RetroResponse response) {
        if(response != null) {
            if (mCategory.equals("trivia")) {
                mDetailsLabelTextView.setText("Details about "+response.getNumber());
            } else if(mCategory.equals("year")) {
                mDetailsLabelTextView.setText(response.getYear() != null ? "Details about "+response.getYear() : response.getDate() != null ? "Details about "+response.getDate() : "Details about "+response.getNumber());
            } else if(mCategory.equals("date")) {
                mDetailsLabelTextView.setText(response.getYear() != null ? "Details about "+response.getYear() : response.getDate() != null ? "Details about "+response.getDate() : "Details about "+response.getNumber());
            } else if(mCategory.equals("math")) {
                mDetailsLabelTextView.setText("Details about "+response.getNumber());
            }

            mDetailsTextView.setText(response.getText());
        }
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(BasicActivity.this, error , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basicPresenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_go) {
            Intent intentAdvance = new Intent(this, AdvanceActivity.class);
            startActivity(intentAdvance);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
