package iSergio.Reto04C3.repository.crud;

import iSergio.Reto04C3.model.Reservacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservacionCrudRepository extends CrudRepository<Reservacion, Integer> {

    @Query ("SELECT c.client, COUNT(c.client) from Reservation AS c group by c.client order by COUNT(c.client)DESC")
    public List<Object[]> countTotalReservacionByCliente();

    public List<Reservacion> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    public List<Reservacion> findAllByDescription(String description);

}
