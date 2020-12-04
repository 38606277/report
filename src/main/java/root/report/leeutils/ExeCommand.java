package root.report.leeutils;

import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.TreeMap;

/**
 * 在服务器中打印spark代码的启动命令
 * @author 0
 */
public class ExeCommand {

    public static String exe(final String cmd) {
        return getResult(cmd);
    }

    public static String exeSpark(final String cmd) {
        final String sparkCmd = CommonBigdataRestV2.SPARK_COMMMAND_PREFIX+" \""+cmd+"\"";
        return getResult(sparkCmd);
    }
    public static String getResult(final String cmd) {
        final Map<String, String> content = new TreeMap<>();
        String result;
        final String win = "win";
        final String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith(win)) {
            final String[] cmds = {"cmd.exe", "-c", cmd};
            result = RuntimeUtil.execForStr(cmds);
        } else {
            final String[] cmds = {"/bin/sh", "-c", cmd};
            result = RuntimeUtil.execForStr(cmds);
        }
        content.put("command", cmd);
        content.put("result", result);
        LogFactory.get().info(JSON.toJSONString(content));
        return result;
    }
}
