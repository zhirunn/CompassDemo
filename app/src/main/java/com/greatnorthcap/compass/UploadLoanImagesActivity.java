package com.greatnorthcap.compass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by aspiree15 on 02/08/17.
 */

public class UploadLoanImagesActivity extends Activity {
    private UserSharedPref UserPref = new UserSharedPref();
    ImageButton imageBNinetyDayBankStatement, imageBFirstPayStub, imageBSecondPayStub, imageBThirdPayStub, imageBGovID, imageBEmploymentLetter, imageBAddressProof, imageBPreAuthorizedDebit, imageBSIN, imageBAnotherID, imageBPreAuthorizedAgreement;
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        final SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadloanimages);
        imageBNinetyDayBankStatement = findViewById(R.id.ninetyDayBankStatementImageButton);
        imageBFirstPayStub = findViewById(R.id.firstRecentPayStubImageButton);
        imageBSecondPayStub = findViewById(R.id.secondRecentPayStubImageButton);
        imageBThirdPayStub = findViewById(R.id.thirdRecentPayStubImageButton);
        imageBGovID = findViewById(R.id.govIDImageButton);
        imageBEmploymentLetter = findViewById(R.id.employmentLetterImageButton);
        imageBAddressProof = findViewById(R.id.addressProofImageButton);
        imageBPreAuthorizedDebit = findViewById(R.id.preauthorizedDebitImageButton);
        imageBSIN = findViewById(R.id.socialInsuranceNumberImageButton);
        imageBAnotherID = findViewById(R.id.anotherIDImageButton);
        imageBPreAuthorizedAgreement = findViewById(R.id.preauthorizedAgreementImageButton);

        imageBNinetyDayBankStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "BankStatement";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

        imageBFirstPayStub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "FirstPayStub";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

        imageBSecondPayStub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "SecondPayStub";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

        imageBThirdPayStub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "ThirdPayStub";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

        imageBGovID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "DriversID";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

        imageBEmploymentLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "EmploymentLetter";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

        imageBAddressProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "ProofOfAddress";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

        imageBPreAuthorizedDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "PreAuthorizedDebit";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

        imageBSIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "SocialInsuranceNumber";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

        imageBAnotherID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "OtherID";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

        imageBPreAuthorizedAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UploadType = "PreAuthorizedAgreement";
                prefEditor.putString(UserPref.getUploadtypeSharedPref(), UploadType);
                prefEditor.commit();
                startActivity(new Intent(UploadLoanImagesActivity.this, UploadImageActivity.class));
            }
        });

    }
}
