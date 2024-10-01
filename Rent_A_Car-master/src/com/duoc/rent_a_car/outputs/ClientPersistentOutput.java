package com.duoc.rent_a_car.outputs;

import com.duoc.rent_a_car.entites.client.Cliente;
import java.io.FileWriter;
import java.io.IOException;

public class ClientPersistentOutput {
    public void almacenarUsuariosEnTxt(Cliente cliente) {
        String salto = System.lineSeparator();
        try (FileWriter writer = new FileWriter("lista_clientes.txt", true)) {
            writer.write(cliente.toString() + salto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
