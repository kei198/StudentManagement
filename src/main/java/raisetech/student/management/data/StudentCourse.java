package raisetech.student.management.data;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class StudentCourse {
  private int id;
  private int studentsId;
  @NotBlank
  private String course;
  @NotBlank
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDay;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate completionDay;
}
