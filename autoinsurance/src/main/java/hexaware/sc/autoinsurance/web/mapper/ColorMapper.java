package hexaware.sc.autoinsurance.web.mapper;

import org.mapstruct.Mapper;

import hexaware.sc.autoinsurance.domain.Color;
import hexaware.sc.autoinsurance.web.model.ColorDto;

@Mapper(
    componentModel = "spring"
)
public interface ColorMapper {
    
    ColorDto colorToColorDto(Color color);

    Color colorDtoToColor(ColorDto colorDto);

    Iterable<ColorDto> colorsToColorsDtos(Iterable<Color> colors);
}
