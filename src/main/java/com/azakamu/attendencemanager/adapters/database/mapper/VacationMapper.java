package com.azakamu.attendencemanager.adapters.database.mapper;

import com.azakamu.attendencemanager.adapters.database.datatransfer.values.VacationDto;
import com.azakamu.attendencemanager.domain.values.Vacation;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {
        TimeframeMapper.class})
public interface VacationMapper {

  Vacation toDomain(VacationDto vacationDto);

  VacationDto toDto(Vacation vacation);

  Set<Vacation> toDomainList(Set<VacationDto> vacationDtos);

  Set<VacationDto> toEntityList(Set<Vacation> vacations);
}
