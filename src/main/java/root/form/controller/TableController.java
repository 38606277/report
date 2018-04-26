package root.form.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;

@RestController
public class TableController  extends RO {


    @RequestMapping(value = "/CreateTable")
    public String CreateTable(){


        return  SuccessMsg("","");

    }



    @RequestMapping(value = "/getData/{templateID}")
    public String GetData(String templateID){


        return  SuccessMsg("","");

    }
    @RequestMapping(value = "/getData/{templateID}/{taskID}")
    public String GetData(String templateID,String taskID){


        return  SuccessMsg("","");

    }

    @RequestMapping(value = "/getData/{templateID}/{taskID}/{userID}")
    public String GetData(String templateID,String taskID,String userID){


        return  SuccessMsg("","");

    }


}
