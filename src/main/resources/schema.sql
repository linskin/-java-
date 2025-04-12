-- 用于创建数据库和表的 SQL 脚本

-- 如果需要，创建数据库 (如果已存在则忽略)
-- CREATE DATABASE IF NOT EXISTS task_manager_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- USE task_manager_db;

-- 删除已存在的表 (方便重新创建)
DROP TABLE IF EXISTS tasks;

-- 创建 tasks 表
CREATE TABLE tasks (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,                 -- 任务 ID，自增主键
                       description VARCHAR(255) NOT NULL,                    -- 任务描述
                       status ENUM('TODO', 'IN_PROGRESS', 'DONE') NOT NULL DEFAULT 'TODO', -- 任务状态 (TODO, IN_PROGRESS, DONE)
                       parent_id BIGINT NULL,                                -- 父任务 ID，可以为空
                       subtask_count INT NOT NULL DEFAULT 0,                 -- 子任务数量
    -- 添加外键约束，确保 parent_id 指向存在的 task id
                       FOREIGN KEY (parent_id) REFERENCES tasks(id) ON DELETE SET NULL -- 父任务删除时，子任务的 parent_id 设为 NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 可以添加一些索引以提高查询性能
CREATE INDEX idx_status ON tasks(status);
CREATE INDEX idx_parent_id ON tasks(parent_id);

-- 插入一些示例数据 (可选)
INSERT INTO tasks (description, status) VALUES ('学习 Java JDBC', 'TODO');
INSERT INTO tasks (description, status) VALUES ('完成任务管理器项目', 'TODO');
INSERT INTO tasks (description, status, parent_id) VALUES ('设计数据库表结构', 'DONE', 2);
UPDATE tasks SET subtask_count = 1 WHERE id = 2; -- 更新父任务计数
INSERT INTO tasks (description, status, parent_id) VALUES ('编写 DAO 层代码', 'IN_PROGRESS', 2);
UPDATE tasks SET subtask_count = 2 WHERE id = 2; -- 更新父任务计数
