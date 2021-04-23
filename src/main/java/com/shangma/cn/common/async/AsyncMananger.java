package com.shangma.cn.common.async;

import com.shangma.cn.common.utils.SpringContainerUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/23 11:11
 * 文件说明：
 */
public class AsyncMananger {


    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    private static AsyncMananger asyncMananger;


    private AsyncMananger() {
        threadPoolTaskExecutor = SpringContainerUtils.getBean(ThreadPoolTaskExecutor.class);
    }

    public static AsyncMananger getInstance() {
        if (asyncMananger == null) {
            asyncMananger = new AsyncMananger();
        }
        return asyncMananger;
    }

    public void executeByAsync(Runnable runnable) {
        threadPoolTaskExecutor.execute(runnable);
    }

    /**
     * 停止线程池执行
     */
    public void shutDown() {
        threadPoolTaskExecutor.shutdown();
    }


}
