package com.example.TibiaTools.Operaciones;

public class staminaTibia {
    int horaMinutos, minutosTopStamina, minutosTotales, totalMinutosStamina, tiempoReal, minutosHora,minutosRestantes;
    int horaMinutosStamina, minutosStamina;

    public void staminaTibia (int horaMinutos, int minutosTopStamina, int minutosTotales, int totalMinutosStamina,
                              int tiempoReal, int minutosHora, int minutosRestantes, int horaMinutosStamina, int minutosStamina){
        this.horaMinutos = horaMinutos;
        this.minutosTopStamina = minutosTopStamina;
        this.minutosTotales = minutosTotales;
        this.totalMinutosStamina = totalMinutosStamina;
        this.tiempoReal = tiempoReal;
        this.minutosHora = minutosHora;
        this.minutosRestantes = minutosRestantes;
        this.horaMinutosStamina = horaMinutosStamina;
        this.minutosStamina = minutosStamina;
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
    public void convertirHoraMinutosReales(int hora){
        minutosStamina = hora * 60;
    }

    //retornamos los minutos reales
    public int getHora(){
        return minutosStamina;
    }

    public void convertirMinutosHora(int minutos){
        horaMinutosStamina = minutos / 60;
        convertirHoraMinutosReales(horaMinutosStamina);
        minutosRestantes = minutos - getHora();
    }

    public int getHorasReales(){
        return horaMinutosStamina;
    }

    public int getMinutosReales(){
        return minutosRestantes;
    }

}
