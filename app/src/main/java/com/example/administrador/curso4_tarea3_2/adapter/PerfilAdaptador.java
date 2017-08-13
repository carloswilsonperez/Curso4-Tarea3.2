package com.example.administrador.curso4_tarea3_2.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrador.curso4_tarea3_2.R;
import com.example.administrador.curso4_tarea3_2.pojo.Mascota;
import com.example.administrador.curso4_tarea3_2.restApi.ConstantesRestApi;
import com.example.administrador.curso4_tarea3_2.restApi.DatosPreferencias;
import com.example.administrador.curso4_tarea3_2.restApi.EndpointsApi;
import com.example.administrador.curso4_tarea3_2.restApi.adapter.RestApiAdapter;
import com.example.administrador.curso4_tarea3_2.restApi.model.LikeResponse;
import com.example.administrador.curso4_tarea3_2.restApi.model.UsuarioResponse;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by administrador on 18/05/17.
 */

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;
    DatosPreferencias datosPreferencias;
    Context context;


    //******** Constructor *******
    public PerfilAdaptador (ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    // Método que va a inflar el layout y lo pasara al ViewHolder para que obtenga los views
    @Override
    public PerfilAdaptador.PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        return new PerfilAdaptador.PerfilViewHolder(v);
    }


    // Se setean los datos de la clase MascotaViewHolder con los datos de la lista recibida
    @Override
    public void onBindViewHolder(final PerfilAdaptador.PerfilViewHolder mascotaViewHolder, int position){
        final Mascota mascota = mascotas.get(position); //Obtiene todos los datos de la mascota en la posición position
        String ruta = mascota.getUrlFoto();

        ruta = ruta.replaceAll("\"", ""); //Quito las comillas dobles que vienen con la url desde el json
 /*       Log.i(TAG, "La ruta la url es:"+ ruta);  */
        Picasso.with(activity) // Libreria para traer las fotos
                .load(ruta) // trae la foto del usuarioApi
                .into(mascotaViewHolder.imgFoto); // ImagenView dode se va a mostrar la foto
        mascotaViewHolder.tvNumLikes.setText(Integer.toString(mascota.getLikes()));// Seteo el Número de likes del cardView
        mascotaViewHolder.llCardViewPerfil.setBackgroundResource(mascota.getColorFondo()); // Establece el color de fondo

        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Codigo que se ejecuta al harcer click sobre la foto
                String tokenInstagram = ConstantesRestApi.ACCESS_TOKEN;
                enviarLike(tokenInstagram);
            }
        });

    }

    @Override
    public int getItemCount(){
        if(mascotas == null){
            return 0;
        } else {
            return mascotas.size();
        }
    }

    // Metodo para enviar el tokens y el id de usuario instagram
    private void enviarLike(String token){
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //instancio el adaptador
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiInstagram2(); //Conecta con el servidor de Instagram2
        String idFotoInstagram = "1536671950728966930_5557323253";
        Call<LikeResponse>  likeResponseCall = endpoints.setLike(idFotoInstagram, token);
        // verificamos si salió bien
        likeResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                Log.d("LikeOK", "Se dio like ok");
                Toast.makeText(activity, "tocaste mi foto", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                Log.d("LikeError", "Algo salió mal al dar like");
            }
        });
    }



    //**********  Clase interna MascotaViewHolder *****************
    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNumLikes;
        private LinearLayout llCardViewPerfil;

        // Constructor
        public PerfilViewHolder(View itemView){
            super(itemView);
            // Cargo todos las vistas del cardview
            this.imgFoto    = (ImageView) itemView.findViewById(R.id.imgFoto);;
            this.tvNumLikes = (TextView) itemView.findViewById(R.id.tvNumLikes);
            // Agregado
            this.llCardViewPerfil = (LinearLayout) itemView.findViewById(R.id.llCardViewPerfil);
        }

    }


}
