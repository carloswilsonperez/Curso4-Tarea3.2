package com.example.administrador.curso4_tarea3_2.restApi.model;

/**
 * Created by administrador on 13/08/17.
 */

public class LikeResponse {

    Integer respuesta;

    public LikeResponse(Integer respuesta) {
        this.respuesta = respuesta;
    }

    public LikeResponse() {
    }

    public Integer getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Integer respuesta) {
        this.respuesta = respuesta;
    }
}
