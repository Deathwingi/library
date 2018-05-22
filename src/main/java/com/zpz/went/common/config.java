package com.zpz.went.common;

import com.jfinal.config.*;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.ElKit;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.IDataSourceProvider;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;
import com.zpz.went.common.interceptor.GlobalInterceptor;
import com.zpz.went.common.interceptor.UserInterceptor;
import com.zpz.went.common.model._MappingKit;
import com.zpz.went.hello.HelloController;

/**
 * Created by home on 2017/11/23.
 */
public class config extends JFinalConfig {
    private static final Prop p = PropKit
            .use("config.properties")
            .appendIfExists("config-dev.properties");

    public void configConstant(Constants me) {
        me.setDevMode(p.getBoolean("devMode"));
        me.setJsonFactory(MixedJsonFactory.me());
    }

    public void configRoute(Routes me) {
        me.add("/hello", HelloController.class);
        me.add(new FrontRoutes());
    }

    public void configEngine(Engine me) {
        me.addSharedFunction("/view/common/layout.html");
    }

    public void configPlugin(Plugins me) {
        DruidPlugin dp = new DruidPlugin(p.get("jdbc.url"), p.get("jdbc.user"),
                p.get("jdbc.pwd"), p.get("jdbc.driver"));
        me.add(dp);
        ActiveRecordPlugin arp = getActiveRecordPlugin(dp);
        arp.setDialect(new SqlServerDialect());
        me.add(arp);
        me.add(getRedisPlugin());
    }

    public DruidPlugin getDruidPlugin() {
        String url = p.get("jdbc.url");
        String username = p.get("jdbc.user");
        String password = p.get("jdbc.pwd");
        String driverClass = p.get("jdbc.driver");
        return new DruidPlugin(url, username, password, driverClass);
    }

    private RedisPlugin getRedisPlugin() {
        String host = p.get("redis.host");
        int port = p.getInt("redis.port");
        int timeout = ElKit.eval(p.get("redis.timeout"));
        String password = p.get("redis.password");
        int database = p.getInt("redis.tokenDatabase");
        return new RedisPlugin("token", host, port, timeout, password, database);
    }

    public void configInterceptor(Interceptors me) {
        me.add(new UserInterceptor());
        me.add(new GlobalInterceptor());
    }

    public void configHandler(Handlers handlers) {

    }

    private ActiveRecordPlugin getActiveRecordPlugin(IDataSourceProvider provider) {
        ActiveRecordPlugin arp = new ActiveRecordPlugin(provider);
        arp.setDialect(new MysqlDialect());
        _MappingKit.mapping(arp);
        arp.setBaseSqlTemplatePath(PathKit.getWebRootPath() + "/sql");
        arp.addSqlTemplate("all.sql");
        return arp;
    }
}

