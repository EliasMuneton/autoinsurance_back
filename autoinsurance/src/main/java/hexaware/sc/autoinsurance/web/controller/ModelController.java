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

import hexaware.sc.autoinsurance.services.ModelServiceImpl;
import hexaware.sc.autoinsurance.web.model.ModelDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/model")
@SecurityRequirement(name = "token")
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

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody @Validated  ModelDto model) throws Exception {        
        return new ResponseEntity<>( modelService.saveNewModel(model), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody @Validated ModelDto model) throws Exception {        
        return new ResponseEntity<>( modelService.updateModel(model, id), HttpStatus.OK);
    }
}
