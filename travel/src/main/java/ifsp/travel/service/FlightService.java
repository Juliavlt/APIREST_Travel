package ifsp.travel.service;

import ifsp.travel.model.AdditionalInfo;
import ifsp.travel.model.Image;
import ifsp.travel.model.entity.Flight;
import ifsp.travel.model.dto.FlightRequestDTO;
import ifsp.travel.model.dto.FlightResponseDTO;
import ifsp.travel.model.entity.User;
import ifsp.travel.repository.AdditionalInfoRepository;
import ifsp.travel.repository.ImageRepository;
import ifsp.travel.repository.FlightRepository;
import ifsp.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired private FlightRepository repository;
    @Autowired private UserRepository userRepository;
    @Autowired private ImageRepository imageRepository;
    @Autowired private AdditionalInfoRepository additionalInfoRepository;

    public FlightResponseDTO create(FlightRequestDTO requestDTO) {

        Flight flight = Flight.builder()
                .name(requestDTO.getName())
                .idUser(requestDTO.getIdUser())
                .images(getImages(requestDTO.getImages()))
                .airline(requestDTO.getAirline())
                .destiny(requestDTO.getDestiny())
                .origin(requestDTO.getOrigin())
                .favored(false)
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .price(requestDTO.getPrice())
                .availableSeats(requestDTO.getAvailableSeats())
                .classType(requestDTO.getClassType())
                .additional(getAdditionalInfo(requestDTO.getAdditional()))
                .build();

        repository.save(flight);

        User user  = userRepository.findById(requestDTO.getIdUser()).orElse(null);
        if (user!=null){
            List<Flight> flights = user.getFlights();
            flights.add(flight);
            user.setFlights(flights);
            userRepository.save(user);
        }

        return FlightResponseDTO.builder().build();
    }

    public FlightResponseDTO get(Long id) {

        Flight flight = repository.findById(id).get();

        return FlightResponseDTO.builder()
                .id(flight.getId())
                .name(flight.getName())
                .airline(flight.getAirline())
                .destiny(flight.getDestiny())
                .origin(flight.getOrigin())
                .favored(flight.getFavored())
                .departureDate(flight.getDepartureDate())
                .returnDate(flight.getReturnDate())
                .price(flight.getPrice())
                .availableSeats(flight.getAvailableSeats())
                .classType(flight.getClassType())
                .additional(getAdditionalInfo(flight.getAdditional()))
                .images(getImagesStringToObject(flight.getImages()))
                .build();
    }

    public FlightResponseDTO update(FlightRequestDTO requestDTO) {
        Flight flight = repository.findById(requestDTO.getId()).get();
        if (flight ==null){
            return FlightResponseDTO.builder().error("Voo não existe.").build();
        }

        repository.save(Flight.builder()
                .id(flight.getId())
                .idUser(flight.getIdUser())
                .name(requestDTO.getName())
                .airline(requestDTO.getAirline())
                .destiny(requestDTO.getDestiny())
                .origin(requestDTO.getOrigin())
                .favored(requestDTO.getFavored())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .price(requestDTO.getPrice())
                .availableSeats(requestDTO.getAvailableSeats())
                .classType(requestDTO.getClassType())
                .additional(requestDTO.getAdditional())
                .images(getImages(requestDTO.getImages()))
                .build());

        return FlightResponseDTO.builder()
                .id(flight.getId())
                .name(requestDTO.getName())
                .airline(requestDTO.getAirline())
                .destiny(requestDTO.getDestiny())
                .origin(requestDTO.getOrigin())
                .departureDate(requestDTO.getDepartureDate())
                .favored(requestDTO.getFavored())
                .returnDate(requestDTO.getReturnDate())
                .price(requestDTO.getPrice())
                .availableSeats(requestDTO.getAvailableSeats())
                .classType(requestDTO.getClassType())
                .additional(requestDTO.getAdditional())
                .images(requestDTO.getImages())
                .build();
    }

    public FlightResponseDTO delete(Long id) {
        Flight flight = repository.findById(id).get();
        if (!ObjectUtils.isEmpty(flight)) {
            repository.delete(flight);
            return FlightResponseDTO.builder().build();
        }
        return FlightResponseDTO.builder().error("Voo não existe.").build();
    }

    public List<Flight> getAllFlights() {
        return repository.findAll();
    }

    public List<Image> getImages(List<String> list) {
        List<Image> images = new ArrayList<>();
        if (list != null){
            for (int i = 0; i < list.size(); i++) {
                Image image = Image.builder().image(list.get(i)).build();
                imageRepository.save(image);
                images.add(image);
            }
        }
        return images;
    }

    public static List<String> getImagesStringToObject(List<Image> list) {
        List<String> images = new ArrayList<>();
        if (list != null){
            for (int i = 0; i < list.size(); i++) {
                images.add(list.get(i).getImage());
            }
        }
        return images;
    }

    public List<AdditionalInfo> getAdditionalInfo(List<AdditionalInfo> list) {
        List<AdditionalInfo> additionalInfo = new ArrayList<>();
        if (list != null){
            for (int i = 0; i < list.size(); i++) {
                AdditionalInfo info = AdditionalInfo.builder()
                        .title(list.get(i).getTitle())
                        .information(list.get(i).getInformation())
                        .build();
                additionalInfoRepository.save(info);
                additionalInfo.add(info);
            }
        }
        return additionalInfo;
    }

    public static List<String> getAdditionalInfoStringToObject(List<Image> list) {
        List<String> images = new ArrayList<>();
        if (list != null){
            for (int i = 0; i < list.size(); i++) {
                images.add(list.get(i).getImage());
            }
        }
        return images;
    }

}