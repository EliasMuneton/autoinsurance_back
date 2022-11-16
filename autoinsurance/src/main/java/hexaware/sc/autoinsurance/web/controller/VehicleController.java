package hexaware.sc.autoinsurance.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexaware.sc.autoinsurance.services.VehicleServiceImpl;
import hexaware.sc.autoinsurance.web.model.VehicleDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/vehicle")
@SecurityRequirement(name = "token")
public class VehicleController {
    
    private VehicleServiceImpl vehicleService;

    @Autowired
    public void setVehicleService(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id) throws Exception {
        return new ResponseEntity<>( vehicleService.getVehicleById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody @Validated  VehicleDto vehicle) throws Exception {        
        return new ResponseEntity<>( vehicleService.saveNewVehicle(vehicle), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody @Validated VehicleDto vehicle) throws Exception {        
        return new ResponseEntity<>( vehicleService.updateVehicle(vehicle, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws Exception {
        return new ResponseEntity<>( vehicleService.deleteVehicle(id), HttpStatus.OK);
    }


    @PostMapping("/search")
    public ResponseEntity<?> seacrh(@RequestBody  VehicleDto vehicle) throws Exception {        
        return new ResponseEntity<>( vehicleService.searchVehicle(vehicle), HttpStatus.OK);
    }
}
