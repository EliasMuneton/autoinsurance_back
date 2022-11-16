package hexaware.sc.autoinsurance.services;

import hexaware.sc.autoinsurance.web.model.ColorDto;

public interface ColorService {
    Iterable<ColorDto> getAllColors();

    ColorDto saveNewColor(ColorDto colorDto) throws Exception;

    ColorDto updateColor(ColorDto colorDto, long id) throws Exception;
}
