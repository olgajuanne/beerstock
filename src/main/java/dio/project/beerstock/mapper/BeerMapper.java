package dio.project.beerstock.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import dio.project.beerstock.dto.BeerDTO;
import dio.project.beerstock.entity.Beer;


@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer toModel(BeerDTO beerDTO);

    BeerDTO toDTO(Beer beer);
}
