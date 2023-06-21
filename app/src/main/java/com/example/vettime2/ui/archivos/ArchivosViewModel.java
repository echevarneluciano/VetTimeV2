package com.example.vettime2.ui.archivos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vettime2.modelos.Cliente;
import com.example.vettime2.modelos.Mascota;
import com.example.vettime2.request.ApiClient;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArchivosViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient.EndPointVetTime end;
    private SharedPreferences sp;
    private String token;
    private MutableLiveData<String> mImagenCliente;
    private MutableLiveData<Mascota> mMascota;

    public ArchivosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

    public LiveData<String> getImagen(){
        if (mImagenCliente == null){
            mImagenCliente = new MutableLiveData<>();
        }
        return mImagenCliente;
    }

    public LiveData<Mascota> getmMascota(){
        if (mMascota == null){
            mMascota = new MutableLiveData<>();
        }
        return mMascota;
    }

    public void imageUpload(Bitmap bitmap,Mascota mascota){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        if(mascota.getId() == 0){
        String name = "user_"+String.valueOf(Calendar.getInstance().getTimeInMillis());
        try {
        Call<Cliente> call = end.UploadImage(token,name,image);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(@Nullable Call<Cliente> call, @Nullable Response<Cliente> response) {
                if (response != null && response.body() != null) {
                 mImagenCliente.setValue(response.body().getFoto());
                }
            }

            @Override
            public void onFailure(@Nullable Call<Cliente> call, @Nullable Throwable t) {
              Log.d("salida 1", t.getMessage());
            }
        });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }else{
        String name = "mascota_"+String.valueOf(Calendar.getInstance().getTimeInMillis());
            try {
                Call<Mascota> call = end.UploadImageMascota(token,name,image,mascota.getId());
                call.enqueue(new Callback<Mascota>() {
                    @Override
                    public void onResponse(@Nullable Call<Mascota> call, @Nullable Response<Mascota> response) {
                        if (response != null && response.body() != null) {
                            mMascota.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(@Nullable Call<Mascota> call, @Nullable Throwable t) {
                        Log.d("salida 1", t.getMessage());
                    }
                });
            } catch (Exception e) {
                Log.d("salida 2", e.getMessage());
            }
    }
    }

}