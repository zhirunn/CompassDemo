package com.greatnorthcap.compass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aspiree15 on 02/08/17.
 */

public class UploadLoanImagesActivity extends Activity {
    private UserSharedPref UserPref = new UserSharedPref();
    public static final String KEY_AMOUNT = "AmountApproved";
    public static final String KEY_LOANID = "LoanID";
    ImageButton imageBNinetyDayBankStatement, imageBFirstPayStub, imageBSecondPayStub, imageBThirdPayStub, imageBGovID, imageBEmploymentLetter, imageBAddressProof, imageBPreAuthorizedDebit, imageBSIN, imageBAnotherID, imageBPreAuthorizedAgreement;
    Boolean BankStatementURLCheck, FirstPayStubURLCheck, SecondPayStubURLCheck, ThirdPayStubURLCheck, DriversIDURLCheck, EmploymentLetterURLCheck, ProofOfAddressURLCheck, PreAuthorizedDebitURLCheck, SocialInsuranceNumberURLCheck, OtherIDURLCheck, PreAuthorizedAgreementURLCheck = false;
    Button buttonApproveLoan;
    EditText editTextSendAmount;

    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        final SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        String loanid = sharedPreferences.getString(UserPref.getSearchedloanidSharedPref(), "Not Available");
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
        buttonApproveLoan = findViewById(R.id.approveButton);
        editTextSendAmount = findViewById(R.id.sendAmountEditText);
        buttonApproveLoan.setText("Set Amount");

        String BankStatementURL = UserPref.getServerAddress() + "Images/" + loanid + "/BankStatement.jpg";
        String FirstPayStubURL = UserPref.getServerAddress() + "Images/" + loanid + "/FirstPayStub.jpg";
        String SecondPayStubURL = UserPref.getServerAddress() + "Images/" + loanid + "/SecondPayStub.jpg";
        String ThirdPayStubURL = UserPref.getServerAddress() + "Images/" + loanid + "/ThirdPayStub.jpg";
        String DriversIDURL = UserPref.getServerAddress() + "Images/" + loanid + "/DriversID.jpg";
        String EmploymentLetterURL = UserPref.getServerAddress() + "Images/" + loanid + "/EmploymentLetter.jpg";
        String ProofOfAddressURL = UserPref.getServerAddress() + "Images/" + loanid + "/ProofOfAddress.jpg";
        String PreAuthorizedDebitURL = UserPref.getServerAddress() + "Images/" + loanid + "/PreAuthorizedDebit.jpg";
        String SocialInsuranceNumberURL = UserPref.getServerAddress() + "Images/" + loanid + "/SocialInsuranceNumber.jpg";
        String OtherIDURL = UserPref.getServerAddress() + "Images/" + loanid + "/OtherID.jpg";
        String PreAuthorizedAgreementURL = UserPref.getServerAddress() + "Images/" + loanid + "/PreAuthorizedAgreement.jpg";

        try {
            Picasso.with(this).load(BankStatementURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBNinetyDayBankStatement, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    BankStatementURLCheck = true;
                }
                @Override
                public void onError() {
                    BankStatementURLCheck = false;
                }
            });
            Picasso.with(this).load(FirstPayStubURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBFirstPayStub, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    FirstPayStubURLCheck = true;
                }
                @Override
                public void onError() {
                    FirstPayStubURLCheck = false;
                }
            });
            Picasso.with(this).load(SecondPayStubURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBSecondPayStub, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    SecondPayStubURLCheck = true;
                }
                @Override
                public void onError() {
                    SecondPayStubURLCheck = false;
                }
            });
            Picasso.with(this).load(ThirdPayStubURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBThirdPayStub, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    ThirdPayStubURLCheck = true;
                }
                @Override
                public void onError() {
                    ThirdPayStubURLCheck = false;
                }
            });
            Picasso.with(this).load(DriversIDURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBGovID, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    DriversIDURLCheck = true;
                }
                @Override
                public void onError() {
                    DriversIDURLCheck = false;
                }
            });
            Picasso.with(this).load(EmploymentLetterURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBEmploymentLetter, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    EmploymentLetterURLCheck = true;
                }
                @Override
                public void onError() {
                    EmploymentLetterURLCheck = false;
                }
            });
            Picasso.with(this).load(ProofOfAddressURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBAddressProof, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    ProofOfAddressURLCheck = true;
                }
                @Override
                public void onError() {
                    ProofOfAddressURLCheck = false;
                }
            });
            Picasso.with(this).load(PreAuthorizedDebitURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBPreAuthorizedDebit, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    PreAuthorizedDebitURLCheck = true;
                }
                @Override
                public void onError() {
                    PreAuthorizedDebitURLCheck = false;
                }
            });
            Picasso.with(this).load(SocialInsuranceNumberURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBSIN, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    SocialInsuranceNumberURLCheck = true;
                }
                @Override
                public void onError() {
                    SocialInsuranceNumberURLCheck = false;
                }
            });
            Picasso.with(this).load(OtherIDURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBAnotherID, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    OtherIDURLCheck = true;
                }
                @Override
                public void onError() {
                    OtherIDURLCheck = false;
                }
            });
            Picasso.with(this).load(PreAuthorizedAgreementURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBPreAuthorizedAgreement, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    PreAuthorizedAgreementURLCheck = true;
                }
                @Override
                public void onError() {
                    PreAuthorizedAgreementURLCheck = false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        buttonApproveLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!BankStatementURLCheck || !FirstPayStubURLCheck || !SecondPayStubURLCheck || !ThirdPayStubURLCheck || !DriversIDURLCheck
                        || !EmploymentLetterURLCheck || !ProofOfAddressURLCheck || !PreAuthorizedDebitURLCheck || !SocialInsuranceNumberURLCheck
                        || !OtherIDURLCheck || !PreAuthorizedAgreementURLCheck) {
                    Toast.makeText(UploadLoanImagesActivity.this, "Please upload all of the required relevant documents.", Toast.LENGTH_LONG).show();
                } else {
                    setloanamount();
                }
            }
        });
    }

    private void setloanamount() {
        final SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        final String useramount = editTextSendAmount.getText().toString().trim();
        final String loanid = sharedPreferences.getString(UserPref.getSearchedloanidSharedPref(), "Not Available");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UserPref.getSetloanUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(UploadLoanImagesActivity.this,response,Toast.LENGTH_LONG).show();
                        //finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UploadLoanImagesActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }

                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_AMOUNT, useramount);
                params.put(KEY_LOANID, loanid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
