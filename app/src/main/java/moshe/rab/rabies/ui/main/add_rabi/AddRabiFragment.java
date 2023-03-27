package moshe.rab.rabies.ui.main.add_rabi;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import moshe.rab.rabies.R;


public class AddRabiFragment extends Fragment implements View.OnClickListener {

    private AddRabiViewModel mViewModel;
    private Button btnAddImage, btnUpload;
    ImageView ivImage;
    private final int GALLERY_REQUEST = 0;
    EditText etName, etDescription;
    Uri mImageUri;

    public static AddRabiFragment newInstance() {
        return new AddRabiFragment();
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_rabi, container, false);
        mViewModel = new ViewModelProvider(this).get(AddRabiViewModel.class);

        ivImage = view.findViewById(R.id.ivImage);

        etName = view.findViewById(R.id.etName);
        etDescription = view.findViewById(R.id.etDescription);

        btnAddImage = view.findViewById(R.id.btnAddImage);
        btnAddImage.setOnClickListener(this);

        btnUpload = view.findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(this);

        return view;
    }

    public void addImage() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERY_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {
            mImageUri = data.getData();
            ivImage.setImageURI(mImageUri);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddImage:
                addImage();
                break;
            case R.id.btnUpload:
                mViewModel.uploadRabiToFirebase(this,mImageUri,etName.getText().toString().trim(),etDescription.getText().toString().trim());
                break;
        }
    }
}