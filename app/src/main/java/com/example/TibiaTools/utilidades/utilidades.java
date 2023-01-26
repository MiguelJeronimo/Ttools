package com.example.TibiaTools.utilidades;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class utilidades {

    public static Boolean suRun() throws IOException, InterruptedException {
        try {
            Process su = null;
            //ejecucion de comandos desde java
            su = Runtime.getRuntime().exec(new String[] {"su","-c","exit"});
            //espera la respuesta
            su.waitFor();
            //Se lee la respuesta
            InputStream in = su.getInputStream();
            //leer archivos en java
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            //se accede a la info de la respuesta
            String suOutput = bufferedReader.readLine();
            Log.d("RESPUESTA: ",suOutput);
            if (suOutput == null) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isPhoneRooted() {
        // check if /system/app/Superuser.apk is present and can run su
        try {
            //acceder a un archivo en java
            File file = new File("/system/app/Superuser.apk");
            //validamos si el archivo exite y suRun ejecuto el comando satisfactoriamente
            if (file.exists() && suRun()) {
                Log.d("Blocking Service", "ROOTED PHONE DETECTED");
                return true;
            } else {
                Log.d("No se puede", "TELEFONO NO ROOT");
            }
        }
        catch (Throwable e1) {
            // ignore
        }

        return false;
    }
}
