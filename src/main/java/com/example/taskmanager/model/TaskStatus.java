package com.example.taskmanager.model;

/**
 * 任务状态枚举
 */
public enum TaskStatus {
    TODO("待办"),
    IN_PROGRESS("进行中"),
    DONE("已完成");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static TaskStatus fromDisplayName(String displayName) {
        for (TaskStatus status : values()) {
            if (status.displayName.equals(displayName) || status.name().equalsIgnoreCase(displayName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid display name: " + displayName);
//        return TODO;
    }
}