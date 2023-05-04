package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Payment_Adpter extends RecyclerView.Adapter<Payment_Adpter.Holder>implements Serializable, Parcelable {


    ArrayList<Subscibtor>arrayList;
    PaymentListener listener;
    protected Payment_Adpter(Parcel in) {

    }

    public static final Creator<Payment_Adpter> CREATOR = new Creator<Payment_Adpter>() {
        @Override
        public Payment_Adpter createFromParcel(Parcel in) {
            return new Payment_Adpter(in);
        }

        @Override
        public Payment_Adpter[] newArray(int size) {
            return new Payment_Adpter[size];
        }
    };



    public ArrayList<Subscibtor> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Subscibtor> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public Payment_Adpter(ArrayList<Subscibtor> arrayList,PaymentListener listener) {
        this.arrayList = arrayList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_rv,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
                Log.d("payment", "yes");
                if (!arrayList.get(position).getName().equals("")){
                    holder.Payment_Name.setText(arrayList.get(position).getName());
                }
                if (arrayList.get(position).getPayDate()!=null&&!arrayList.get(position).getPayDate().equals("")){
                    DateFormat dateFormat = new SimpleDateFormat("M/d");
                    Date date = new Date(arrayList.get(position).getPayDate());
                    holder.Payment_Payment_history.setText(dateFormat.format(date).toString());
                }
                holder.Payment_price.setText(arrayList.get(position).getPayment()+"");

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.Onclick(arrayList.get(holder.getAdapterPosition()).getId());
                    }
                });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    class Holder extends RecyclerView.ViewHolder{

        TextView Payment_Name,Payment_price,Payment_Payment_history;
        public Holder(@NonNull View itemView) {
            super(itemView);
                Log.d("Home", "yes");
                Payment_Name =itemView.findViewById(R.id.Payment_rv_ed_name);
                Payment_price =itemView.findViewById(R.id.Payment_ed_cost);
                Payment_Payment_history =itemView.findViewById(R.id.Payment_tv_Payment_history);

        }
    }
    interface PaymentListener{
        void Onclick(int id);
    }
}
