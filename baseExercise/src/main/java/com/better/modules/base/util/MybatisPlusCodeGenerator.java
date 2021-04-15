package com.better.modules.base.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MyBatisPlusIntegration
 * 执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 */
public class MybatisPlusCodeGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) throws Exception {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
        throw new Exception("请输入正确的" + tip + "！");
    }

    @SneakyThrows
    public static void main(String[] args) {

        //step.1 全局配置
        GlobalConfig gc = new GlobalConfig();
        /**String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/vue_day03_admin/src/main/java");*/
        gc.setOutputDir("/MybatisPlusCodeGenerator/IdeaProjects/dobetter");// 生成路径，最好使用绝对路径，window路径是不一样的
        gc.setAuthor("wangxq");
        gc.setIdType(IdType.AUTO);// 主键策略
        gc.setSwagger2(true);//自动配置swagger文档
        gc.setOpen(false);// 是否打开输出目录，默认true
        gc.setActiveRecord(true);// 开启 ActiveRecord 模式，默认false
        gc.setFileOverride(true);// 是否覆盖已有文件
        gc.setBaseResultMap(true);// XML 开启 BaseResultMap
        gc.setBaseColumnList(true);// XML 开启 baseColumnList  生成基本的SQL片段
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setControllerName("%sAction"); gc.setMapperName("%sDao"); gc.setXmlName("%sMapper"); gc.setServiceImplName("%sServiceDiy");
        gc.setServiceName("%sService");


        //step.2 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 设置数据库类型
        dsc.setDbType(DbType.MYSQL)
           .setUrl("jdbc:mysql://172.23.112.101:3306/aes_dev?useUnicode=true&useSSL=false&characterEncoding=utf8")
           .setDriverName("com.mysql.cj.jdbc.Driver")
           .setUsername("root")
           .setPassword("root")
           .setTypeConvert(new MySqlTypeConvert() {
               // 自定义数据库表字段类型转换【可选】
               @Override
               public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                   System.out.println("转换类型：" + fieldType);
                   // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                   return super.processTypeConvert(globalConfig, fieldType);
               }
           });


        //step.3 策略配置globalConfiguration中
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true) //全局大写命名
                .setEntityLombokModel(true) //使用lombok
                .setRestControllerStyle(true) //使用Restcontroller注解
                .setTablePrefix(new String[]{"tb_", "tsys_", "t_"})// 此处可以修改为您的表前缀
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                .setInclude(scanner("表名，多个英文逗号分割").split(",")) // 需要生成的表
                //strategy.setInclude(new String[] { "tb_user" });
                .setEntityBuilderModel(true);


        //step.4 包名策略配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dobetter")
          .setModuleName(scanner("模块名"));


        //step. 自定义配置
        /*InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // TODO
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return gc.getOutputDir()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);*/





        //step.5 整合配置
        AutoGenerator mpg = new AutoGenerator();
        mpg.setDataSource(dsc);
        mpg.setPackageInfo(pc);
//        mpg.setCfg(cfg);
        mpg.setGlobalConfig(gc);
//        mpg.setTemplate(templateConfig);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        //step.6 执行操作
        mpg.execute();
    }

}
