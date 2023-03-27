package moshe.rab.rabies.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import moshe.rab.rabies.R;
import moshe.rab.rabies.RabiAdapter;
import moshe.rab.rabies.models.Rabi;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    FloatingActionButton fBtnAddRabi;
    RecyclerView rvRabies;
    ArrayList<Rabi> rabiArrayList;
    RabiAdapter rabiAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        rvRabies = view.findViewById(R.id.rvRabies);

        fBtnAddRabi = view.findViewById(R.id.fBtnAddRabi);
        fBtnAddRabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.goToAddRabi(MainFragment.this);
            }
        });

        showRabies();

        return view;
    }

    public void showRabies() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Rabies");
        rabiArrayList = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rabiArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Rabi temp = new Rabi((HashMap<String, Object>) dataSnapshot.getValue());
                    rabiArrayList.add(temp);
                }
                rabiAdapter = new RabiAdapter(getContext(),rabiArrayList);
                rvRabies.setHasFixedSize(false);
                rvRabies.setLayoutManager(new LinearLayoutManager(getContext()));
                rvRabies.setAdapter(rabiAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}