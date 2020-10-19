/*
package root.report.datastorage;

import com.cnbmtech.api.common.audit.BaseAbsAuditEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

*/
/**
 * @author 0
 *//*

@ApiModel(description = "大数据控制中心 -- 任务管理")
@Entity
@Table(name = "bigdata_control_task_manage")
public class TaskManage extends BaseAbsAuditEntity {
    @Id
    @GeneratedValue(generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "com.cnbmtech.api.utils.id.SnowflakeId")
    @ApiModelProperty(value = "序号")
    private Long id;

    @ApiModelProperty(value = "任务类型")
    private String taskType;

    @ApiModelProperty(value = "任务标题")
    private String taskName;

    @ApiModelProperty(value = "任务版本")
    private String taskVersion;

    @ApiModelProperty(value = "运行状态")
    private String runState;

    @ApiModelProperty(value = "执行状态")
    private String executionStatus;

    @ApiModelProperty(value = "最后一次执行时间")
    private Date lastTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskVersion() {
        return taskVersion;
    }

    public void setTaskVersion(String taskVersion) {
        this.taskVersion = taskVersion;
    }

    public String getRunState() {
        return runState;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public TaskManage(Long id, String taskType, String taskName, String  executionStatus, Date lastTime) {
        this.id = id ;
        this.taskType = taskType;
        this.taskName = taskName;
        this.executionStatus = executionStatus;
        this.lastTime = lastTime;
    }
    public TaskManage() {
    }
}
*/
