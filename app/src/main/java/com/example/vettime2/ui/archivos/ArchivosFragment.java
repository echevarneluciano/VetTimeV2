package com.example.vettime2.ui.archivos;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentArchivosBinding;
import com.example.vettime2.login.LogInActivity;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ArchivosFragment extends Fragment {

    private ArchivosViewModel mViewModel;
    private FragmentArchivosBinding binding;
    private static final int SELECT_REQUEST_CODE = 1;
    private static final int CAPTURE_REQUEST_CODE = 0;

    public static ArchivosFragment newInstance() {
        return new ArchivosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentArchivosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(ArchivosViewModel.class);

        mViewModel.getImagen().observe(getViewLifecycleOwner(), imagenCliente -> {
           Bundle bundle = new Bundle();
           bundle.putString("imagenCliente", imagenCliente);
           Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_archivosFragment_to_perfilFragment, bundle);
        });

        binding.selectImage.setOnClickListener(view -> {
            if (LogInActivity.permissionGranted) {
                Intent select = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(select, SELECT_REQUEST_CODE);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ArchivosViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case CAPTURE_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {

                    Bitmap bitmap;
                    if (data != null) {
                        bitmap = (Bitmap) data.getExtras().get("data");
                       binding.ImageView.setImageBitmap(bitmap);
                        mViewModel.imageUpload(bitmap);
                    }

                }

            }
            break;

            case SELECT_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {

                    try {
                        Uri ImageUri;
                        if (data != null) {
                            ImageUri = data.getData();
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), ImageUri);
                           binding.ImageView.setImageBitmap(bitmap);
                            //progressDialog.show();
                            mViewModel.imageUpload(bitmap);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            break;
        }

    }

}