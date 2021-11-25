package iSergio.Reto04C3.repository.crud;

import iSergio.Reto04C3.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Integer> {

}
