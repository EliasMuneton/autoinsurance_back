package hexaware.sc.autoinsurance.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexaware.sc.autoinsurance.services.ModelServiceImpl;

@RestController
@RequestMapping("/api/v1/model")
public class ModelController {
    
    private ModelServiceImpl modelService;

    @Autowired
    public void setModelService(ModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        return new ResponseEntity<>( modelService.getAllModels(), HttpStatus.OK);
    }

    
    @GetMapping("/brand/{idBrand}")
    public ResponseEntity<?> get(@PathVariable long idBrand) throws Exception {
        return new ResponseEntity<>( modelService.modelsByBrand(idBrand), HttpStatus.OK);
    }
}
