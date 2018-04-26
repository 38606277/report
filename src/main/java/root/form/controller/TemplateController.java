package root.form.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;

@RestController
public class TemplateController extends RO {


    @RequestMapping(value = "/create")
    public String Create(){
        return SuccessMsg("创建成功","");
    }

    @RequestMapping(value = "/update")
    public String Update(){
        return SuccessMsg("更新成功","");
    }


    //查询所有模板
    @RequestMapping(value = "/List")
    public String Get(){
        return SuccessMsg("成功","");
    }


}
