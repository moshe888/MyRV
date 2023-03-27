package moshe.rab.rabies.ui.main;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import moshe.rab.rabies.R;
import moshe.rab.rabies.ui.main.add_rabi.AddRabiFragment;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel


    public void goToAddRabi(Fragment fragment) {
        fragment.getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AddRabiFragment.newInstance())
                .commitNow();
    }

}