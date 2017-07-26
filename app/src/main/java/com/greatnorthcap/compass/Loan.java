package com.greatnorthcap.compass;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Dan on 7/26/2017.
 */

public class Loan extends ArrayAdapter<String> {
    private String[] LoanIds;
    private String[] AmountsApproved;
    private String[] APR;
    private String[] TermDate;
    private String[] PaymentDue;
    private String[] Principal;
    private String[] Interest;
    private String[] Status;
    private String[] BorrowerID;
    private String[] LenderID;
    private String[] DateLastModified;
    private Activity context;

    public Loan (Activity context, String[] LoanIds, String[] AmountsApproved, String[] APR,String[] TermDate,
                 String[] PaymentDue, String[] Principal, String[] Interest, String[] Status, String[] BorrowerID,
                 String[] LenderID, String[] DateLastModified) {
        super(context, R.layout.list_view_layout_2, LoanIds);
        this.context = context;
        this.LoanIds = LoanIds;
        this.AmountsApproved = AmountsApproved;
        this.APR = APR;
        this.TermDate = TermDate;
        this.PaymentDue = PaymentDue;
        this.Principal = Principal;
        this.Interest = Interest;
        this.Status = Status;
        this.BorrowerID = BorrowerID;
        this.LenderID = LenderID;
        this.DateLastModified = DateLastModified;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout_2, null, true);
        TextView textViewLoanID = listViewItem.findViewById(R.id.textViewLoanId);
        TextView textViewAmountApproved = listViewItem.findViewById(R.id.textViewAmountApproved);
        TextView textViewAPR = listViewItem.findViewById(R.id.textViewAPR);
        TextView textViewTermDate = listViewItem.findViewById(R.id.textViewTermDate);
        TextView textViewPaymentDue = listViewItem.findViewById(R.id.textViewPaymentDue);
        TextView textViewPrincipal = listViewItem.findViewById(R.id.textViewPrincipal);
        TextView textViewInterest = listViewItem.findViewById(R.id.textViewInterest);
        TextView textViewStatus = listViewItem.findViewById(R.id.textViewStatus);
        TextView textViewBorrowerID= listViewItem.findViewById(R.id.textViewBorrowerID);
        TextView textViewLenderID = listViewItem.findViewById(R.id.textViewLenderID);
        TextView textViewDateLastModified = listViewItem.findViewById(R.id.textViewDateLastModified);
     /*  textViewLoanID.setText("Loan ID: ");
        textViewAmountApproved.setText("Amount Approved: ");
        textViewAPR.setText("APR: ");
        textViewTermDate.setText("Term Date: ");
        textViewPaymentDue.setText("Payment Due: ");
        textViewPrincipal.setText("Principal: ");
        textViewInterest.setText("Interest: ");
        textViewStatus.setText("Status: " );
        textViewBorrowerID.setText("Borrower ID: " );
        textViewLenderID.setText("Lender ID: " );
        textViewDateLastModified.setText("Date Last Modified: ");
        */
        textViewLoanID.setText("Loan ID: " + LoanIds[position]);
        textViewAmountApproved.setText("Amount Approved: " + AmountsApproved[position]);
        textViewAPR.setText("APR: " + APR[position]);
        textViewTermDate.setText("Term Date: " + TermDate[position]);
        textViewPaymentDue.setText("Payment Due: " + PaymentDue[position]);
        textViewPrincipal.setText("Principal: " + Principal[position]);
        textViewInterest.setText("Interest: " + Interest[position]);
        textViewStatus.setText("Status: " + Status[position]);
        textViewBorrowerID.setText("Borrower ID: " + BorrowerID[position]);
        textViewLenderID.setText("Lender ID: " + LenderID[position]);
        textViewDateLastModified.setText("Date Last Modified: " + DateLastModified[position]);

        return listViewItem;
    }


}
