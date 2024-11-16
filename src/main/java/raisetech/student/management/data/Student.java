package raisetech.student.management.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
  private int id;
  private String name;
  private String ruby;
  private String mailaddress;
  private String address;
  private int age;
  private String gender;
  private String nickname;
  private String remark;
  private Boolean isDeleted;

}
