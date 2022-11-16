package hexaware.sc.autoinsurance.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hexaware.sc.autoinsurance.domain.Color;
import hexaware.sc.autoinsurance.domain.Token;
import hexaware.sc.autoinsurance.repositories.ColorRepository;
import hexaware.sc.autoinsurance.security.JWTUtil;
import hexaware.sc.autoinsurance.web.mapper.ColorMapper;
import hexaware.sc.autoinsurance.web.model.ColorDto;

@Service
public class ColorServiceImpl implements ColorService {

    private ColorMapper colorMapper;
    private ColorRepository colorRepository;
    private JWTUtil jwtUtil;

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

    @Autowired
    public void setTokenUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    
    @Override
    public ColorDto saveNewColor(ColorDto colorDto) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        if (tokenData.getUserRolId() != 1) { // Valid just for ADMIN
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have grant access");
        }
        Color color = colorMapper.colorDtoToColor(colorDto);
        color.setCreatedAt(new Date());
        color.setCreatedBy(tokenData.getUserId());
        return colorMapper.colorToColorDto(colorRepository.save(color));
    }

    @Override
    public ColorDto updateColor(ColorDto colorDto, long id) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        if (tokenData.getUserRolId() != 1) { // Valid just for ADMIN
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have grant access");
        }
        Optional<Color> colorExist =  colorRepository.findById(id);
        if (!colorExist.isPresent()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "ID " + id + " not found");
        Color color = colorExist.get();
        color.setColorName(colorDto.getColorName());
        color.setUpdatedAt(new Date());
        color.setUpdatedBy(tokenData.getUserId());
        return colorMapper.colorToColorDto(colorRepository.save(color));
    }
    
}
