package com.duoc.rent_a_car.entites.cars;

public class AutoSedan extends Vehiculo{

    public AutoSedan() {
    }

    public AutoSedan(String patente, Integer capacidadPasajeros, String marca, String categoria, Integer valorArriendo, String tipoVehiculo) {
        super(patente, capacidadPasajeros, marca, categoria, valorArriendo, tipoVehiculo);
    }

    public AutoSedan(String patente, Integer capacidadPasajeros, String marca, String categoria, Integer valorArriendo, String tipoVehiculo, Integer diasArriendo) {
        super(patente, capacidadPasajeros, marca, categoria, valorArriendo, tipoVehiculo, diasArriendo);
    }
    
    @Override
    public void showDataCar() {
        System.out.println("Informacion de Automovil\n"
                + "Categoria: " + getCategoria() + "\n"
                + "Patente: " + getPatente() + "\n"
                + "Tipo: " + getTipoVehiculo() + "\n"
                + "Marca: " + getMarca() + "\n"
                + "Capacidad de pasajeros: " + getCapacidadPasajeros() + "\n"
                + "Valor Arriendo: " + getValorArriendo());
    }
    
}
