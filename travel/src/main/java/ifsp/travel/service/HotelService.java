package ifsp.travel.service;

import ifsp.travel.model.Image;
import ifsp.travel.model.entity.Hotel;
import ifsp.travel.model.dto.HotelRequestDTO;
import ifsp.travel.model.dto.HotelResponseDTO;
import ifsp.travel.repository.HotelRepository;
import ifsp.travel.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class HotelService {

    @Autowired private ImageRepository imageRepository;
    @Autowired private HotelRepository repository;

    public HotelResponseDTO create(HotelRequestDTO requestDTO) {

        Hotel hotel = Hotel.builder()
                .name(requestDTO.getName())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .location(requestDTO.getLocation())
                .images(getImages(requestDTO.getImages()))
                .dailyPrice(requestDTO.getDailyPrice())
                .rate(0.0)
                .build();

        repository.save(hotel);

        return HotelResponseDTO.builder().build();
    }

    public HotelResponseDTO find(Long id) {

        Hotel hotel = repository.findById(id).get();

        return HotelResponseDTO.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .departureDate(hotel.getDepartureDate())
                .returnDate(hotel.getReturnDate())
                .location(hotel.getLocation())
                .dailyPrice(hotel.getDailyPrice())
                .images(getImagesStringToObject(hotel.getImages()))
                .rate(hotel.getRate())
                .build();
    }

    public HotelResponseDTO update(Long id, HotelRequestDTO requestDTO) {
        Hotel hotel = repository.findById(id).get();
        if (hotel ==null){
            return HotelResponseDTO.builder().error("Hotel não existe.").build();
        }

        repository.save(Hotel.builder()
                .name(requestDTO.getName())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .location(requestDTO.getLocation())
                .images(getImages(requestDTO.getImages()))
                .dailyPrice(requestDTO.getDailyPrice())
                .build());

        return HotelResponseDTO.builder()
                .name(requestDTO.getName())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .location(requestDTO.getLocation())
                .images(requestDTO.getImages())
                .dailyPrice(requestDTO.getDailyPrice())
                .rate(requestDTO.getRate())
                .build();
    }

    public HotelResponseDTO delete(Long id) {
        Hotel hotel = repository.findById(id).get();
        if (!ObjectUtils.isEmpty(hotel)) {
            repository.delete(hotel);
            return HotelResponseDTO.builder().build();
        }
        return HotelResponseDTO.builder().error("Hotel não existe.").build();
    }


    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    public void rate(HotelRequestDTO request) {
        Hotel hotel = repository.findById(request.getId()).get();
        Double updatedRate = (hotel.getRate() + request.getRate()) /  2 ;
        repository.save(Hotel.builder()
                        .id(request.getId())
                        .name(hotel.getName())
                        .departureDate(hotel.getDepartureDate())
                        .returnDate(hotel.getReturnDate())
                        .location(hotel.getLocation())
                        .images(hotel.getImages())
                        .rate(updatedRate)
                        .dailyPrice(hotel.getDailyPrice())
                        .build());
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