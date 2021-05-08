package ltd.linqiu.mapper;

import ltd.linqiu.entity.Remark;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RemarkMapper {

    Integer insert(Remark remark);

    @Select("select * from remark order by id desc")
    List<Remark> selectAll();

    @Select("select * from remark where id = #{id}")
    Remark selectById(@Param("id") Integer id);

    @Select("select * from remark where order_id = #{orderId}")
    Remark selectByOrderId(@Param("orderId") Integer orderId);

    @Delete("delete from remark where id = #{id}")
    Integer delete(Remark remark);

    @Update("update remark set images = #{images} where id = #{id}")
    Integer updateImagesById(@Param("images") String images, @Param("id") Integer id);

    @Update("update remark set reply = #{reply} where id = #{id}")
    Integer updateReplyById(@Param("reply") String reply, @Param("id") Integer id);
}
