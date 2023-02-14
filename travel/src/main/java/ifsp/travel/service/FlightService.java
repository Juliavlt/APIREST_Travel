package ifsp.travel.service;

import ifsp.travel.model.entity.Flight;
import ifsp.travel.model.dto.FlightRequestDTO;
import ifsp.travel.model.dto.FlightResponseDTO;
import ifsp.travel.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository repository;

    public FlightResponseDTO create(FlightRequestDTO requestDTO) {

        Flight flight = Flight.builder()
                .name(requestDTO.getName())
                .airline(requestDTO.getAirline())
                .destiny(requestDTO.getDestiny())
                .origin(requestDTO.getOrigin())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .price(requestDTO.getPrice())
                .vacancies(requestDTO.getVacancies())
                .classType(requestDTO.getClassType())
                .build();

        repository.save(flight);

        return FlightResponseDTO.builder().build();
    }

    public FlightResponseDTO get(FlightRequestDTO requestDTO) {

        Flight flight = repository.findById(requestDTO.getId()).get();

        repository.save(flight);

        return FlightResponseDTO.builder()
                .id(flight.getId())
                .name(flight.getName())
                .airline(flight.getAirline())
                .destiny(flight.getDestiny())
                .origin(flight.getOrigin())
                .departureDate(flight.getDepartureDate())
                .returnDate(flight.getReturnDate())
                .price(flight.getPrice())
                .vacancies(flight.getVacancies())
                .classType(flight.getClassType())
                .build();
    }

    public FlightResponseDTO update(Long id, FlightRequestDTO requestDTO) {
        Flight flight = repository.findById(id).get();
        if (flight ==null){
            return FlightResponseDTO.builder().error("Voo não existe.").build();
        }

        repository.save(Flight.builder()
                .id(id)
                .name(requestDTO.getName())
                .airline(requestDTO.getAirline())
                .destiny(requestDTO.getDestiny())
                .origin(requestDTO.getOrigin())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .price(requestDTO.getPrice())
                .vacancies(requestDTO.getVacancies())
                .classType(requestDTO.getClassType())
                .build());

        return FlightResponseDTO.builder().build();
    }

    public FlightResponseDTO delete(String flightname) {
        Flight flight = repository.findByName(flightname);
        if (!ObjectUtils.isEmpty(flight)) {
            repository.delete(flight);
            return FlightResponseDTO.builder().build();
        }
        return FlightResponseDTO.builder().error("Voo não existe.").build();
    }

    public List<Flight> getAllFlights() {
        return repository.findAll();
    }

}