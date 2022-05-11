package com.example.ttools.Operaciones;

public class ExperienciaCompartida extends calcularBlessings{
/**CALCULAREMOS LA EXPERIENCIA COMPARTIDA EN ESTA CLASE*/


    private int mayor,menor;


    public ExperienciaCompartida(int nivel){
        super(nivel);
    }

    public ExperienciaCompartida(){}


    /**FORMUALA
     * RANGO MENOR = FUNCION_PISO((NIVEL*2)/3))
     * **/
    /**Calculado el rango menor**/
    public void  CalculoRangoMenor(double nivel){
        double numero1 =  ((nivel*2)/3);
        int rango_menor = (int) Math.ceil(numero1); //La función Math.ceil(x) devuelve el entero más pequeño mayor o igual a un número dado.
        menor =rango_menor;

    }

    /**FORMUALA
     * RANGO MENOR = FUNCION_TECHO((NIVEL*3)/2))
     * **/

    /**Calculando el rango mayor**/
    public void  CalculoRangoMayor(double nivel){
        double numero2 = ((nivel*3)/2);
        int rango_mayor = (int) Math.floor(numero2); //La función Math.ceil(x) devuelve el entero más pequeño mayor o igual a un número dado.
        mayor=rango_mayor;
    }

    /**Retornando los valores del calculo de los rangos**/
    public int getRangoMenor(){
        return menor;
    }
    public int getRangoMayor(){
        return mayor;
    }

}
