package com.duoc.rent_a_car.interfaces;

import com.duoc.rent_a_car.entites.client.Cliente;
import com.duoc.rent_a_car.outputs.ClientPersistentOutput;

public interface IOperacionesCliente {

    public void registrarUsuario(ClientPersistentOutput clientPersistent);
    public Cliente obtenerClientePorId(int idCliente);
    
}
