package com.example.ttools.Operaciones;

public class calcularBlessings {

    private int nivel, blessing, blessing_especial, suma_blessings_principales, totalBlessings, setHeartOfMountain, setBloodOfMountain;

    public calcularBlessings() {
       // this.nivel=nivel;
    }

    public calcularBlessings(int nivel) {
    }


    public void calcularBlessings(int nivel){
            this.nivel=nivel;
    }

    public void calcularBlessings(){

    }



    public void blessingIndividual (int nivel){ //Calculo de las blessings principales individualmente (el precio aplica tambien para la twits of fate)

         int constante = 200;
         blessing = constante*(nivel - 20);

        // sumaBlessingsPrincipales(nivel);
         //return blessing;
    }

    public void blessingEspecial(int nivel){  // calculo de las blessings especiales (Heart of the Mountain y 	Blood of the Mountain) individualmente

        int constante = 260;
        blessing_especial = constante *(nivel -20);
        //return blessing_especial;

    }

    public void sumaBlessingsPrincipales(int nivel){  // es la suma de las cinco blessings principales sin la twits of fate

         suma_blessings_principales = getBlessingIndividual()*5;  //suando las blessings principales
        //return suma_blessings_principales;

    }


    public void setTotalBlessings(int total){
        totalBlessings = nivel;
    }

    public int getBlessingIndividual(){return blessing;} //devuelve el costo unitario de la blessings principales

    public int getBlessingEspecial(){return blessing_especial;}//devuelve el valor unitario de las blessings especiales

    public int getSumaBlessingsPrincipales(){return suma_blessings_principales;}//devuelve la suma de las 5 bless principales sin la twits of fate

    public int getTotalBlessings(){return totalBlessings;}




/**
 * AQUI CALCULAREMOS EL TOTAL DE LAS BLESSINGS OPCIONALES Y SUMARLAS CON EL TOTAL DE LAS BLESSINGS NORMALES*/

    public void setHeartOfMountain(int setHeartOfMountain){
        this.setHeartOfMountain = setHeartOfMountain;
    }

    public void setBloodOfMountain(int setBloodOfMountain){
        this.setBloodOfMountain = setBloodOfMountain;
    }

    public int getHeartOfMountain(){

        return setHeartOfMountain;
    }

    public int getBloodOfMountain(){

        return setBloodOfMountain;
    }





}
