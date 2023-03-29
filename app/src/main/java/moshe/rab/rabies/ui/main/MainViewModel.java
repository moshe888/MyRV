package moshe.rab.rabies.ui.main;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import moshe.rab.rabies.ui.main.add_rabi.AddRabiFragment;

public class MainViewModel extends ViewModel {
    public void goToAddRabi(Fragment fragment) {
        fragment.getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AddRabiFragment.newInstance())
                .commitNow();
    }
}