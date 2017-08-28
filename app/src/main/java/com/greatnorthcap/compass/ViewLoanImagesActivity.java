package com.greatnorthcap.compass;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by aspiree15 on 28/08/17.
 */

public class ViewLoanImagesActivity extends Activity {
    private UserSharedPref UserPref = new UserSharedPref();
    ImageButton imageBNinetyDayBankStatement, imageBFirstPayStub, imageBSecondPayStub, imageBThirdPayStub, imageBGovID, imageBEmploymentLetter, imageBAddressProof, imageBPreAuthorizedDebit, imageBSIN, imageBAnotherID, imageBPreAuthorizedAgreement;
    Button buttonApproveLoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadloanimages);
        final SharedPreferences sharedPreferences = getSharedPreferences(UserPref.getSharedPrefName(), Context.MODE_PRIVATE);
        String loanid = sharedPreferences.getString(UserPref.getSearchedloanidSharedPref(), "Not Available");
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
            Picasso.with(this).load(BankStatementURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBNinetyDayBankStatement);
            Picasso.with(this).load(FirstPayStubURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBFirstPayStub);
            Picasso.with(this).load(SecondPayStubURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBSecondPayStub);
            Picasso.with(this).load(ThirdPayStubURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBThirdPayStub);
            Picasso.with(this).load(DriversIDURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBGovID);
            Picasso.with(this).load(EmploymentLetterURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBEmploymentLetter);
            Picasso.with(this).load(ProofOfAddressURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBAddressProof);
            Picasso.with(this).load(PreAuthorizedDebitURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBPreAuthorizedDebit);
            Picasso.with(this).load(SocialInsuranceNumberURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBSIN);
            Picasso.with(this).load(OtherIDURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBAnotherID);
            Picasso.with(this).load(PreAuthorizedAgreementURL).networkPolicy(NetworkPolicy.NO_CACHE).into(imageBPreAuthorizedAgreement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
