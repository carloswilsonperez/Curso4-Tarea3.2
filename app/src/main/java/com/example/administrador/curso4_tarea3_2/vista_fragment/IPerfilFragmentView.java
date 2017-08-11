package com.example.administrador.curso4_tarea3_2.vista_fragment;

import com.example.administrador.curso4_tarea3_2.adapter.PerfilAdaptador;
import com.example.administrador.curso4_tarea3_2.pojo.Mascota;
import com.example.administrador.curso4_tarea3_2.pojo.Perfil;

import java.util.ArrayList;

/**
 * Created by administrador on 25/06/17.
 */

public interface IPerfilFragmentView {

    public void generarGridLayout();

    public PerfilAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(PerfilAdaptador adaptador);

    public void mostrarPerfil(ArrayList<Perfil> perfiles);
}
