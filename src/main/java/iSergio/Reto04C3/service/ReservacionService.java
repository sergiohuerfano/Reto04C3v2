package iSergio.Reto04C3.service;

import iSergio.Reto04C3.model.DescriptionAmount;
import iSergio.Reto04C3.model.Reservacion;
import iSergio.Reto04C3.model.CountClient;
import iSergio.Reto04C3.repository.ReservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservacionService {

    @Autowired
    private ReservacionRepository reservacionRepository;

    public List<Reservacion> getAll(){return reservacionRepository.getAll();}

    public Optional<Reservacion> getReserva(int id){return reservacionRepository.getReserva(id);}

    public Reservacion save(Reservacion reserva){
        if(reserva.getIdReservation()==null){
            return reservacionRepository.save(reserva);
        }else {
            Optional<Reservacion> reservaAux=reservacionRepository.getReserva(reserva.getIdReservation());
            if(reservaAux.isEmpty()){
                return reservacionRepository.save(reserva);
            }else{
                return reserva;
            }
        }
    }

    public Reservacion update(Reservacion reserva){
        if(reserva.getIdReservation()!=null){
            Optional<Reservacion> reservaAux=reservacionRepository.getReserva(reserva.getIdReservation());
            if(!reservaAux.isEmpty()){
                if(reserva.getStartDate()!=null){
                    reservaAux.get().setStartDate(reserva.getStartDate());
                }
                if(reserva.getDevolutionDate()!=null){
                    reservaAux.get().setDevolutionDate(reserva.getDevolutionDate());
                }
                if(reserva.getStatus()!=null){
                    reservaAux.get().setStatus(reserva.getStatus());
                }
                return reservacionRepository.save(reservaAux.get());
            }
        }
        return reserva;
    }

    public boolean deleteReserva(int id){
        Optional<Reservacion> reserAux=getReserva(id);
        if(!reserAux.isEmpty()){
            reservacionRepository.delete(reserAux.get());
            return true;
        }
        return false;
    }

    public List<CountClient> getTopClientes(){
        return reservacionRepository.getTopClientes();
    }

    public DescriptionAmount getStatusReport(){
        List<Reservacion> completed = reservacionRepository.getReservacionesByDescription("completed");
        List<Reservacion> cancelled = reservacionRepository.getReservacionesByDescription("cancelled");

        return new DescriptionAmount(completed.size(),cancelled.size());
    }

    public List<Reservacion> getReservacionesPeriod(String data1, String data2){

        //yyyy-mm-dd
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne = new Date();
        Date dateTwo = new Date();

        try{
            dateOne = parser.parse(data1);
            dateTwo = parser.parse(data2);
        }catch (ParseException exp) {
            exp.printStackTrace();
        }
        if(dateOne.before(dateTwo)) {
            return reservacionRepository.getReservacionesPeriodo(dateOne, dateTwo);
        }else {
            return new ArrayList<>();
        }
    }
}
