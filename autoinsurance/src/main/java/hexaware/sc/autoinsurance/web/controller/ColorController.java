package hexaware.sc.autoinsurance.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexaware.sc.autoinsurance.services.ColorServiceImpl;

@RestController
@RequestMapping("/api/v1/color")
public class ColorController {
    
    private ColorServiceImpl colorService;

    @Autowired
    public void setColorService(ColorServiceImpl colorService) {
        this.colorService = colorService;
    }
    

    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        return new ResponseEntity<>( colorService.getAllColors(), HttpStatus.OK);
    }
}
