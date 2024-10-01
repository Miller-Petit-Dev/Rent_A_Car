package com.duoc.rent_a_car;

import com.duoc.rent_a_car.entites.cars.OperacionesVehiculo;
import com.duoc.rent_a_car.entites.client.Finanzas;
import com.duoc.rent_a_car.entites.client.OperacionesCliente;
import com.duoc.rent_a_car.entites.threads.CargarDataCliente;
import com.duoc.rent_a_car.entites.threads.CargarDataVehiculo;
import com.duoc.rent_a_car.inputs.ClientPersistentInput;
import com.duoc.rent_a_car.inputs.VehiclePersistentInput;
import com.duoc.rent_a_car.outputs.ClientPersistentOutput;
import com.duoc.rent_a_car.outputs.VehiclePersistentOuput;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
Buenas profesor aca un pequeño resumen del proyecto para que no busque todas las cosas con mucho esfuerzo.
Partiendo con el uso de hilos, decidi usarlos para manejar la carga de informacion desde los archivos txt
tanto de clientes como autos registrados.
En cuanto a las listas de autos, use HashMap y ademas utilice listas syncronizadas para actualizar toda la info.
De hecho una vez que carga la informacion desde el archivo txt todo se agrega a la lista sincronizada de vehiculos y clientes.
En cuanto  la generacion de boleta y arriendos de vehiculos, quise hacerlo apto para cualquier usuario sin necesidad de que deba logearse
para ello cada vez que se intentan realizar estas acciones, se solicita el id del usuario en cuestion.
 */
public class Rent_A_Car {

    static ClientPersistentInput clientPersistentInput = new ClientPersistentInput();
    static ClientPersistentOutput clientPersistent = new ClientPersistentOutput();
    static VehiclePersistentInput persistentInput = new VehiclePersistentInput();
    static VehiclePersistentOuput ouput = new VehiclePersistentOuput();
    static OperacionesCliente operacionesCliente = new OperacionesCliente();
    static OperacionesVehiculo operacionesVehiculo = new OperacionesVehiculo();
    static Finanzas finanzas = new Finanzas();
    static Scanner sc = new Scanner(System.in);
    static int opcionMenu = 0;
    static boolean romperBucle = false;

    public static void main(String[] args) {
        //COMIENZO A INSTANCIAR MIS CLASES QUE MANEJARAN HILOS PARA CARGAR LA INFORMACION DE CLIENTES Y VEHICULOS
        Thread clienteThread = new Thread(new CargarDataCliente(operacionesCliente, clientPersistentInput));
        Thread vehiculoThread = new Thread(new CargarDataVehiculo(persistentInput, operacionesVehiculo));

        //INICIO LOS HILOS
        clienteThread.start();
        vehiculoThread.start();

        try {
            clienteThread.join();
            synchronized (persistentInput) {
                persistentInput.notifyAll();
            }
            vehiculoThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        menu();
    }

    public static void menu() {
        do {
            try {
                System.out.println("====================================================================================================");
                System.out.println("|                  BriefDrive                                                                      |");
                System.out.println("====================================================================================================");
                System.out.println("| Elige alguna de las siguientes opciones, escribiendo el numero segun corresponda                   |");
                System.out.println("| 1: Registrar usuario (selecciona esta opcion para poder arrendar vehiculos.)                     |");
                System.out.println("| 2: Mostrar tarifas de arriendo.                                                                  |");
                System.out.println("| 3: Arrendar vehiculo.                                                                            |");
                System.out.println("| 4: Ver listado de vehiculos disponibles.                                                          |");
                System.out.println("| 5: Ver vehiculos arrendados por mas de una semana.                                               |");
                System.out.println("| 6: Generar boleta.                                                                             |");
                System.out.println("| 7: Salir del sistema.                                                                          |");
                System.out.println("====================================================================================================");

                opcionMenu = sc.nextInt();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println("ERROR, debe ingresar un NUMERO para validar la opcion ingresada");
                sc.next(); //limpio el buffer
            }
            switch (opcionMenu) {
                //REGISTRO USUARIO
                case 1:
                    operacionesCliente.registrarUsuario(clientPersistent);
                    break;
                //MOSTRAR TARIFAS DE ARRIENDO
                case 2:
                    System.out.println("Las siguientes tarifas estan excentas de IVA\n"
                            + "Auto sedan: $50.000 por dia de arriendo.\n"
                            + "Furgon: $60.000 por dia.\n"
                            + "Camion: $120.000.\n"
                            + "Si eres cliente nuevo contaras con un descuento del 10% sobre el valor total de la compra(se excluye el IVA)");
                    break;
                //ARRENDAR VEHICULO
                case 3:
                    operacionesCliente.mostrarClientes(); //muestro los clientes registradoss
                    System.out.println("Ingrese el id del usuario que desea arrendar un vehiculo");
                    int idClienteSeleccionado = sc.nextInt();
                    operacionesVehiculo.arrendarVehiculo(idClienteSeleccionado, operacionesCliente, ouput);
                    break;
                //VER LISTADO DE VEHICULOS
                case 4:
                    System.out.println("Mostrando lista de todos los vehiculos");
                    operacionesVehiculo.listarTodosVehiculos();
                    break;
                //VER ARRIENDOS DE MAS DE UNA SEMANA
                case 5:
                    System.out.println("Mostrando vehiculos arrendados por mas de una semana");
                    operacionesVehiculo.filtrarArriendosPorMasDeUnaSemana();
                    break;
                //GENERAR BOLETA Y SALIR
                case 6:
                    operacionesCliente.mostrarClientes(); //muestro los clientes registradoss
                    System.out.println("Ingrese el id del cliente deseaado para generar boleta");
                    int idCliente = sc.nextInt();
                    operacionesCliente.generarBoleta(idCliente, finanzas, operacionesVehiculo);
                    break;
                case 7:
                    System.out.println("Saliendo del sistema, gracias por preferir\n"
                            + "Rent a Car Brief Drive.");
                    romperBucle = true;
                    break;
                //ELECCION INCORRECTA
                default:
                    System.out.println("Lo sentimos, la opcion ingresada no existe");
            }
        } while (!romperBucle);
        sc.close();//Se cierra el objeto
    }
}
