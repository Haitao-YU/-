package ltd.linqiu.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Application {
    private Integer id;
    private Date date;
    private Integer state;
    private String stuId;
    private String stuName;
    private Integer stuSex;
    private String stuCollege;
    private Integer stuGrade;
    private String stuPhone;
    private Date serviceDate;
    private String serviceLocation;
    private double serviceHours;
    private String serviceContent;
    private String voucherName;
    private String voucherPhone;

    private String evidentMaterials;
}
