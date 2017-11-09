package com.vikram.numbersfact.ui.advance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vikram.numbersfact.R;
import com.vikram.numbersfact.dataservice.RetroResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvanceActivity extends AppCompatActivity implements IAdvanceView {
    private IAdvancePresenter advancePresenter;

    private Spinner mCategorySpinner;
    private EditText mInputEditText;
    private Button mSubmitButton;
    private TextView mDetailsLabelTextView;
    private TextView mDetailsTextView;
    private ProgressBar mProgressBar;

    private String mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCategorySpinner = (Spinner) findViewById(R.id.spin_category);
        mInputEditText = (EditText) findViewById(R.id.edit_number);
        mSubmitButton = (Button) findViewById(R.id.btn_submit);
        mDetailsLabelTextView = (TextView) findViewById(R.id.txt_lbl_details);
        mDetailsTextView = (TextView) findViewById(R.id.txt_details);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        advancePresenter = new AdvancePresenter(this);

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
                if (mCategory != null && !mInputEditText.getText().toString().equals("")) {
                    String input = mInputEditText.getText().toString();
                    String regex = "^([0-9]+|(0?[1-9]|1[0-2])\\/(0?[1-9]|[12][0-9]|3[01]))$";
                    Pattern r = Pattern.compile(regex);
                    Matcher m = r.matcher(input);
                    if (!m.find( )) {
                        Toast.makeText(AdvanceActivity.this, "Wrong input, only number and MM/DD allowed" , Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (input.contains("/") && !mCategory.equals("date")) {
                        Toast.makeText(AdvanceActivity.this, "/ in input allowed only with date category" , Toast.LENGTH_SHORT).show();
                        return;
                    }
                    advancePresenter.getSpecificNumberFacts(mInputEditText.getText().toString(), mCategory);
                } else {
                    Toast.makeText(AdvanceActivity.this, "Please select a category and enter number" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSuccess(RetroResponse response) {
        if(response != null) {
            mDetailsLabelTextView.setText("Details about "+mInputEditText.getText().toString());

            mDetailsTextView.setText(response.getText());
        }
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(AdvanceActivity.this, error , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        advancePresenter.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}