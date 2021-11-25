package iSergio.Reto04C3.controller;

import iSergio.Reto04C3.model.CountClient;
import iSergio.Reto04C3.model.DescriptionAmount;
import iSergio.Reto04C3.model.Reservacion;
import iSergio.Reto04C3.service.ReservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/Reservation")
public class ReservacionController {

    @Autowired
    private ReservacionService reservacionService;

    @GetMapping("/all")
    public List<Reservacion> getReservas(){
        return reservacionService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservacion> getReserva(@PathVariable("id") int id){
        return reservacionService.getReserva(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservacion save(@RequestBody Reservacion reserva){
        return reservacionService.save(reserva);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservacion update(@RequestBody Reservacion reserva) {return reservacionService.update(reserva);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){ return reservacionService.deleteReserva(id);}

    @GetMapping("/report-status")
    public DescriptionAmount getReservasDescriptionStatus(){
        return reservacionService.getStatusReport();
    }

    @GetMapping("/report-clients")
    public List<CountClient> getCountClientes(){
        return reservacionService.getTopClientes();
    }

    @GetMapping("report-dates/{dateOne}/{dateTwo}")
    public List<Reservacion> getDatesReport(@PathVariable("dateOne") String data1, @PathVariable("dateTwo")String data2){
        return reservacionService.getReservacionesPeriod(data1, data2);
    }

}
