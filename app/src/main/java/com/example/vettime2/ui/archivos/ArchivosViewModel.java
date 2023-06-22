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
import com.example.vettime2.modelos.Consulta;
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
    private MutableLiveData<Consulta> mConsulta;

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

    public LiveData<Consulta> getConsulta(){
        if (mConsulta == null){
            mConsulta = new MutableLiveData<>();
        }
        return mConsulta;
    }

    public void imageUpload(Bitmap bitmap, Mascota mascota, Consulta consulta){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        if (consulta.getId() != 0){
            String name = "comprobante_"+consulta.getId()+"_"+Calendar.getInstance().getTimeInMillis();
            try {
                Call<Consulta> call = end.UploadImageConsulta(token,name,image,consulta.getId());
                call.enqueue(new Callback<Consulta>() {
                    @Override
                    public void onResponse(@Nullable Call<Consulta> call, @Nullable Response<Consulta> response) {
                        if (response != null && response.body() != null) {
                            Consulta consulta = response.body();
                            mConsulta.setValue(consulta);
                            Toast.makeText(context, "Comprobante subido", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(@Nullable Call<Consulta> call, @Nullable Throwable t) {
                      Log.d("salida 1", t.getMessage());
                    }
                });
            } catch (Exception e) {
                Log.d("salida 2", e.getMessage());
            }
        }
        else if(mascota.getId() == 0){
        String name = "user_"+Calendar.getInstance().getTimeInMillis();
        try {
        Call<Cliente> call = end.UploadImage(token,name,image);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(@Nullable Call<Cliente> call, @Nullable Response<Cliente> response) {
                if (response != null && response.body() != null) {
                    Cliente cliente = response.body();
                    mImagenCliente.setValue(cliente.getFoto());
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
        String name = "mascota_"+Calendar.getInstance().getTimeInMillis();
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