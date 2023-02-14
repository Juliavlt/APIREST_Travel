package ifsp.travel.service;

import ifsp.travel.model.entity.Hotel;
import ifsp.travel.model.dto.HotelRequestDTO;
import ifsp.travel.model.dto.HotelResponseDTO;
import ifsp.travel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository repository;

    public HotelResponseDTO create(HotelRequestDTO requestDTO) {

        Hotel hotel = Hotel.builder()
                .name(requestDTO.getName())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .location(requestDTO.getLocation())
                .dailyPrice(requestDTO.getDailyPrice())
                .rate(requestDTO.getRate())
                .build();

        repository.save(hotel);

        return HotelResponseDTO.builder().build();
    }

    public HotelResponseDTO find(HotelRequestDTO requestDTO) {

        Hotel hotel = repository.findById(requestDTO.getId()).get();

        return HotelResponseDTO.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .departureDate(hotel.getDepartureDate())
                .returnDate(hotel.getReturnDate())
                .location(hotel.getLocation())
                .dailyPrice(hotel.getDailyPrice())
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
                .dailyPrice(requestDTO.getDailyPrice())
                .rate(requestDTO.getRate())
                .build());

        return HotelResponseDTO.builder().build();
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
}