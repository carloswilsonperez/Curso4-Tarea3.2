package com.example.administrador.curso4_tarea3_2.servicios;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by administrador on 05/08/17.
 */

public class NotificationIDTokenService extends FirebaseInstanceIdService {

    public final static String TAG = "FIREBASE_TOKEN";

    @Override //Metodo que obtien el token
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken(); // Obtiene el token
        enviarTokenRegistro(token);
    }

    // Metodo para enviar el tokens
    private void enviarTokenRegistro(String token){
        Log.d(TAG, token);
    }
}
