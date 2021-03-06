package com.example.administrador.curso4_tarea3_2.vista_fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrador.curso4_tarea3_2.R;
import com.example.administrador.curso4_tarea3_2.adapter.PerfilAdaptador;
import com.example.administrador.curso4_tarea3_2.pojo.Mascota;
import com.example.administrador.curso4_tarea3_2.pojo.Perfil;
import com.example.administrador.curso4_tarea3_2.presentador.IPerfilFragmentPresenter;
import com.example.administrador.curso4_tarea3_2.presentador.PerfilFragmentPresenter;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragmentView extends Fragment implements IPerfilFragmentView {

    ArrayList<Mascota> mascotas;
    ArrayList<Perfil> perfiles;
    private RecyclerView rvPerfiles;
    private IPerfilFragmentPresenter presenter;
    private static final String KEY_EXTRA_URL = "url";
    private static final String KEY_EXTRA_LIKE = "like";
    private CircularImageView imgFotoCircular;
    private TextView tvNombrePerfil;

    // Primero hay que sobreescribir el método onCreateView
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil , container, false);
        imgFotoCircular = (CircularImageView) v.findViewById(R.id.imgFotoCirular);
        tvNombrePerfil = (TextView) v.findViewById(R.id.tvNombrePerfil);
        rvPerfiles = (RecyclerView) v.findViewById(R.id.rvPerfil);
        presenter = new PerfilFragmentPresenter(this, getContext());
        return v;
    }


    // Cargo las mascotas a mostrar
    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();

     /*   mascotas.add(new Mascota("Yaman", 2, getResources().getString(R.drawable.perro01), R.color.fondo_perro01));
        mascotas.add(new Mascota("Yaman", 5, R.drawable.perro01, R.color.fondo_perro01));
        mascotas.add(new Mascota("Yaman", 3, R.drawable.perro01, R.color.fondo_perro01));
        mascotas.add(new Mascota("Yaman", 3, R.drawable.perro01, R.color.fondo_perro01));
        mascotas.add(new Mascota("Yaman", 4, R.drawable.perro01, R.color.fondo_perro01));
        mascotas.add(new Mascota("Yaman", 2, R.drawable.perro01, R.color.fondo_perro01));
        mascotas.add(new Mascota("Yaman", 2, R.drawable.perro01, R.color.fondo_perro01));
        mascotas.add(new Mascota("Yaman", 2, R.drawable.perro01, R.color.fondo_perro01));
        mascotas.add(new Mascota("Yaman", 5, R.drawable.perro01, R.color.fondo_perro01));*/
    }


    @Override // Crea el GridLayout para presentar los perfiles
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getContext(), 3);
        rvPerfiles.setLayoutManager(glm);//Le decimos que el RecyclerView se comporte como un GridLayoutManager
    }

    @Override // Genera el adaptador que va a manejar el RecylerView, en este caso es el PerfilAdaptador y recibie un ArrayList de mascotas
    public PerfilAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        PerfilAdaptador adaptador = new PerfilAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override // Le indicamos al RecyclerView el adaptador que debe usar
    public void inicializarAdaptadorRV(PerfilAdaptador adaptador) {
        rvPerfiles.setAdapter(adaptador);
    }

    @Override // Muestra los datos del perfil en el PerfilFragment
    public void mostrarPerfil(ArrayList<Perfil> perfiles) {
        String nombrePerfil = perfiles.get(0).getNombreUsuario(); // obtiene el nombre de Usuario
        tvNombrePerfil.setText(nombrePerfil); //Setea el nombre de usuario
        String ruta = perfiles.get(0).getUrlFotoPerfil(); // Obtiene la ruta de la foto de perfil
        ruta = ruta.replaceAll("\"", ""); //Quito las comillas dobles que vienen con la url desde el json
        Picasso.with(getActivity()) // Libreria para traer las fotos
                .load(ruta) // trae la foto del perfil de usuario
                .resize(200,180)
                .centerCrop()
                .into(imgFotoCircular); // donde se va a mostrar la foto
    }
}
