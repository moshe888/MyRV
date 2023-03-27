package moshe.rab.rabies;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import moshe.rab.rabies.models.Rabi;

public class RabiAdapter extends RecyclerView.Adapter<RabiAdapter.MiniRabiHolder> implements RecyclerView.OnItemTouchListener {

    public ArrayList<Rabi> mList;
    private final Context context;

    public RabiAdapter(Context context, ArrayList<Rabi> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MiniRabiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rabi_tile, parent, false);
        return new MiniRabiHolder(v);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull MiniRabiHolder holder, @SuppressLint("RecyclerView") int position) {

        if (!mList.get(position).getUrl().isEmpty())
            Glide.with(context).load(mList.get(position).getUrl()).into(holder.ivImage);
        holder.tvName.setText(String.valueOf(mList.get(position).getName()));
        holder.tvDescription.setText(String.valueOf(mList.get(position).getDescription()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MiniRabiHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvName,tvDescription;

        public MiniRabiHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}

