package com.example.apisncf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    ArrayList<Sncf> arrayList = new ArrayList<Sncf>() ;
    private Context context ;


    public recyclerAdapter(ArrayList<Sncf> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    public void removeItem(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public MyViewHolder  onCreateViewHolder( ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlist,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.txt_arrive.setText(arrayList.get(position).getDateArrive());
        holder.txt_heure.setText(arrayList.get(position).getDateDepart());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_arrive,txt_heure;
        CardView view_card;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_arrive = (TextView) itemView.findViewById(R.id.txt_arrive);
            txt_heure = (TextView) itemView.findViewById(R.id.txt_heure);
            view_card = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}