package gm.gym_fit;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gm.gym_fit.modelo.Cliente;
import gm.gym_fit.servicio.IClienteServicio;

@SpringBootApplication
public class GymFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio iClienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(GymFitApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la Aplicacion");
		// Levantar la fabrica de spring
		SpringApplication.run(GymFitApplication.class, args);
		logger.info("Aplicacion finalizada!");
	}

	@Override
	public void run(String... args) throws Exception {
		gymFitApp();
	}

	private void gymFitApp(){
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			var opcion = mostraMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(nl);
		}
	}

	private int mostraMenu(Scanner consola){
		logger.info("""
			\n*** Aplicacion Zona Fit (GYM) ***
			1. Listar Clientes
			2. Buscar Cliente
			3. Agregar Cliente
			4. Modificar Cliente
			5. Eliminar Cliente
			6. Salir
		""");
		System.out.print("Elige una opcion: ");
		return Integer.parseInt(consola.nextLine());
	}
	

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion) {
			case 1: {
				logger.info(nl + "--- Listado de Clientes ---" + nl);
				List<Cliente> clientes = iClienteServicio.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente.toString() + nl));
				break;
			}
			case 2: {
				logger.info(nl + "--- Buscar Cliente por Id ---" + nl);
				logger.info("Id Cliente a buscar: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = iClienteServicio.buscarClientePorId(idCliente);
				if (cliente != null)
					logger.info("Cliente encontrado: " + cliente + nl);
				else
					logger.info("Cliente NO encontrado" + nl);
				break;
			}

			case 3 : {
				logger.info("--- Agregar Cliente ---" + nl);
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("Membresia: ");
				var membresia = Integer.parseInt(consola.nextLine());
				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);
				iClienteServicio.guardarCliente(cliente);
				logger.info("Cliente agregado: " + cliente + nl);
				break;
			}
			case 4 : {
				logger.info("--- Modificar Cliente ---" + nl);
				logger.info("Id Cliente: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = iClienteServicio.buscarClientePorId(idCliente);
				if(cliente != null){
					logger.info("Nombre: " );
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Membresia: ");
					var membresia = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setMembresia(membresia);
					iClienteServicio.guardarCliente(cliente);
					logger.info("Cliente modificado: " + cliente + nl);
				}
				else
					logger.info("cliente NO encontrado: " + cliente + nl);
				break;
			}

			case 5 : {
				logger.info("--- Eliminar Cliente ---" + nl);
				logger.info("Id Cliente: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				var cliente = iClienteServicio.buscarClientePorId(idCliente);
				if(cliente != null){
					iClienteServicio.eliminarCliente(cliente);
					logger.info("Cliente eliminado: " + cliente + nl);
				}
				else
					logger.info("Cliente No encontrado: " + cliente + nl);
				break;
			}
			case 6: {
				logger.info("Saliendo de la aplicación...");
				salir = true;
				break;
			}
			default: {
				logger.info("Opción no válida, por favor elige otra.");
				break;
			}
		}
		return salir; 
	}
	
}
