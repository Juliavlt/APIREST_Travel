package ifsp.travel.controller;
import ifsp.travel.model.entity.Flight;
import ifsp.travel.model.dto.FlightRequestDTO;
import ifsp.travel.model.dto.FlightResponseDTO;
import ifsp.travel.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class FlightController {

    @Autowired
    FlightService flightService;

    @PostMapping("/flight")
    @Transactional
    public ResponseEntity<?> createFlight(
            @RequestBody FlightRequestDTO flightRequestDTO) {

        FlightResponseDTO flight = flightService.create(flightRequestDTO);

        if(flight.getError()==null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(flight.getError());
    }

    @GetMapping("/flight")
    @Transactional
    public ResponseEntity<?> getFlight(@RequestParam(value = "id", required = true) Long id) {

        FlightResponseDTO response = flightService.get(id);
        if (response.getError()==null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response.getError());
    }

    @DeleteMapping("/flight")
    @Transactional
    public ResponseEntity deleteFlight(@RequestParam(value = "id", required = true) Long id) {
        FlightResponseDTO flightResponseDTO = flightService.delete(id);
        if(flightResponseDTO.getError()==null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(flightResponseDTO.getError());
    }

    @GetMapping("/allFlights")
    @Transactional
    public ResponseEntity<List<Flight>> getFlights(){
        List<Flight> response = flightService.getAllFlights();
        return ResponseEntity.ok(response);
    }
}
