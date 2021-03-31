package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VolunteerUser {
    private String id;
    private String password;
    private String name;
    private Integer sex;
    private String college;
    private Integer grade;
    private String phone;

    public void copy(VolunteerUser c) {
        this.id = c.getId();
        this.password = c.getPassword();
        this.name = c.getName();
        this.sex = c.getSex();
        this.college = c.getCollege();
        this.grade = c.getGrade();
        this.phone = c.getPhone();
    }
}
