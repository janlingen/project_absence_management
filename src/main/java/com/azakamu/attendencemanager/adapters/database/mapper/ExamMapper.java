package com.azakamu.attendencemanager.adapters.database.mapper;

import com.azakamu.attendencemanager.adapters.database.datatransfer.entities.ExamDto;
import com.azakamu.attendencemanager.domain.entities.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {
        TimeframeMapper.class,
        ExamIdMapper.class})
public interface ExamMapper {

  @Mapping(source = "id", target = "examId.id")
  Exam toDomain(ExamDto examDto);

  @Mapping(source = "examId.id", target = "id")
  ExamDto toDto(Exam exam);
}
