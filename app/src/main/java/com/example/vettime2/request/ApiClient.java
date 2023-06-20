package com.example.vettime2.request;

import com.example.vettime2.modelos.Cliente;
import com.example.vettime2.modelos.Cliente_mascota;
import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.modelos.Empleado_tarea;
import com.example.vettime2.modelos.Mascota;
import com.example.vettime2.modelos.Sucursal;
import com.example.vettime2.modelos.Tarea;
import com.example.vettime2.modelos.TurnosPorTarea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiClient {
    private static final String PATH="http://192.168.15.7:5111/api/";
    private static EndPointVetTime endPointVetTime;

    public static EndPointVetTime getEndpointVetTime(){

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        endPointVetTime=retrofit.create(EndPointVetTime.class);

        return endPointVetTime;
    }

    public interface EndPointVetTime{

        @GET("EmpleadosTareas")
        Call<List<Empleado_tarea>> obtenerEmpleadosTareas(@Header("Authorization")String token);

        @GET("Mascotas")
        Call<List<Cliente_mascota>> obtenerMascotas(@Header("Authorization")String token);

        @GET("ClientesMascotas")
        Call<List<Cliente_mascota>> obtenerClientesMascotas(@Header("Authorization")String token);

        @GET("Tareas/Turnos/{tarea}/{fecha}/{empleado}")
        Call<List<TurnosPorTarea>> obtenerTurnosPorFecha(@Header("Authorization")String token,@Path("tarea")String tarea, @Path("fecha") String fecha, @Path("empleado") String empleado);

        @GET("Consultas/Turnos/{fecha}/{empleado}")
        Call<List<Consulta>> obtenerConsultasPorFecha(@Header("Authorization")String token,@Path("fecha") String fecha, @Path("empleado") String empleado);

        @POST("Consultas/{empleado}")
        Call<Consulta> nuevaConsultas(@Header("Authorization")String token,@Body Consulta consulta, @Path("empleado") String empleado);

        @POST("Mascotas")
        Call<Mascota> editaMascota(@Header("Authorization")String token,@Body Mascota mascota);

        @POST("Mascotas/Nueva")
        Call<Mascota> nuevaMascota(@Header("Authorization")String token,@Body Mascota mascota);

        @GET("Sucursales")
        Call<Sucursal> getSucursal();

        @GET("Clientes")
        Call<Cliente> getCliente(@Header("Authorization")String token);

        @POST("Clientes")
        Call<Cliente> editaCliente(@Header("Authorization")String token,@Body Cliente cliente);

        @GET("Consultas/Pendientes")
        Call<List<Consulta>> obtenerConsultasPendientes(@Header("Authorization")String token);

        @GET("Consultas/Historial")
        Call<List<Consulta>> obtenerConsultasHistorial(@Header("Authorization")String token);

        @POST("Clientes/login")
        Call<Cliente> loginCliente(@Header("Authorization")String token, @Body Cliente cliente);

    }

}
