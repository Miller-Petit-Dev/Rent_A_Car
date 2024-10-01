package com.duoc.rent_a_car.entites.client;

import com.duoc.rent_a_car.entites.cars.Vehiculo;
import java.util.HashMap;
import java.util.Map;

public class Cliente {

    private Integer idCliente;
    private String nombreCliente;
    private Integer telefono;
    private Map<String, Vehiculo> listaVehiculos;

    public Cliente() {
        this.listaVehiculos = new HashMap<>(); // Inicialización del HashMap
    }

    public Cliente(Integer idCliente, String nombreCliente, Integer telefono) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.listaVehiculos = new HashMap<>();
    }

    public Cliente(Integer idCliente, String nombreCliente, Integer telefono, Map<String, Vehiculo> listaVehiculos) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        //condicion              si es verddero    si es falso
        this.listaVehiculos = (listaVehiculos != null) ? listaVehiculos : new HashMap<>();
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Map<String, Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(Map<String, Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombreCliente=" + nombreCliente + ", telefono=" + telefono + '}';
    }

}
