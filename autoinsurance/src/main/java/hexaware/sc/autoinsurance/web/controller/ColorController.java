package hexaware.sc.autoinsurance.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexaware.sc.autoinsurance.services.ColorServiceImpl;
import hexaware.sc.autoinsurance.web.model.ColorDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/color")
@SecurityRequirement(name = "token")
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

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody @Validated  ColorDto color) throws Exception {        
        return new ResponseEntity<>( colorService.saveNewColor(color), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody @Validated ColorDto color) throws Exception {        
        return new ResponseEntity<>( colorService.updateColor(color, id), HttpStatus.OK);
    }
}
