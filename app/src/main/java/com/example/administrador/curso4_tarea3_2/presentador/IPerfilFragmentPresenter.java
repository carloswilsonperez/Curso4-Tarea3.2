package com.example.administrador.curso4_tarea3_2.presentador;

/**
 * Created by administrador on 25/06/17.
 */

public interface IPerfilFragmentPresenter {

    public void obtenerMediosRecientes();

    public void mostrarMediosRecientesRv();

    public void obtenerPerfil();

    public void obtenerIdPerfil(String usuario);

    public void enviarIdPerfil();

    public void mostrarPerfil();
}
