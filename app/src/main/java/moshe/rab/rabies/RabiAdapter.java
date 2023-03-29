package moshe.rab.rabies;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import moshe.rab.rabies.models.Rabi;

public class RabiAdapter extends RecyclerView.Adapter<RabiAdapter.MiniRabiHolder> implements RecyclerView.OnItemTouchListener {

    public ArrayList<Rabi> mList;
    private final Context context;
    private final Activity activity;

    public RabiAdapter(Activity activity,Context context, ArrayList<Rabi> mList) {
        this.activity = activity;
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
        holder.tvDescription.setText("...");
        holder.tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder d = new AlertDialog.Builder(activity);
                d.setTitle("Description");
                d.setMessage(String.valueOf(mList.get(position).getDescription()));
                d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                d.show();
            }
        });
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

