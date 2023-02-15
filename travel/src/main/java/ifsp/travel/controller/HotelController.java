package ifsp.travel.controller;

import ifsp.travel.model.entity.Hotel;
import ifsp.travel.model.dto.HotelRequestDTO;
import ifsp.travel.model.dto.HotelResponseDTO;
import ifsp.travel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/hotel")
    @Transactional
    public ResponseEntity<?> createHotel(
            @RequestBody HotelRequestDTO request) {

        HotelResponseDTO hotel = hotelService.create(request);

        if(hotel.getError()==null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(hotel.getError());
    }

    @GetMapping("/hotel")
    @Transactional
    public ResponseEntity<?> getHotel(@RequestParam(value = "id", required = true) Long id) {

        HotelResponseDTO response = hotelService.find(id);
        if (response.getError()==null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response.getError());
    }

    @DeleteMapping("/hotel")
    @Transactional
    public ResponseEntity deleteHotel(@RequestParam(value = "id", required = true) Long id) {
        HotelResponseDTO hotelResponseDTO = hotelService.delete(id);
        if(hotelResponseDTO.getError()==null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(hotelResponseDTO.getError());
    }

    @GetMapping("/allHotels")
    @Transactional
    public ResponseEntity<List<Hotel>> getHotels(){
        List<Hotel> response = hotelService.getAllHotels();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/hotel/rate")
    @Transactional
    public ResponseEntity<?> rateHotel(
            @RequestBody HotelRequestDTO request) {

        hotelService.rate(request);
        return ResponseEntity.ok().build();
    }
}
