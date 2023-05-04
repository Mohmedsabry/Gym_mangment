package com.example.myapplication.Database;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Payment_Adpter;
import com.example.myapplication.R;

public class PaymentFrag extends Fragment {
    private Payment_Adpter adpter;

    public Payment_Adpter getAdpter() {
        return adpter;
    }

    public void setAdpter(Payment_Adpter adpter) {
        this.adpter = adpter;
    }

    private static final String ARG_PARAM1 = "Adpter";



    public PaymentFrag() {
        // Required empty public constructor
    }

    public static PaymentFrag newInstance(Payment_Adpter adpter) {
        PaymentFrag fragment = new PaymentFrag();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,adpter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            adpter = (Payment_Adpter) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.Payment_rv);
        recyclerView.setAdapter(adpter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}