INSERT INTO JOB_SCHEDULE (job_name, trigger_cron, active_yn)
VALUES
    ('batchJob1', '0 * * * * ?', 'Y'),
    ('batchJob2', '0 * * * * ?', 'Y');