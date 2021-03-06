package com.zpz.went.common.model;

import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;
import com.zpz.went.common.config;

/**
 * Created by home on 2018/3/6.
 */
public class _Generator{

    public static void main(String[] args) {

        String baseModelPackageName = "com.zpz.went.common.model.base";
        String baseModelOutputDir = "src/main/java/com/zpz/went/common/model/base";
        String modelPackageName = "com.zpz.went.common.model";
        String modelOutputDir = "src/main/java/com/zpz/went/common/model";

        DruidPlugin dp = new config().getDruidPlugin();
        dp.start();

        Generator generator = new Generator(dp.getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);

        generator.setDialect(new SqlServerDialect());
        generator.setGenerateChainSetter(true);
        generator.setGenerateDaoInModel(false);
        generator.setRemovedTableNamePrefixes("j_");
        generator.setGenerateDataDictionary(false);

        generator.generate();

        dp.stop();

    }

}
