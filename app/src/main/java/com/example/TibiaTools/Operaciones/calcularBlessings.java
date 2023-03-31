package com.example.TibiaTools.Operaciones;

public class calcularBlessings {
    private int nivel;
    private int blessing;
    private int blessing_especial;
    private int suma_blessings_principales;
    private int bless_pvp;
    private int setHeartOfMountain;
    private int setBloodOfMountain;

    public calcularBlessings() { }

    public calcularBlessings(int nivel) { }

    public void calcularBlessings(int nivel){
            this.nivel=nivel;
    }

    public void blessingIndividual (int nivel){ //Calculo de las blessings principales individualmente (el precio aplica tambien para la twits of fate)
         int constante = 200;
         if (nivel <= 30){
             blessing = 2000;
         } else if(nivel>=31&&nivel<120){//si entra en el rango de 31 a 120 aplica la formula
             blessing = constante*(nivel - 20);
         }else{
             //20000 + 75 gps por cada nivel adicional
             //twist of fate no tiene cambios, sigue igual que siempre
             int constanteBless = 20000;
             blessing = ((nivel - 120) *75 ) + constanteBless;
         }
    }
    /*
    * Bless del pvp
    * */
    public void blessingTwistOfFate (int nivel){ //Calculo de las blessings principales individualmente (el precio aplica tambien para la twits of fate)
        int constante = 200;
        if (nivel <= 30){
            bless_pvp = 2000;
        } else if(nivel>=31&&nivel<120){//si entra en el rango de 31 a 120 aplica la formula
            bless_pvp = constante*(nivel - 20);
        }else{
            bless_pvp = 20000;
        }
    }

    public void blessingEspecial(int nivel){  // calculo de las blessings especiales (Heart of the Mountain y 	Blood of the Mountain) individualmente
        int constante = 260;
        if (nivel <=30 ){
            blessing_especial = 2600;
        } else if (nivel>=31&&nivel<120){//si entra en el rango de 31 a 120 aplica la formula
            blessing_especial = constante *(nivel -20);
        } else{
            //20000 + 100 gps por cada nivel adicional
            int constanteBless = 26000;
            blessing_especial = ((nivel - 120) * 100) + constanteBless;
        }
    }

    public void sumaBlessingsPrincipales(int nivel){  // es la suma de las cinco blessings principales sin la twits of fate
         suma_blessings_principales = getBlessingIndividual()*5;  //suando las blessings principales
    }

    public int getBlessingIndividual(){return blessing;} //devuelve el costo unitario de la blessings principales

    public int getBlessingEspecial(){return blessing_especial;}//devuelve el valor unitario de las blessings especiales

    public int getSumaBlessingsPrincipales(){return suma_blessings_principales;}//devuelve la suma de las 5 bless principales sin la twits of fate

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

    public int getblessingTwistOfFate(){return bless_pvp;}

}
