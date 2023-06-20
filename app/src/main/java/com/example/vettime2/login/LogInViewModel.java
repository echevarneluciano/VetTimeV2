package com.example.vettime2.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.auth0.android.result.UserProfile;
import com.example.vettime2.MainActivity;
import com.example.vettime2.modelos.Cliente;
import com.example.vettime2.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInViewModel extends AndroidViewModel {
    private Context context;
    private SharedPreferences sp;
    private ApiClient.EndPointVetTime end;

    public LogInViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
        sp = context.getSharedPreferences("token.xml",0);
    }

    public void login(String token, UserProfile userProfile){

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token","Bearer "+token);
        editor.commit();

        Cliente cliente = new Cliente();
        cliente.setActivo(1);
        cliente.setApellido(userProfile.getFamilyName());
        cliente.setNombre(userProfile.getNickname());
        cliente.setMail(userProfile.getName());
        cliente.setAuthId(userProfile.getId());
        Log.d("token",sp.getString("token",""));
        Log.d("cliente",cliente.toString()+" "+userProfile.getId());

        try {
            Call<Cliente> call= end.loginCliente(sp.getString("token",""),cliente);
            call.enqueue(new Callback<Cliente>() {
                @Override
                public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                    if(response.isSuccessful()){
                        if(response.body()!=null){
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("cliente",response.body());
                            context.startActivity(intent);
                        }else{
                            Toast.makeText(context,"Error al iniciar sesion",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<Cliente> call, Throwable t) {
                    Toast.makeText(context,"Error al llamar login",Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(context, "Error, informe a soporte.", Toast.LENGTH_LONG).show();
        }
    }

}
