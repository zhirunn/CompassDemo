package com.greatnorthcap.compass;

/**
 * Created by Dan on 7/20/2017.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class User extends ArrayAdapter<String> {
    private String[] userIds;
    private String[] userEmails;
    private String[] borrowerTypes;
    private String[] lenderTypes;
    private Activity context;

    public User (Activity context, String[] userIds, String[] userEmails, String[] borrowerTypes,String[] lenderTypes) {
        super(context, R.layout.list_view_layout, userIds);
        this.context = context;
        this.userIds = userIds;
        this.userEmails = userEmails;
        this.borrowerTypes = borrowerTypes;
        this.lenderTypes = lenderTypes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewuserId = listViewItem.findViewById(R.id.textViewUserId);
        TextView textViewEmail = listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewBorrower = listViewItem.findViewById(R.id.textViewBorrowerType);
        TextView textViewLender = listViewItem.findViewById(R.id.textViewLenderType);
        textViewuserId.setText("User ID: " + userIds[position]);
        textViewEmail.setText("User Email: " + userEmails[position]);
        String Unscreened = "0";
        String LowRisk = "1";
        String HighRisk = "2";
        if ( borrowerTypes[position].equalsIgnoreCase(Unscreened))
        {
            textViewBorrower.setText("Borrower Status: Unscreened ");

        }
        else if (borrowerTypes[position].equalsIgnoreCase(LowRisk))
        {
            textViewBorrower.setText("Borrower Status: Low Risk ");

        }
        else if (borrowerTypes[position].equalsIgnoreCase(HighRisk))
        {
            textViewBorrower.setText("Borrower Status: High Risk ");

        }
        else
        {
            textViewBorrower.setText("Borrower Status: Admin");
        }
        if (lenderTypes[position].equalsIgnoreCase(Unscreened))
        {
            textViewLender.setText("Lender Status: Unscreened ");
        }
        else if (lenderTypes[position].equalsIgnoreCase(LowRisk))
        {
            textViewLender.setText("Lender Status: Low Risk");
        }
        else if (lenderTypes[position].equalsIgnoreCase(HighRisk))
        {
            textViewLender.setText("Lender Status: High Risk ");
        }
        else
        {
            textViewLender.setText("Lender Status: Admin");
        }
        return listViewItem;
    }
}