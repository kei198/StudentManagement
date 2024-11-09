package raisetech.student.management;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {
  private String id;
  private String studentsId;
  private String course;
  private LocalDate startDay;
  private LocalDate completionDay;
}
