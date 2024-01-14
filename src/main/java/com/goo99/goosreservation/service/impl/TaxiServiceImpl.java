package com.goo99.goosreservation.service.impl;

import com.goo99.goosreservation.data.dto.PagingDto;
import com.goo99.goosreservation.data.dto.TaxiAddDto;
import com.goo99.goosreservation.data.dto.TaxiInfoDto;
import com.goo99.goosreservation.data.entity.Driver;
import com.goo99.goosreservation.data.entity.Taxi;
import com.goo99.goosreservation.exception.CustomException;
import com.goo99.goosreservation.repository.DriverRepo;
import com.goo99.goosreservation.repository.TaxiRepo;
import com.goo99.goosreservation.service.TaxiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.goo99.goosreservation.type.ErrorCode.DRIVER_NOTFOUND;

@Service
@RequiredArgsConstructor
public class TaxiServiceImpl implements TaxiService {

  private final TaxiRepo taxiRepo;
  private final DriverRepo driverRepo;

  @Override
  public TaxiInfoDto add(TaxiAddDto taxiAddDto) {

    Taxi taxi = taxiRepo.save(TaxiAddDto.toEntity(taxiAddDto));

    Driver driver = findDriverById(taxiAddDto.getDriverId());
    driver.setTaxiId(taxi.getId());
    driverRepo.save(driver);

    return TaxiInfoDto.from(taxi, driver);
  }

  @Override
  public Page<TaxiInfoDto> getList(PagingDto pagingDto) {

    Page<Taxi> list = taxiRepo.findAllBy(
      PageRequest.of(pagingDto.getPageNum() - 1, pagingDto.getSize(),
        Sort.by(Sort.Direction.DESC, "reviewCount")));

    return list.map(m -> {
      return TaxiInfoDto.from(m, findDriverById(m.getDriverId()));
    });
  }

  @Override
  public TaxiInfoDto getInfo(Long taxiId) {

    Taxi taxi = findTaxiById(taxiId);
    Driver driver = findDriverById(taxi.getDriverId());

    return TaxiInfoDto.from(taxi, driver);
  }

  public Driver findDriverById(Long driverId) {
    return driverRepo.findById(driverId)
      .orElseThrow(() -> new CustomException(DRIVER_NOTFOUND));
  }

  public Taxi findTaxiById(Long taxiId) {
    return taxiRepo.findById(taxiId)
      .orElseThrow(() -> new CustomException(DRIVER_NOTFOUND));
  }
}
