package com.azakamu.attendencemanager.domain.entities;

import com.azakamu.attendencemanager.domain.values.ExamId;
import com.azakamu.attendencemanager.domain.values.Timeframe;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import org.apache.tomcat.jni.Local;

/**
 * Exam is an entity that represents the an exam a {@link Student} entity could attend and
 * a {@link Timeframe} which represents the exemption period
 * (including for example arrival and departure). In addition, it contains information whether
 * the exam takes place online or offline, its name and id.
 * <br>
 * <br>
 * E.g. an exam in starts at 11:00 and the exam takes place offline,
 * {@link Timeframe#start()} could be at 10:00 so that the student has 1h for arrival.
 */
public class Exam {

  private final ExamId examId;
  private final String name;
  private final Integer exemptionOffset;
  private final Timeframe timeframe;
  private final Boolean online;

  /**
   * Required arguments constructor, that initializes every class attribute.
   *
   * @param examId          the related {@link ExamId}
   * @param name            the name of the exam
   * @param online          whether the exam takes place offline or online
   * @param exemptionOffset the additional time added at the start and end depending on {@link #online}
   * @param timeframe       the {@link Timeframe} in which the exam takes place
   */
  public Exam(ExamId examId, String name, Boolean online, Integer exemptionOffset,
              Timeframe timeframe) {
    this.examId = examId;
    this.name = name;
    this.online = online;
    this.exemptionOffset = exemptionOffset;
    this.timeframe = setTimeframe(timeframe);
  }


  private LocalTime increaseStart() {
    if (online) {
      return timeframe.start().plusMinutes(exemptionOffset);
    }
    return timeframe.start().plusMinutes(exemptionOffset);
  }

  private LocalTime decreaseEnd() {
    if (online) {
      return timeframe.end().minusMinutes(exemptionOffset);
    }
    return timeframe.end();
  }

  public List<LocalTime> getReducedExamTime() {
    return List.of(increaseStart(), decreaseEnd());
  }

  public String getExamTimeframe() {
    List<LocalTime> times = getReducedExamTime();
    return timeframe.date() + ", " + times.get(0) + " - " + times.get(1);
  }

  public String getExamExcemptionTime() {
    return timeframe.start() + " - " + timeframe.end();
  }

  public static Exam createDummy() {
    return new Exam(
        ExamId.createDummy(),
        "Dummy",
        false,
        120,
        new Timeframe(LocalDate.now(), LocalTime.now(), LocalTime.now()));
  }

  private Timeframe setTimeframe(Timeframe timeframe) {
    if (online) {
      return new Timeframe(timeframe.date(),
          timeframe.start().minusMinutes(exemptionOffset),
          timeframe.end());
    }
    return new Timeframe(timeframe.date(),
        timeframe.start().minusMinutes(exemptionOffset),
        timeframe.end().plusMinutes(exemptionOffset));
  }

  public ExamId getExamId() {
    return examId;
  }

  public String getName() {
    return name;
  }

  public Integer getExemptionOffset() {
    return exemptionOffset;
  }

  public Timeframe getTimeframe() {
    return timeframe;
  }

  public Boolean getOnline() {
    return online;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Exam exam = (Exam) o;
    return examId.equals(exam.examId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(examId);
  }
}