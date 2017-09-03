package com.example.administrador.curso4_tarea3_2.servicios;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.administrador.curso4_tarea3_2.Activity.MainActivity;
import com.example.administrador.curso4_tarea3_2.R;
import com.example.administrador.curso4_tarea3_2.vista_fragment.PerfilFragmentView;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import javax.mail.Quota;

/**
 * Created by administrador on 05/08/17.
 */
// Clase que maneja el serivcio de Notificaciones
public class NotificationService extends FirebaseMessagingService {

    public final static String TAG = "FIREBASE";

    @Override //este método se ejecuta al recibir una notificación
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // super.onMessageReceived(remoteMessage);
        Log.d(TAG, "Tag From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Mensage Body: " + remoteMessage.getNotification().getBody());

        Intent intent = new Intent(this, MainActivity.class);
        int numTab = 1;
        int notificacionId=128;

        intent.putExtra("numTab", numTab);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT); //Intent especifico para notificaciones

        // Obtengo el sonido del teléfono configurado para notificaciones
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        int color = getResources().getColor(R.color.colorAccent);


        // ******* Crea la notificacion *********
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setColor(color)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        // Clase para manejar las notificaciones
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE); // Manejador de notificaciones
        notificationManager.notify(notificacionId, notificacion.build());

    }

}
