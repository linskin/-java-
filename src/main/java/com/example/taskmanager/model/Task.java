package com.example.taskmanager.model;

/**
 * 任务实体类
 */
public class Task {
    private long id;
    private String description;
    private TaskStatus status;
    private Long parentId;
    private int subtaskCount;

    // 默认构造函数
    public Task() {}

    // 用于从数据库提取数据
    public Task(long id,String description,TaskStatus status,Long parentId,int subtaskCount){
        this.id = id;
        this.description = description;
        this.status = status;
        this.parentId = parentId;
        this.subtaskCount = subtaskCount;
    }

    // 用于创建新任务(Id由数据库自动生成,subtaskCount数据库默认为0)
    public Task(String description,TaskStatus status,Long parentId){
        this.description = description;
        this.status = status;
        this.parentId = parentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getSubtaskCount() {
        return subtaskCount;
    }

    public void setSubtaskCount(int subtaskCount) {
        this.subtaskCount = subtaskCount;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status.getDisplayName() +
                ", parentId=" + (parentId == null ? '无' : parentId) +
                ", subtaskCount=" + subtaskCount +
                '}';
    }
}