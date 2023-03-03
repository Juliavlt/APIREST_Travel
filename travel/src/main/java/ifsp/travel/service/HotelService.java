package ifsp.travel.service;

import ifsp.travel.model.AdditionalInfo;
import ifsp.travel.model.Image;
import ifsp.travel.model.entity.Hotel;
import ifsp.travel.model.dto.HotelRequestDTO;
import ifsp.travel.model.dto.HotelResponseDTO;
import ifsp.travel.repository.AdditionalInfoRepository;
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
    @Autowired private AdditionalInfoRepository additionalInfoRepository;

    public HotelResponseDTO create(HotelRequestDTO requestDTO) {

        Hotel hotel = Hotel.builder()
                .name(requestDTO.getName())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .location(requestDTO.getLocation())
                .images(getImages(requestDTO.getImages()))
                .dailyPrice(requestDTO.getDailyPrice())
                .additional(getAdditionalInfo(requestDTO.getAdditional()))
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
                .additional(getAdditionalInfo(hotel.getAdditional()))
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
                .additional(getAdditionalInfo(requestDTO.getAdditional()))
                .dailyPrice(requestDTO.getDailyPrice())
                .build());

        return HotelResponseDTO.builder()
                .name(requestDTO.getName())
                .departureDate(requestDTO.getDepartureDate())
                .returnDate(requestDTO.getReturnDate())
                .location(requestDTO.getLocation())
                .images(requestDTO.getImages())
                .dailyPrice(requestDTO.getDailyPrice())
                .additional(getAdditionalInfo(requestDTO.getAdditional()))
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
                .additional(getAdditionalInfo(hotel.getAdditional()))
                .dailyPrice(hotel.getDailyPrice())
                        .build());
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