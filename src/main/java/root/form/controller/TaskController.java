package root.form.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;

@RestController
public class TaskController extends RO {

    //创建模板任务
    @RequestMapping(value = "/getTask/{templateID}")
    public  String CreateTask(){

        return  SuccessMsg("","");
    }

    //修改模板任务
    @RequestMapping(value = "/getTask/{templateID}")
    public  String UpdateTask(){

        return  SuccessMsg("","");
    }

    //修改模板任务
    @RequestMapping(value = "/getTask/{templateID}")
    public  String DeleteTask(){

        return  SuccessMsg("","");
    }

    //获取当前模板下的任务
    @RequestMapping(value = "/getTask/{templateID}")
    public  String GetTask(){

        return  SuccessMsg("","");
    }




}
