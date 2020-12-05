package com.dwinuray.app_assignment.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dwinuray.app_assignment.R;
import com.dwinuray.app_assignment.databases.Database;
import com.dwinuray.app_assignment.models.Assignments;


public class FragmentUpdate extends Fragment {

    public static String ID   = "id";
    public static String TYPE = "type";
    public static String NAME = "name";
    public static String DESC = "desc";
    public static String DATE = "date";
    public static String STATUS = "status";

    EditText txName, txDesc, txDate;
    Button btnSubmit;
    private Database db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_update, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txName = view.findViewById(R.id.txtName);
        txDesc = view.findViewById(R.id.txtDescription);
        txDate = view.findViewById(R.id.txtDate);
        btnSubmit = view.findViewById(R.id.btnSave);


        // submit
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String alert = "Informasi ";
                Boolean statusInsert = true;
                if ( String.valueOf(txName.getText()).equals("") ) {

                    txName.requestFocus();
                    alert += "nama ";

                    statusInsert = false;
                }

                if ( String.valueOf(txName.getText()).equals("") ) {

                    txName.requestFocus();
                    alert += "tanggal ";

                    statusInsert = false;
                }

                // check data before insert
                if ( statusInsert ) {

                    // value
                    String setType, setName, setDescription, setDate, setStatus;
                    setType        = getArguments().getString(TYPE);
                    setName        = String.valueOf(txName.getText());
                    setDescription = String.valueOf(txDesc.getText());
                    setDate        = String.valueOf(txDate.getText());
                    setStatus      = getArguments().getString(STATUS);

                    // db
                    db = new Database(getActivity());
                    db.updateAssignment(new Assignments(getArguments().getString(ID), setType, setName, setDescription, setDate, setStatus));
                    Toast.makeText(getActivity(), setName + " berhasil diperbarui", Toast.LENGTH_SHORT).show();







                    FragmentDetail fragmentDetail = new FragmentDetail();

                    // bundle
                    Bundle bundle = new Bundle();
                    bundle.putString(fragmentDetail.ID, getArguments().getString(ID));
                    bundle.putString(fragmentDetail.NAME, setName);
                    bundle.putString(fragmentDetail.DESC, setDescription);
                    bundle.putString(fragmentDetail.DATE, setDate);
                    bundle.putString(fragmentDetail.TYPE, setType);
                    bundle.putString(fragmentDetail.STATUS, "progress");

                    fragmentDetail.setArguments(bundle);

                    FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment, fragmentDetail, FragmentDetail.class.getSimpleName());
                    transaction.addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();


                } else {

                    Toast.makeText(getActivity(), alert + "harap diisi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String name, desc, date;
        name = getArguments().getString(NAME);
        desc = getArguments().getString(DESC);
        date = getArguments().getString(DATE);

        txName.setText(name);
        txDesc.setText(desc);
        txDate.setText(date);
    }
}
