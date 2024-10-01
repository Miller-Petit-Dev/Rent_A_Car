package com.duoc.rent_a_car.entites.cars;

public abstract class Vehiculo {

    private String patente;
    private Integer capacidadPasajeros;
    private String marca;
    private String categoria;
    private Integer valorArriendo;
    private String tipoVehiculo;
    private Integer diasArriendo;

    public Vehiculo() {
    }
    //SOBRECARGA DE METODOS
    public Vehiculo(String patente, Integer capacidadPasajeros, String marca, String categoria, Integer valorArriendo, String tipoVehiculo) {
        this.patente = patente;
        this.capacidadPasajeros = capacidadPasajeros;
        this.marca = marca;
        this.categoria = categoria;
        this.valorArriendo = valorArriendo;
        this.tipoVehiculo = tipoVehiculo;
    }
    public Vehiculo(String patente, Integer capacidadPasajeros, String marca, String categoria, Integer valorArriendo, String tipoVehiculo, Integer diasArriendo) {
        this.patente = patente;
        this.capacidadPasajeros = capacidadPasajeros;
        this.marca = marca;
        this.categoria = categoria;
        this.valorArriendo = valorArriendo;
        this.tipoVehiculo = tipoVehiculo;
        this.diasArriendo = diasArriendo;
    }
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Integer getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(Integer capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getValorArriendo() {
        return valorArriendo;
    }

    public void setValorArriendo(Integer valorArriendo) {
        this.valorArriendo = valorArriendo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Integer getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(Integer diasArriendo) {
        this.diasArriendo = diasArriendo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "patente=" + patente + ", capacidadPasajeros=" + capacidadPasajeros + ", marca=" + marca + ", categoria=" + categoria + ", valorArriendo=" + valorArriendo + ", tipoVehiculo=" + tipoVehiculo + ", diasArriendo=" + diasArriendo + '}';
    }
    
   

    //Metodo abstracto
    public abstract void showDataCar();

}
