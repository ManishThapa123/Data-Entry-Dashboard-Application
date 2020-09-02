package com.manish.mydashboardapplication.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.manish.mydashboardapplication.R;
import com.manish.mydashboardapplication.SingleClientDetail;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.MyViewHolder> {
    private Context context;
    private List<Client> mDataList;

    public ClientAdapter(Context context, List<Client> mDataList) {
        this.context = context;
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mRootView = LayoutInflater.from(context).inflate(R.layout.client_details,parent,false);
        return new MyViewHolder(mRootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Client client = mDataList.get(position);

        holder.txt1.setText("Property Name: " + client.getPropertyName());
        holder.txt2.setText("City: "+client.getCityName());
        holder.txt3.setText("Area: "+client.getArea());
//        long cNumber = client.getClientNumber();
        holder.txt4.setText("Client Number: "+String.valueOf(client.getClientNumber()));
        holder.txt5.setText("Owners Name" +client.getOwnersName());
        holder.txt6.setText("Preferred Language: "+client.getLanguage());
        holder.txt7.setText("Pending");


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SingleClientDetail.class);
                i.putExtra("pname",client.getPropertyName());
                i.putExtra("city", client.getCityName());
                i.putExtra("area",client.getArea());
                i.putExtra("cNumber",String.valueOf(client.getClientNumber()));
                i.putExtra("oName",client.getOwnersName());
                i.putExtra("pLang",client.getLanguage());
                i.putExtra("pending", "Pending");
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;

        TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt1 = itemView.findViewById(R.id.txtPname);
            txt2 = itemView.findViewById(R.id.txtLocation);
            txt3 = itemView.findViewById(R.id.txtArea);
            txt4 = itemView.findViewById(R.id.txtNumber);
            txt5 = itemView.findViewById(R.id.txtOwnerName);
            txt6 = itemView.findViewById(R.id.txtLanguage);
            txt7 = itemView.findViewById(R.id.txtStatus);
            cardView = itemView.findViewById(R.id.cardView);
        }




    }
}
