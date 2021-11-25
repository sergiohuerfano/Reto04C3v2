package iSergio.Reto04C3.repository;

import iSergio.Reto04C3.model.Cliente;
import iSergio.Reto04C3.model.CountClient;
import iSergio.Reto04C3.model.Reservacion;
import iSergio.Reto04C3.repository.crud.ReservacionCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservacionRepository {

    @Autowired
    private ReservacionCrudRepository reservacionCrudRepository;

    public List<Reservacion> getAll(){return (List<Reservacion>) reservacionCrudRepository.findAll();}

    public Optional<Reservacion> getReserva(int id){return reservacionCrudRepository.findById(id);}

    public Reservacion save(Reservacion msj){return reservacionCrudRepository.save(msj);}

    public void delete(Reservacion reservacion){
        reservacionCrudRepository.delete(reservacion);
    }

    public List<Reservacion> getReservacionesByDescription (String description){
        return reservacionCrudRepository.findAllByDescription(description);
    }

    public List<Reservacion> getReservacionesPeriodo(Date dateOne, Date dateTwo){
        return reservacionCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }

    public List<CountClient> getTopClientes(){
        List<CountClient> resultado = new ArrayList<>();
        List<Object[]> report = reservacionCrudRepository.countTotalReservacionByCliente();
        for(int i=0; i<report.size(); i++){
            resultado.add(new CountClient((Long) report.get(i)[1],(Cliente) report.get(i)[0]));
        }
        return resultado;
    }
}
