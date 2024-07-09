package app.heartsea.taskmanager.dao.mapper;

import app.heartsea.taskmanager.domain.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Select("SELECT * FROM tasks")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "creatorUserId", column = "creator_user_id"),
            @Result(property = "completerUserId", column = "completer_user_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "completedAt", column = "completed_at")
    })
    List<Task> findAll();

    @Select("SELECT * FROM tasks WHERE id = #{id}")
    Task findById(Long id);

    @Insert("INSERT INTO tasks(title, description, creator_user_id, created_at) VALUES(#{title}, #{description}, #{creatorUserId}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Task task);

    @Update("UPDATE tasks SET title=#{title}, description=#{description}, completer_user_id=#{completerUserId}, completed_at=#{completedAt} WHERE id = #{id}")
    void update(Task task);

    @Delete("DELETE FROM tasks WHERE id = #{id}")
    void delete(Long id);
}
