package com.goo99.goosreservation.service.impl;

import com.goo99.goosreservation.data.dto.PagingDto;
import com.goo99.goosreservation.data.dto.owner.StudioAddDto;
import com.goo99.goosreservation.data.dto.StudioInfoDto;
import com.goo99.goosreservation.data.entity.Owner;
import com.goo99.goosreservation.data.entity.Studio;
import com.goo99.goosreservation.exception.CustomException;
import com.goo99.goosreservation.repository.OwnerRepo;
import com.goo99.goosreservation.repository.StudioRepo;
import com.goo99.goosreservation.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.goo99.goosreservation.type.ErrorCode.OWNER_NOTFOUND;

@Service
@RequiredArgsConstructor
public class StudioServiceImpl implements StudioService {

  private final StudioRepo studioRepo;
  private final OwnerRepo ownerRepo;

  @Override
  public StudioInfoDto add(StudioAddDto studioAddDto) {

    Studio studio = studioRepo.save(StudioAddDto.toEntity(studioAddDto));

    Owner owner = findOwnerById(studioAddDto.getOwnerId());
    owner.setStudioId(studio.getId());
    ownerRepo.save(owner);

    return StudioInfoDto.from(studio, owner);
  }

  @Override
  public Page<StudioInfoDto> getList(PagingDto pagingDto) {

    Page<Studio> list = studioRepo.findAllBy(
      PageRequest.of(pagingDto.getPageNum() - 1, pagingDto.getSize(),
        Sort.by(Sort.Direction.DESC, "reviewCount")));

    return list.map(m -> {
      return StudioInfoDto.from(m, findOwnerById(m.getOwnerId()));
    });
  }

  @Override
  public StudioInfoDto getInfo(Long studioId) {

    Studio studio = findStudioById(studioId);
    Owner owner = findOwnerById(studio.getOwnerId());

    return StudioInfoDto.from(studio, owner);
  }

  public Owner findOwnerById(Long ownerId) {
    return ownerRepo.findById(ownerId)
      .orElseThrow(() -> new CustomException(OWNER_NOTFOUND));
  }

  public Studio findStudioById(Long studioId) {
    return studioRepo.findById(studioId)
      .orElseThrow(() -> new CustomException(OWNER_NOTFOUND));
  }
}
