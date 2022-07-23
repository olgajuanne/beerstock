package dio.project.beerstock.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import dio.project.beerstock.dto.BeerDTO;
import dio.project.beerstock.dto.QuantityDTO;
import dio.project.beerstock.exception.BeerAlreadyRegisteredException;
import dio.project.beerstock.exception.BeerNotFoundException;
import dio.project.beerstock.exception.BeerStockExceededException;
import dio.project.beerstock.service.BeerService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/beers") //
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController implements BeerControllerDocs {

  private final BeerService beerService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
      return beerService.createBeer(beerDTO);
  }

  @GetMapping("/{name}")
  public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
      return beerService.findByName(name);
  }

  @GetMapping
  public List<BeerDTO> listBeers() {
      return beerService.listAll();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id) throws BeerNotFoundException {
      beerService.deleteById(id);
  }

  @PatchMapping("/{id}/increment")
  public BeerDTO increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO) throws BeerNotFoundException, BeerStockExceededException {
      return beerService.increment(id, quantityDTO.getQuantity());
  }
}
