package com.lbc.mo.constants;

public enum EmailOpts {

    FINISHED("Job completes successfully (1) FINISHED", 16),
    IDLE("Idle job is automatically shutdown (1) 3小时提醒、4小时停止", 8),
    KILLED("Manually shutdown a job (0) 用户自己停止", 4),
    FAIL_TIMEOUT("Job failed, i.e. error in code, timeout (1) FAILURE、超时提醒、超时停止", 2),
    RESOURCE("Resource is not enough (1) 资源不足提醒、运行之后提醒", 1);

    public final String name;
    public final int value;

    EmailOpts(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
