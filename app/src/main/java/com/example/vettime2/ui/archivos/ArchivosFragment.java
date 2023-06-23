package com.example.vettime2.ui.archivos;

import static android.app.Activity.RESULT_OK;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.modelos.Mascota;

import java.io.IOException;

public class ArchivosFragment extends Fragment {

    private ArchivosViewModel mViewModel;
    private FragmentArchivosBinding binding;
    private static final int SELECT_REQUEST_CODE = 1;
    private static final int CAPTURE_REQUEST_CODE = 0;
    private Mascota mascota = new Mascota();
    private Consulta consulta = new Consulta();
    private Bundle bundle = new Bundle();
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public Boolean permissionGranted = false;

    public static ArchivosFragment newInstance() {
        return new ArchivosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        permissionGranted = CheckPermission();

        binding = FragmentArchivosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(ArchivosViewModel.class);

        String origen = getArguments().getString("origen");
        switch (origen) {
            case "comprobante":
                try {
                    consulta = (Consulta) getArguments().getSerializable("consulta");
                }catch (NullPointerException e){
                    Log.e("ERROR", "Error en ArchivosFragment: " + e.getMessage());
                }
                break;
            case "perfil":

                break;
            case "mascota":
                try {
                    mascota = (Mascota) getArguments().getSerializable("mascota");
                }catch (NullPointerException e){
                    Log.e("ERROR", "Error en ArchivosFragment: " + e.getMessage());
                }
                break;
        }

        mViewModel.getImagen().observe(getViewLifecycleOwner(), imagenCliente -> {
           bundle.putString("imagenCliente", imagenCliente);
           Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_archivosFragment_to_perfilFragment, bundle);
        });

        mViewModel.getmMascota().observe(getViewLifecycleOwner(), mMascota -> {
           bundle.putSerializable("mascota", mMascota);
           Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_archivosFragment_to_detalleMascotaFragment, bundle);
        });

        binding.selectImage.setOnClickListener(view -> {
            Log.d("imagen", LogInActivity.permissionGranted+"");
            if (permissionGranted) {
                Intent select = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(select, SELECT_REQUEST_CODE);
            }
        });

       binding.captureImage.setOnClickListener(view -> {
            if (permissionGranted) {
                Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(capture, CAPTURE_REQUEST_CODE);
            }
        });

       mViewModel.getConsulta().observe(getViewLifecycleOwner(), consulta -> {
           Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_archivosFragment_to_navigation_dashboard);
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
                        mViewModel.imageUpload(bitmap,mascota,consulta);
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
                            mViewModel.imageUpload(bitmap,mascota,consulta);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            break;
        }

    }

    public boolean CheckPermission() {
        if (ContextCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(this.getContext())
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", (dialogInterface, i) -> {
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(this.getActivity(),
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_LOCATION);
                            startActivity(new Intent(this.getContext(), ArchivosFragment.class));
                            this.getActivity().overridePendingTransition(0, 0);
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(this.getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;

        }
    }

}