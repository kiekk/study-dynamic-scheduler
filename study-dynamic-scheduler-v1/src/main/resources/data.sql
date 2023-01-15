INSERT INTO JOB_SCHEDULE (id, job_name, trigger_cron, active_yn)
VALUES
    ('1', 'batchJob1', '0 * * * * ?', 'Y'),
    ('2', 'batchJob2', '0 * * * * ?', 'Y');