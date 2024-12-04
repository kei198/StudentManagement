package raisetech.student.management.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
  private int id;
  @NotBlank
  private String name;
  @NotBlank
  private String ruby;
  @Email
  private String mailaddress;
  private String address;
  private int age;
  private String gender;
  private String nickname;
  private String remark;
  private Boolean isDeleted;

}
