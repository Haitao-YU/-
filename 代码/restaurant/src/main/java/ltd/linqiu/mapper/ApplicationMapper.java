package ltd.linqiu.mapper;

import ltd.linqiu.entity.Application;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ApplicationMapper {
    @Select("select * from application order by id")
    List<Application> selectAll();

    @Select("select * from application where id = #{id}")
    Application selectById(@Param("id") Integer id);

    @Select("select * from application where stu_id = #{stuId}")
    List<Application> selectByStuId(@Param("stuId") String stuId);

    @Select("select max(id) from application")
    Integer getMaxId();

    @Insert("insert into application(date,state,stu_id,stu_name,stu_sex,stu_college,stu_grade,stu_phone,service_date," + "service_location,service_hours,service_content,voucher_name,voucher_phone,evidentMaterials) values(now()" + ",0,#{stuId},#{stuName},#{stuSex},#{stuCollege},#{stuGrade},#{stuPhone},#{serviceDate}," + "#{serviceLocation},#{serviceHours},#{serviceContent},#{voucherName},#{voucherPhone},#{evidentMaterials})")
    Integer insert(Application application);

    @Update("update application set evidentMaterials = #{evidentMaterials} where id = #{id}")
    Integer updateEvidentMaterialsById(@Param("id") Integer id, @Param("evidentMaterials") String evidentMaterials);

    @Update("update application set state = #{state} where id = #{id}")
    Integer updateStateById(Application application);

    @Delete("delete from application where id = #{id}")
    Integer delete(Application application);
}
