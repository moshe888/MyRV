package moshe.rab.rabies.ui.main.add_rabi;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import moshe.rab.rabies.R;
import moshe.rab.rabies.models.Rabi;
import moshe.rab.rabies.ui.main.MainFragment;


public class AddRabiViewModel extends ViewModel {

    private String getFileExtension(Activity activity, Uri mUri) {
        ContentResolver cr = activity.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

    public void uploadRabiToFirebase(AddRabiFragment fragment, Uri uri, String name, String description) {
        if (uri != null && !name.isEmpty() && !description.isEmpty()) {
            final DatabaseReference root = FirebaseDatabase.getInstance().getReference("Rabies");
            final StorageReference reference = FirebaseStorage.getInstance().getReference();

            final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(fragment.getActivity(),uri));
            fileRef.putFile(uri).addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(url -> {
                String modelId = root.push().getKey();
                Rabi rabi = new Rabi(modelId, name, url.toString(), description);
                 root.child(modelId).setValue(rabi.toMap());
                Toast.makeText(fragment.getActivity(), "The rabi post uploaded", Toast.LENGTH_SHORT).show();
                fragment.getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .commitNow();
            })).addOnProgressListener(snapshot -> {
            }).addOnFailureListener(e -> {
                Toast.makeText(fragment.getActivity(), "The upload failed", Toast.LENGTH_SHORT).show();
            });
        } else Toast.makeText(fragment.getActivity(), "Enter all Data", Toast.LENGTH_SHORT).show();
    }
}