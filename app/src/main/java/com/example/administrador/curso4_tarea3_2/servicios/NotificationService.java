package com.example.administrador.curso4_tarea3_2.servicios;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.administrador.curso4_tarea3_2.Activity.MainActivity;
import com.example.administrador.curso4_tarea3_2.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by administrador on 05/08/17.
 */

public class NotificationService extends FirebaseMessagingService {

    public final static String TAG = "FIREBASE";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // super.onMessageReceived(remoteMessage);
        Log.d(TAG, "Tag From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Mensage Body: " + remoteMessage.getNotification().getBody());

        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT); //Intent especifico para notificaciones

        // Obtengo el sonido del teléfono configurado para notificaciones
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // ******* Crea la notificacion *********
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_perro)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                ;

        // Clase para manejar las notificaciones
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE); // Manejador de notificaciones
        notificationManager.notify(0, notificacion.build());

    }

}
