DROP TABLE IF EXISTS `JOB_SCHEDULE`;

CREATE TABLE `JOB_SCHEDULE`
(
    id   int NOT NULL AUTO_INCREMENT COMMENT 'uniq job id',
    job_name     varchar(40) DEFAULT NULL COMMENT '배치 명',
    trigger_cron varchar(40) DEFAULT NULL COMMENT '배치 시간 (cron 형식)',
    active_yn    char(1)     DEFAULT 'N' COMMENT '배치 활성 여부',
    PRIMARY KEY (id)
);