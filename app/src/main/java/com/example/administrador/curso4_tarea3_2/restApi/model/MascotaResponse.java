package com.example.administrador.curso4_tarea3_2.restApi.model;

import com.example.administrador.curso4_tarea3_2.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by administrador on 19/06/17.
 */
// Clase para manejar la id_dispositivo con las mascotas de la API de Instagram
public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {

        this.mascotas = mascotas;
    }
}
