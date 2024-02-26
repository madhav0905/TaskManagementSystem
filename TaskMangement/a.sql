use task_manager;
CREATE TABLE users (
    id BINARY(16) PRIMARY KEY,
    username BIGINT UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_enabled BOOLEAN DEFAULT FALSE,
    email VARCHAR(255)
);


CREATE TABLE tasks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    status VARCHAR(50),
    priority VARCHAR(50),
    assigner_id BINARY(16),
    assignee_id BINARY(16),
    FOREIGN KEY (assigner_id) REFERENCES users(id),
    FOREIGN KEY (assignee_id) REFERENCES users(id)
);

ALTER TABLE users MODIFY COLUMN id BINARY(16) DEFAULT (UUID_TO_BIN(UUID()));
CREATE TABLE `roles` (
  `user_id` BIGINT  NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `users` (username,password,is_enabled,email)
VALUES 
(12345,'{noop}maddy',1,'maddy@gmail.com')
(5678,'{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1,'mary@gmail.com')

select username, password, is_enabled from users where username=12345;
select * from tasks;
update  users  set password ='{noop}maddy' where username=1234;

insert into tasks(description,status,priority,assigner_id,assignee_id)
values
("task 1","INPROGRESS","HIGH",UUID_TO_BIN('9d89887c-d3c6-11ee-854a-e8d8d1dc47b6'),UUID_TO_BIN('e525006b-d3c9-11ee-854a-e8d8d1dc47b6'))