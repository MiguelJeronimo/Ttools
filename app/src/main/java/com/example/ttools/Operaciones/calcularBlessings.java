package com.example.ttools.Operaciones;

public class calcularBlessings {

    int nivel;

    public void calcularBlessings(int nivel){
            this.nivel=nivel;
    }

    public void calcularBlessings(){

    }



    public int blessingIndividual (int nivel){ //Calculo de las blessings principales individualmente (el precio aplica tambien para la twits of fate)

         int constante = 200;
         int blessing = constante*(nivel - 20);
         return blessing;
    }

    public int blessingEspecial(int nivel){  // calculo de las blessings especiales (Heart of the Mountain y 	Blood of the Mountain) individualmente

        int constante = 260;
        int blessing_especial = constante *(nivel -20);
        return blessing_especial;

    }

    public int sumaBlessingsPrincipales(int nivel){  // es la suma de las cinco blessings principales sin la twits of fate

        int suma_blessings_principales = blessingIndividual(nivel)*5;  //suando las blessings principales
        return suma_blessings_principales;

    }




}
