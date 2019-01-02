package com.hanyuzhou.accountingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.hanyuzhou.accountingapp.R.drawable.baseline_attach_money_24;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener, CategoryRecyclerAdapter.OnCategoryClickListener {

    private static String TAG = "AddRecordActivity";

    private EditText editText;
    private TextView amountText;
    private String userInput = "";

    private RecyclerView recyclerView;
    private CategoryRecyclerAdapter adapter;

    private String category = "General";
    private RecordBean.RecordType type = RecordBean.RecordType.RECORD_TYPE_EXPENSE;
    private String remark = category;

    RecordBean record = new RecordBean();

    private boolean inEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        findViewById(R.id.keyboard_one).setOnClickListener(this);
        findViewById(R.id.keyboard_two).setOnClickListener(this);
        findViewById(R.id.keyboard_three).setOnClickListener(this);
        findViewById(R.id.keyboard_four).setOnClickListener(this);
        findViewById(R.id.keyboard_five).setOnClickListener(this);
        findViewById(R.id.keyboard_six).setOnClickListener(this);
        findViewById(R.id.keyboard_seven).setOnClickListener(this);
        findViewById(R.id.keyboard_eight).setOnClickListener(this);
        findViewById(R.id.keyboard_nine).setOnClickListener(this);
        findViewById(R.id.keyboard_zero).setOnClickListener(this);

        getSupportActionBar().setElevation(0);

        amountText = (TextView) findViewById(R.id.textView_amount);
        editText = (EditText) findViewById(R.id.editText);
        editText.setText(remark);

        handleBackspace();
        handleDone();
        handleDot();
        handleTypeChange();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CategoryRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.notifyDataSetChanged();

        adapter.setOnCategoryClickListener(this);

        RecordBean record = (RecordBean) getIntent().getSerializableExtra("record");
        if (record != null) {
            inEdit = true;
            this.record = record;
        }
    }

    private void handleDot() {
        findViewById(R.id.keyboard_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userInput.contains(".")) {
                    userInput += ".";
                }
            }
        });
    }

    private void handleTypeChange() {
        findViewById(R.id.keyboard_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton button = findViewById(R.id.keyboard_type);
                if (type == RecordBean.RecordType.RECORD_TYPE_EXPENSE) {
                    type = RecordBean.RecordType.RECORD_TYPE_INCOME;
                    button.setImageResource(R.drawable.baseline_attach_money_24);
                } else {
                    type = RecordBean.RecordType.RECORD_TYPE_EXPENSE;
                    button.setImageResource(R.drawable.baseline_money_off_24);
                }
                adapter.changeType(type);
                category = adapter.getSelected();
            }
        });
    }

    private void handleBackspace() {
        findViewById(R.id.keyboard_backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInput.length() > 0) {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
                if (userInput.length() > 0 && userInput.charAt(userInput.length() - 1) == '.') {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
                updateAmountText();
            }
        });
    }

    private void handleDone() {
        findViewById(R.id.keyboard_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userInput.equals("")) {
                    double amount = Double.valueOf(userInput);
                    record.setAmount(amount);
                    if (type == RecordBean.RecordType.RECORD_TYPE_EXPENSE) {
                        record.setType(1);
                    } else {
                        record.setType(2);
                    }
                    record.setCategory(adapter.getSelected());
                    record.setRemark(editText.getText().toString());

                    if (inEdit) {
                        GlobalUtil.getInstance().databaseHelper.editRecord(record.getUuid(), record);
                    } else {
                        GlobalUtil.getInstance().databaseHelper.addRecord(record);
                    }
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "金额不能为0", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String input = button.getText().toString();
        if (userInput.contains(".")) {
            if (userInput.split("\\.").length == 1 || userInput.split("\\.")[1].length() < 2) {
                userInput += input;
            }
        } else {
            userInput += input;
        }
        updateAmountText();

    }

    private void updateAmountText() {
        if (userInput.contains(".")) {
            if (userInput.split("\\.").length == 1) {
                amountText.setText(userInput + "00");
            } else if (userInput.split("\\.")[1].length() == 1) {
                amountText.setText(userInput + "0");
            } else if (userInput.split("\\.")[1].length() == 2) {
                amountText.setText(userInput);
            }
        } else {
            if (userInput.equals("")) {
                amountText.setText("0.00");
            } else {
                amountText.setText(userInput + ".00");
            }
        }
    }

    @Override
    public void onClikc(String category) {
        this.category = category;
        editText.setText(category);
    }
}
