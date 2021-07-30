建表语句：

```mysql
use basedb;

CREATE TABLE IF NOT EXISTS `documentation`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `content` TEXT,
    `lever` int not null default 0,
    `parent_id` INT NOT NULL DEFAULT -1,
    `permission_level` INT NOT NULL DEFAULT 2,
    `cuid` INT NOT NULL DEFAULT 1,
    KEY `lever` (`lever`),
    KEY `parent_id` (`parent_id`)
) DEFAULT CHARSET=UTF8MB4;
```

