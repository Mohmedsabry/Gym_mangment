package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
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

public class HomeAdpter extends RecyclerView.Adapter<HomeAdpter.Holder> implements Serializable, Parcelable {
    ArrayList<Subscibtor>arrayList;
    HomeListener listener;
    protected HomeAdpter(Parcel in) {
    }

    public static final Creator<HomeAdpter> CREATOR = new Creator<HomeAdpter>() {
        @Override
        public HomeAdpter createFromParcel(Parcel in) {
            return new HomeAdpter(in);
        }

        @Override
        public HomeAdpter[] newArray(int size) {
            return new HomeAdpter[size];
        }
    };

    public ArrayList<Subscibtor> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Subscibtor> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public HomeAdpter(ArrayList<Subscibtor> arrayList,HomeListener listener)
    {
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rv,null,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
            if (!arrayList.get(position).getName().equals("")){
                holder.Name.setText(arrayList.get(position).getName());
            }
            if (arrayList.get(position).getEndDate()!=null&&!arrayList.get(position).getEndDate().equals("")){
                DateFormat dateFormat = new SimpleDateFormat("M/d");
                Date date = new Date(arrayList.get(position).getEndDate());
                holder.End_date.setText(dateFormat.format(date).toString());
            }
            holder.Price.setText(String.valueOf(arrayList.get(position).getPayment()));
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
        TextView Name,End_date,Price;
        public Holder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Home_rv_ed_name);
            End_date = itemView.findViewById(R.id.Home_rv_ed_End_Date);
            Price = itemView.findViewById(R.id.Home_rv_ed_payment);

        }

    }
    interface HomeListener{
        void Onclick(int id);
    }
}
