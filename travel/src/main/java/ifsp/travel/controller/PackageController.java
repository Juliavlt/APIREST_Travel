package ifsp.travel.controller;

import ifsp.travel.model.dto.PackageRequestDTO;
import ifsp.travel.model.dto.PackageResponseDTO;
import ifsp.travel.model.entity.Package;
import ifsp.travel.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class PackageController {

    @Autowired
    PackageService packagesService;

    @PostMapping("/packages")
    @Transactional
    public ResponseEntity<?> createPackage(
            @RequestBody PackageRequestDTO packagesRequestDTO) {

        PackageResponseDTO packages = packagesService.create(packagesRequestDTO);

        if(packages.getError()==null){
            return ResponseEntity.ok(String.valueOf(packages.getId()));
        }
        return ResponseEntity.badRequest().body(packages.getError());
    }

    @GetMapping("/packages")
    @Transactional
    public ResponseEntity<?> getPackage(@RequestParam(value = "id", required = true) Long id) {

        PackageResponseDTO response = packagesService.find(id);
        if (response.getError()==null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response.getError());
    }

    @DeleteMapping("/packages")
    @Transactional
    public ResponseEntity deletePackage(@RequestParam(value = "id", required = true) Long id) {
        PackageResponseDTO packagesResponseDTO = packagesService.delete(id);
        if(packagesResponseDTO.getError()==null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(packagesResponseDTO.getError());
    }

    @GetMapping("/allPackages")
    @Transactional
    public ResponseEntity<List<Package>> getPackages(){
        List<Package> response = packagesService.getAllPackages();
        return ResponseEntity.ok(response);
    }
}
