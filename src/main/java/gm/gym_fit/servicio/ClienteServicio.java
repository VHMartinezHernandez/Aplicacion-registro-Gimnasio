package gm.gym_fit.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gm.gym_fit.modelo.Cliente;
import gm.gym_fit.repositorio.IClienteRepositorio;

@Service

public class ClienteServicio implements IClienteServicio {

    @Autowired
    private IClienteRepositorio iClienteRepositorio;

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = iClienteRepositorio.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClientePorId(Integer idCliente) {
        Cliente cliente = iClienteRepositorio.findById(idCliente).orElse(null);
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
       iClienteRepositorio.save(cliente);
        
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        iClienteRepositorio.delete(cliente);
        
    }   

}
