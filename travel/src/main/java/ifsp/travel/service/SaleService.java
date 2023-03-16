package ifsp.travel.service;

import ifsp.travel.model.Image;
import ifsp.travel.model.dto.PackageRequestDTO;
import ifsp.travel.model.dto.PackageResponseDTO;
import ifsp.travel.model.dto.SaleRequestDTO;
import ifsp.travel.model.dto.SaleResponseDTO;
import ifsp.travel.model.entity.Flight;
import ifsp.travel.model.entity.Hotel;
import ifsp.travel.model.entity.Package;
import ifsp.travel.model.entity.User;
import ifsp.travel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    @Autowired private PackageRepository packageRepository;
    @Autowired private HotelRepository hotelRepository;
    @Autowired private FlightRepository flightRepository;
    @Autowired private UserRepository userRepository;

    public SaleResponseDTO sale(SaleRequestDTO requestDTO) {
        Hotel hotel = requestDTO.getIdHotel() != null ? hotelRepository.findById(requestDTO.getIdHotel()).get() : null;
        Flight flight = requestDTO.getIdFlight() != null ? flightRepository.findById(requestDTO.getIdFlight()).get() : null;
        Package pack = requestDTO.getIdPackage() != null ? packageRepository.findById(requestDTO.getIdPackage()).get() : null;
        User user = requestDTO.getIdUser() != null ? userRepository.findById(requestDTO.getIdUser()).get() : null;

        if ( hotel != null) {
            Integer availableRooms = hotel.getAvailableRooms();
            if (requestDTO.getPersons()<=availableRooms){
                availableRooms -= requestDTO.getPersons();
                hotel.setAvailableRooms(availableRooms);
                hotelRepository.save(hotel);
                List<Hotel> hotels = user.getHotels();
                hotels.add(hotel);
                user.setHotels(hotels);
                userRepository.save(user);
            } else {
                return SaleResponseDTO.builder().error("Vagas indisponíveis").build();
            }
        } else if (flight != null){
            Integer availableSeats = flight.getAvailableSeats();
            if (requestDTO.getPersons()<=availableSeats){
                availableSeats -= requestDTO.getPersons();
                flight.setAvailableSeats(availableSeats);
                flightRepository.save(flight);
                List<Flight> flights = user.getFlights();
                flights.add(flight);
                user.setFlights(flights);
                userRepository.save(user);
            } else {
                return SaleResponseDTO.builder().error("Vagas indisponíveis").build();
            }
        } else if (pack != null){
            Integer availableRooms = pack.getHotel().getAvailableRooms();
            Integer availableSeats = pack.getFlight().getAvailableSeats();
            if (availableRooms!=0 || availableSeats!=0){
                pack.setAvailable(1);
                Hotel hotelRooms = pack.getHotel();
                Flight flightSeats = pack.getFlight();
                hotelRooms.setAvailableRooms(hotelRooms.getAvailableRooms() - requestDTO.getPersons());
                flightSeats.setAvailableSeats(flightSeats.getAvailableSeats() - requestDTO.getPersons());
                hotelRepository.save(hotelRooms);
                flightRepository.save(flightSeats);
                packageRepository.save(pack);
                List<Package> packages = user.getPackages();
                packages.add(pack);
                user.setPackages(packages);
                userRepository.save(user);
            } else {
                pack.setAvailable(0);
                packageRepository.save(pack);
                return SaleResponseDTO.builder().error("Vagas indisponíveis").build();
            }
        }

        return SaleResponseDTO.builder().response("Venda realizada com sucesso").build();
    }
}