package com.xyhua.mapper;

import com.xyhua.bean.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    @Select("select * from student where id = #{sid}")
    Student getStudent(@Param("sid") String id);

    @Insert("insert into student values (#{sid}, #{sname}, #{sgender})")
    void insertOne(@Param("sid") String id, @Param("sname") String sname,
                   @Param("sgender") int sgender);
}
