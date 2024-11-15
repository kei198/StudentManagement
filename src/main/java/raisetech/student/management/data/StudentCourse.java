package raisetech.student.management.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {
  private int id;
  private int studentsId;
  private String course;
  private LocalDate startDay;
  private LocalDate completionDay;
}
