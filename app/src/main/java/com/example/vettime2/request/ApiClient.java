package com.example.vettime2.request;

import com.example.vettime2.modelos.Cliente_mascota;
import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.modelos.Empleado_tarea;
import com.example.vettime2.modelos.Mascota;
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
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiClient {

    //private static final String PATH="http://10.120.10.172:5200/api/";//"http://localhost:5500/api/";
    private static final String PATH="http://192.168.15.31:5200/api/";
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

        @GET("Tareas")
        Call<List<Tarea>> obtenerTareas();

        @GET("EmpleadosTareas")
        Call<List<Empleado_tarea>> obtenerEmpleadosTareas();

        @GET("Mascotas")
        Call<List<Mascota>> obtenerMascotas();

        @GET("ClientesMascotas")
        Call<List<Cliente_mascota>> obtenerClientesMascotas();

        @GET("Tareas/Turnos/{tarea}/{fecha}/{empleado}")
        Call<List<TurnosPorTarea>> obtenerTurnosPorFecha(@Path("tarea")String tarea, @Path("fecha") String fecha, @Path("empleado") String empleado);

        @GET("Consultas/Turnos/{fecha}/{empleado}")
        Call<List<Consulta>> obtenerConsultasPorFecha(@Path("fecha") String fecha, @Path("empleado") String empleado);

        @POST("Consultas/{empleado}")
        Call<Consulta> nuevaConsultas(@Body Consulta consulta, @Path("empleado") String empleado);

    }

}
