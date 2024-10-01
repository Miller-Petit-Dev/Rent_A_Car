package com.duoc.rent_a_car.entites.cars;

public class Camion extends Vehiculo {

    private String capacidadDeCarga;

    public Camion() {
    }

    public Camion(String capacidadDeCarga, String patente, Integer capacidadPasajeros, String marca, String categoria, Integer valorArriendo, String tipoVehiculo) {
        super(patente, capacidadPasajeros, marca, categoria, valorArriendo, tipoVehiculo);
        this.capacidadDeCarga = capacidadDeCarga;
    }

    public Camion(String patente, Integer capacidadPasajeros, String marca, String categoria, Integer valorArriendo, String tipoVehiculo, Integer diasArriendo) {
        super(patente, capacidadPasajeros, marca, categoria, valorArriendo, tipoVehiculo, diasArriendo);
    }
    
    public String getCapacidadDeCarga() {
        return capacidadDeCarga;
    }

    public void setCapacidadDeCarga(String capacidadDeCarga) {
        this.capacidadDeCarga = capacidadDeCarga;
    }

    @Override
    public void showDataCar() {
        System.out.println("Informacion de Camion\n"
                + "Categoria: " + getCategoria() + "\n"
                + "Patente: " + getPatente() + "\n"
                + "Tipo: " + getTipoVehiculo() + "\n"
                + "Marca: " + getMarca() + "\n"
                + "Capacidad de pasajeros: " + getCapacidadPasajeros() + "\n"
                + "Capacidad de carga: " + getCapacidadDeCarga() + "\n"
                + "Valor Arriendo: " + getValorArriendo());
    }

}
