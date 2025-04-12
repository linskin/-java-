package com.example.taskmanager.dao;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Task 数据访问对象接口
 */
public interface TaskDao {

    /**
     * 保存新任务到数据库。如果是子任务，会处理事务更新父任务计数。
     * @param task 要保存的任务对象 (ID 应为空或无效)
     * @return 保存后的任务对象 (包含数据库生成的 ID)
     * @throws SQLException 如果数据库操作失败
     */
    Task save(Task task) throws SQLException;

    /**
     * 根据 ID 查找任务
     * @param id 任务 ID
     * @return 包含任务的 Optional，如果未找到则为空
     * @throws SQLException 如果数据库操作失败
     */
    Optional<Task> findById(long id) throws SQLException;

    /**
     * 查找所有任务
     * @return 任务列表
     * @throws SQLException 如果数据库操作失败
     */
    List<Task> findAll() throws SQLException;

    /**
     * 更新任务状态
     * @param id 任务 ID
     * @param status 新的任务状态
     * @return 如果更新成功返回 true，否则 false
     * @throws SQLException 如果数据库操作失败
     */
    boolean updateStatus(long id, TaskStatus status) throws SQLException;

    /**
     * 更新任务的子任务计数 (主要用于事务)
     * @param taskId 任务 ID
     * @param delta 变化的数量 (例如 +1 或 -1)
     * @param connection 外部传入的连接，用于事务控制
     * @return 如果更新成功返回 true
     * @throws SQLException 如果数据库操作失败
     */
    boolean updateSubtaskCount(long taskId, int delta, Connection connection) throws SQLException;

    /**
     * 根据 ID 删除任务。注意：如果删除的是父任务，子任务随之删除。
     * @param id 任务 ID
     * @return 如果删除成功返回 true，否则 false
     * @throws SQLException 如果数据库操作失败
     */
    boolean deleteById(long id) throws SQLException;

}
