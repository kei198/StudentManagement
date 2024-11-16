package raisetech.student.management.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class StudentCourse {
  private int id;
  private int studentsId;
  private String course;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDay;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate completionDay;
}
