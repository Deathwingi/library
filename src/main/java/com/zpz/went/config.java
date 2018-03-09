package com.zpz.went;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.IDataSourceProvider;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * Created by home on 2017/11/23.
 */
public class config extends JFinalConfig {
    private static final Prop p = loadConfig();

    //加载配置
    private static Prop loadConfig() {

        return PropKit.use("config.properties");
    }


    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 80, "/");
    }

    public void configConstant(Constants me) {
        me.setDevMode(p.getBoolean("devMode"));
    }

    public void configRoute(Routes me) {
        me.add("/hello", HelloController.class);
        me.add(new FrontRoutes());
    }

    public void configEngine(Engine me) {
        me.addSharedFunction("view/common/layout.html");
    }

    public void configPlugin(Plugins me) {
        DruidPlugin dp = new DruidPlugin(p.get("jdbc.url"),p.get("jdbc.user"),
                p.get("jdbc.pwd"),p.get("jdbc.driver"));
        me.add(dp);
        ActiveRecordPlugin arp = getActiveRecordPlugin(dp);
        arp.setDialect(new SqlServerDialect());
        me.add(arp);
        arp.addMapping("j_user", "id", User.class);
    }
    public DruidPlugin getDruidPlugin() {
        String url = p.get("jdbc.url");
        String username = p.get("jdbc.user");
        String password = p.get("jdbc.pwd");
        String driverClass = p.get("jdbc.driver");
        return new DruidPlugin(url, username, password, driverClass);
    }

    public void configInterceptor(Interceptors interceptors) {

    }

    public void configHandler(Handlers handlers) {

    }
    private ActiveRecordPlugin getActiveRecordPlugin(IDataSourceProvider provider) {
        ActiveRecordPlugin arp = new ActiveRecordPlugin(provider);
        arp.setDialect(new SqlServerDialect());
        _MappingKit.mapping(arp);
        arp.setBaseSqlTemplatePath(PathKit.getWebRootPath() + "/sql");
        arp.addSqlTemplate("all.sql");
        return arp;
    }
}

