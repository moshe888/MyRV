package com.example.myrv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustemAdapter extends RecyclerView.Adapter<CustemAdapter.MyViewHolder> {
    private ArrayList<DataModel> dataSet;

    public CustemAdapter(ArrayList<DataModel> dataSet) {
        this.dataSet = dataSet;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            textViewName = itemView.findViewById(R.id.textView1Card);
            textViewVersion = itemView.findViewById(R.id.textView2Card);
            imageView = itemView.findViewById(R.id.imageViewCard);

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( parent.getContext()).inflate(R.layout.cardview, parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
     }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textViewName = holder. textViewName;
        TextView textViewVersion = holder. textViewVersion;
        ImageView imageView = holder. imageView;

        CardView cardView = holder.cardView;
        textViewName. setText (dataSet.get (position) .getName());
        textViewVersion.setText (dataSet.get (position) .getVersion());
        imageView. setImageResource (dataSet . get (position) .getImage());


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}