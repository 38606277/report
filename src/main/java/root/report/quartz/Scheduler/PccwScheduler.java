package root.report.quartz.Scheduler;

import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Auther: pccw
 * @Date: 2018/11/26 15:08
 * @Description:
 *      创建 quartz 任务调度器
 */
public class PccwScheduler {

    public static void main(String[] args) throws SchedulerException {

        // 1. 创建 调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();   // 添加异常

        // 2. 声明 jobDetail 跟 trigger 触发器

        // 3. 关联 jobDetail 跟 trigger 之间的关系

        // 4. 启动
    }

}
