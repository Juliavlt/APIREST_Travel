package ifsp.travel.service;

import ifsp.travel.model.dto.*;
import ifsp.travel.model.entity.*;
import ifsp.travel.model.entity.Package;
import ifsp.travel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

                User vendedor = userRepository.findById(hotel.getIdUser()).get();
                List<Message> messages = vendedor.getMessages();
                Message message = Message.builder().message("Venda do Hotel " + hotel.getName() + " realizada com sucesso!").build();
                messages.add(message);
                vendedor.setMessages(messages);
                userRepository.save(vendedor);

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

                User vendedor = userRepository.findById(flight.getIdUser()).get();
                List<Message> messages = vendedor.getMessages();
                Message message = Message.builder().message("Venda do Voo " + flight.getName() + " realizada com sucesso!").build();
                messages.add(message);
                vendedor.setMessages(messages);
                userRepository.save(vendedor);

            } else {
                return SaleResponseDTO.builder().error("Vagas indisponíveis").build();
            }
        } else if (pack != null){
            Integer availableRooms = pack.getHotel().getAvailableRooms();
            Integer availableSeats = pack.getFlight().getAvailableSeats();
            if (availableRooms!=0 && availableSeats!=0){
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

                User vendedorFlight = userRepository.findById(pack.getFlight().getIdUser()).get();
                User vendedorHotel = userRepository.findById(pack.getHotel().getIdUser()).get();


                List<Message> messagesFlight = vendedorFlight.getMessages();
                Message message1 = Message.builder().message("Venda do Pacote " + pack.getTitle() + ", Voo " + pack.getFlight().getName() +", realizada com sucesso!").build();
                messagesFlight.add(message1);
                vendedorFlight.setMessages(messagesFlight);
                userRepository.save(vendedorFlight);

                List<Message> messagesHotel = vendedorHotel.getMessages();
                Message message2 = Message.builder().message("Venda do Pacote " + pack.getTitle() + ", Hotel " +  pack.getHotel().getName() + ", realizada com sucesso!").build();
                messagesHotel.add(message2);
                vendedorHotel.setMessages(messagesHotel);
                userRepository.save(vendedorHotel);

            } else {
                pack.setAvailable(0);
                packageRepository.save(pack);
                return SaleResponseDTO.builder().error("Vagas indisponíveis").build();
            }
        }

        return SaleResponseDTO.builder().response("Venda realizada com sucesso").build();
    }

    public void rate(RateRequestDTO request) {
        Hotel hotel = request.getIdHotel() != null ? hotelRepository.findById(request.getIdHotel()).get() : null;

        Integer avaliations = hotel.getQtdAvaliations();

        Integer updatedRate = 0;
        if (avaliations == 0) updatedRate = request.getRate();
        else  updatedRate = (hotel.getTotalAvaliations() + request.getRate()) /  (hotel.getQtdAvaliations() + 1);

        hotel.setQtdAvaliations(avaliations+1);
        hotel.setRate(updatedRate);
        hotel.setTotalAvaliations(hotel.getTotalAvaliations()+ request.getRate());
        hotel.setRate(updatedRate);
        hotelRepository.save(hotel);
    }

}