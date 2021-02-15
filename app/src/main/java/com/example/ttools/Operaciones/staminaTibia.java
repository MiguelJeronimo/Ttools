package com.example.ttools.Operaciones;

public class staminaTibia {
    int horaMinutos, minutosTopStamina, minutosTotales, totalMinutosStamina, tiempoReal, minutosHora,minutosRestantes;

    public void staminaTibia (int horaMinutos, int minutosTopStamina, int minutosTotales, int totalMinutosStamina, int tiempoReal, int minutosHora, int minutosRestantes){
        this.horaMinutos = horaMinutos;
        this.minutosTopStamina = minutosTopStamina;
        this.minutosTotales = minutosTotales;
        this.totalMinutosStamina = totalMinutosStamina;
        this.tiempoReal = tiempoReal;
        this.minutosHora = minutosHora;
        this.minutosRestantes = minutosRestantes;
    }

    //convertiremos las horas en minutos
    public void convertirHoraMinutosStamina(int Hora, int Minutos){
        horaMinutos = Hora * 60;
        minutosTopStamina = 42 * 60;
        minutosTotales = horaMinutos + Minutos;
        totalMinutosStamina = minutosTopStamina - minutosTotales;
    }
    //retornando el valor de las horas convertidas en minutos
    public int getConvertirHoraMinutosStamina(){
        return  totalMinutosStamina;
    }

    //calculando los minutos reales que tardara la stamina en recargarse
    public void minutoStamina(int minutoStamina){
        tiempoReal = (minutoStamina * 6)+10;
    }

    //retornar el valor del tiempo real que tarda la stamina en recargar
    public int getminutoStamina(){
        return tiempoReal;
    }

    //Conversión de horas a minutos
    public void Hora(int hora){
        minutosHora = hora * 60;
    }
    //retornamos los minutos
    public int getHora(){
        return minutosHora;
    }

    public void convertirMinutosHora(int Minutos){
        horaMinutos = Minutos / 60;
        Hora(Minutos);
        minutosRestantes = Minutos - getHora();
    }

    public int getHorasReales(){return horaMinutos;}
    public int getMinutosReales(){
        return minutosRestantes;
    }

/***/
}
