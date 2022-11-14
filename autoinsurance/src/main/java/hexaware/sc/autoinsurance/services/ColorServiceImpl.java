package hexaware.sc.autoinsurance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hexaware.sc.autoinsurance.repositories.ColorRepository;
import hexaware.sc.autoinsurance.web.mapper.ColorMapper;
import hexaware.sc.autoinsurance.web.model.ColorDto;

@Service
public class ColorServiceImpl implements ColorService {

    private ColorMapper colorMapper;
    private ColorRepository colorRepository;

    @Autowired
    public void setColorMapper(ColorMapper colorMapper) {
        this.colorMapper = colorMapper;
    }

    @Autowired
    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }


    @Override
    public Iterable<ColorDto> getAllColors() {
        return colorMapper.colorsToColorsDtos(colorRepository.findAll());
    }
    
}
