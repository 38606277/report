package root.report.util.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * @Auther: pccw
 * @Date: 2018/11/16 16:36
 * @Description:
 */
public class EhcacheManager {

    private static Cache cache;

    public static synchronized  Cache getCache() {
        if(cache == null) initCache();
        return cache;
    }


    private static void initCache() {
        URL url = EhcacheManager.class.getClassLoader().getResource("ehcache.xml");
        CacheManager cm =  CacheManager.create(url);
       /* InputStream in = EhcacheManager.class.getClassLoader().getResourceAsStream("/ehcache.xml");
        CacheManager cm = CacheManager.create(in);*/
        cache = cm.getCache("mybatis-ys-cache");
    }

}
