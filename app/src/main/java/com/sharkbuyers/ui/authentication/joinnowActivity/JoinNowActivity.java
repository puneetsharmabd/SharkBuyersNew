package com.sharkbuyers.ui.authentication.joinnowActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.ui.authentication.uploadresume.UploadResumeActivity;
import com.sharkbuyers.utils.CommonMethods;
import com.sharkbuyers.utils.UtilsFontFamily;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinNowActivity extends BaseClass {


    @BindView(R.id.editFirstName)
    EditText editFirstName;
    @BindView(R.id.editLastName)
    EditText editLastName;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.editDataOfBirth)
    EditText editDataOfBirth;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.tvJownNow)
    TextView tvJownNow;
    @BindView(R.id.editGender)
    EditText editGender;
    @BindView(R.id.editPassword)
    EditText editPassword;
    @BindView(R.id.editCPassword)
    EditText editCPassword;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String select_latitude = "", select_longitude = "", date_of_birth = "";
    Dialog dialog;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_now);
        ButterKnife.bind(this);
        context=JoinNowActivity.this;
        tvJownNow.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

    }

    @OnClick({R.id.editDataOfBirth, R.id.editGender, R.id.tvNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editDataOfBirth:
                OpenDatePickerDialog();
                break;
            case R.id.editGender:
                genderDialog();
                break;
            case R.id.tvNext:
                valiationOnRegister();

                break;
        }
    }

    private void OpenDatePickerDialog() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        date_of_birth = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        editDataOfBirth.setText(date_of_birth);
                    }
                }, mYear, mMonth, mDay);

        //datePickerDialog.setTitle("Please pick your date of birth");
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();


    }


    private void genderDialog() {

        dialog = new Dialog(context, R.style.DialogThemes);
        dialog.setContentView(R.layout.dialog_gender);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tv_male = (TextView) dialog.findViewById(R.id.tv_male);
        TextView tv_female = (TextView) dialog.findViewById(R.id.tv_female);

        tv_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGender.setText("Male");
                dialog.dismiss();
            }
        });

        tv_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGender.setText("Female");
                dialog.dismiss();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void valiationOnRegister() {

        if (editFirstName.getText().toString().trim().isEmpty()) {
            editFirstName.setError(getString(R.string.string_first_name));
        } else if (editFirstName.length() < 3 || editFirstName.length() > 15) {
            editFirstName.setError(getString(R.string.username_length));
        } else if (editLastName.getText().toString().trim().isEmpty()) {
            editLastName.setError(getString(R.string.string_last_name));
        } else if (editLastName.length() < 3 || editLastName.length() > 15) {
            editLastName.setError(getString(R.string.lastname_length));
        } else if (editEmail.getText().toString().trim().isEmpty()) {
            editEmail.setError(getString(R.string.string_email));
        } else if (!CommonMethods.isValidEmail(editEmail.getText().toString())) {
            editEmail.setError(getString(R.string.string_validemail));
//        }  else if (editPhone.getText().toString().trim().isEmpty()) {
//            editPhone.setError(getString(R.string.string_phone_number));
//        } else if (!CommonMethods.isValidMobile(editPhone.getText().toString())) {
//            editPhone.setError(getString(R.string.enter_valid_phone_number));
//        }
        }else if (editGender.getText().toString().isEmpty()) {
            editGender.setError(getString(R.string.string_gender));
        }   else if (editPassword.getText().toString().trim().isEmpty()) {
            editPassword.setError(getString(R.string.r_password_enter));

//        } else if (editPassword.length() < 6 || editPassword.length() > 16) {
//            editPassword.setError(getString(R.string.password_length));
//        } else if (!CommonMethods.isValidPassword(editPassword.getText().toString().trim())) {
//            editPassword.setError(getString(R.string.password_pattern_hint));
        } else if (editCPassword.getText().toString().trim().isEmpty()) {
            editCPassword.setError(getString(R.string.r_confirm_password));
        } else if (!editCPassword.getText().toString().trim().matches(editPassword.getText().toString().trim())) {
            editCPassword.setError(getString(R.string.r_password_not_match));
        } else {
            Intent intent = new Intent(JoinNowActivity.this, UploadResumeActivity.class);
            intent.putExtra("firstName", editFirstName.getText().toString().trim());
            intent.putExtra("lastName", editLastName.getText().toString().trim());
            intent.putExtra("email", editEmail.getText().toString().trim());
            //intent.putExtra("phone", editPhone.getText().toString().trim());
            intent.putExtra("phone", "");
            intent.putExtra("gender", editGender.getText().toString().trim());
            intent.putExtra("password", editCPassword.getText().toString().trim());
            startActivity(intent);
        }
    }

}
