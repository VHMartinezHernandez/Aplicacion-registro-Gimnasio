package gm.gym_fit.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import gm.gym_fit.modelo.Cliente;

public interface IClienteRepositorio extends JpaRepository<Cliente, Integer>{

}
