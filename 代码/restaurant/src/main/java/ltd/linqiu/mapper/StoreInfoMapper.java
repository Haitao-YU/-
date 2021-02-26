package ltd.linqiu.mapper;

import ltd.linqiu.entity.StoreInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StoreInfoMapper {
    @Select("select * from store_info")
    List<StoreInfo> selectAll();

    @Select("select * from store_info where id = #{id}")
    StoreInfo selectById(@Param("id") Integer id);

    @Select("select * from store_info where name = #{name}")
    List<StoreInfo> selectByName(@Param("name") String name);

    @Insert("insert into store_info(name,value) values (#{name},#{value})")
    Integer insert(StoreInfo storeInfo);

    @Update("update store_info set value = #{value} where name = #{name}")
    Integer update(StoreInfo storeInfo);
}
