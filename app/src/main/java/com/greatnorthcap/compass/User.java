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
        TextView textViewuserId = (TextView) listViewItem.findViewById(R.id.textViewUserId);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewBorrower = (TextView) listViewItem.findViewById(R.id.textViewBorrowerType);
        TextView textViewLender = (TextView) listViewItem.findViewById(R.id.textViewLenderType);
        textViewuserId.setText("User ID: " + userIds[position]);
        textViewEmail.setText("User Email: " +userEmails[position]);
        textViewBorrower.setText("Borrower Status: " + borrowerTypes[position]);
        textViewLender.setText("Lender Status: " + lenderTypes[position]);
        return listViewItem;
    }
}