package ifsp.travel.service;

import ifsp.travel.model.Image;
import ifsp.travel.model.dto.PackageRequestDTO;
import ifsp.travel.model.dto.PackageResponseDTO;
import ifsp.travel.model.entity.Flight;
import ifsp.travel.model.entity.Hotel;
import ifsp.travel.model.entity.Package;
import ifsp.travel.repository.FlightRepository;
import ifsp.travel.repository.HotelRepository;
import ifsp.travel.repository.ImageRepository;
import ifsp.travel.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService {

    @Autowired private PackageRepository repository;
    @Autowired private HotelRepository hotelRepository;
    @Autowired private FlightRepository flightRepository;
    @Autowired private ImageRepository imageRepository;

    public PackageResponseDTO create(PackageRequestDTO requestDTO) {

        Hotel hotel = hotelRepository.findById(requestDTO.getId()).orElse(null);
        Flight flight = flightRepository.findById(requestDTO.getId()).orElse(null);

        Package pack = Package.builder()
                .mainImage(requestDTO.getMainImage())
                .images(requestDTO.getImages())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .origin(requestDTO.getOrigin())
                .destiny(requestDTO.getDestiny())
                .title(requestDTO.getTitle())
                .price(requestDTO.getPrice())
                .hotel(hotel)
                .flight(flight)
                .build();

        repository.save(pack);

        return PackageResponseDTO.builder().build();
    }

    public PackageResponseDTO find(Long id) {

        Package pack = repository.findById(id).get();

        return PackageResponseDTO.builder()
                .id(pack.getId())
                .mainImage(pack.getMainImage())
                .images(pack.getImages())
                .departureDate(pack.getDepartureDate())
                .returnDate(pack.getReturnDate())
                .origin(pack.getOrigin())
                .destiny(pack.getDestiny())
                .title(pack.getTitle())
                .price(pack.getPrice())
                .hotel(pack.getHotel())
                .flight(pack.getFlight())
                .build();
    }

    public PackageResponseDTO update(Long id, PackageRequestDTO requestDTO) {
        Package pack = repository.findById(id).get();
        if (pack ==null){
            return PackageResponseDTO.builder().error("Package não existe.").build();
        }

        Hotel hotel = hotelRepository.findById(requestDTO.getId()).orElse(null);
        Flight flight = flightRepository.findById(requestDTO.getId()).orElse(null);

        repository.save(Package.builder()
                .id(pack.getId())
                .mainImage(requestDTO.getMainImage())
                .images(requestDTO.getImages())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .origin(requestDTO.getOrigin())
                .destiny(requestDTO.getDestiny())
                .title(requestDTO.getTitle())
                .price(requestDTO.getPrice())
                .hotel(hotel)
                .flight(flight)
                .build());

        return PackageResponseDTO.builder()
                .id(pack.getId())
                .mainImage(requestDTO.getMainImage())
                .images(requestDTO.getImages())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .origin(requestDTO.getOrigin())
                .destiny(requestDTO.getDestiny())
                .title(requestDTO.getTitle())
                .price(requestDTO.getPrice())
                .hotel(hotel)
                .flight(flight)
                .build();
    }

    public PackageResponseDTO delete(Long id) {
        Package pack = repository.findById(id).get();
        if (!ObjectUtils.isEmpty(pack)) {
            repository.delete(pack);
            return PackageResponseDTO.builder().build();
        }
        return PackageResponseDTO.builder().error("Pacote não existe.").build();
    }


    public List<Package> getAllPackages() {
        return repository.findAll();
    }

    public List<Image> getImages(List<String> list) {
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Image image = Image.builder().image(list.get(i)).build();
            images.add(image);
            imageRepository.save(image);
        }
        return images;
    }

    public static List<String> getImagesStringToObject(List<Image> list) {
        List<String> images = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            images.add(list.get(i).getImage());
        }
        return images;
    }

}