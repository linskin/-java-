package com.example.taskmanager.dao.impl;

import com.example.taskmanager.dao.TaskDao;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JdbcTaskDao implements TaskDao{

    @Override
    public Task save(Task task) throws SQLException {
        return null;
    }

    @Override
    public Optional<Task> findById(long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() throws SQLException {
        return Collections.emptyList();
    }

    @Override
    public boolean updateStatus(long id, TaskStatus status) throws SQLException {
        return false;
    }

    @Override
    public boolean updateSubtaskCount(long taskId, int delta, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws SQLException {
        return false;
    }
}
