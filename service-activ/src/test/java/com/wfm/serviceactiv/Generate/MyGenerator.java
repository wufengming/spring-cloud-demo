package com.wfm.serviceactiv.Generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * description: MyGenerator
 * date: 2019-10-24 14:36
 * author: wfm
 * version: 1.0
 */

public class MyGenerator {
    //配置部分

    //数据库配置
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DRIVER_URL = "jdbc:mysql://localhost:3306/wfm?useUnicode=true&characterEncoding=UTF-8&useSSL=false";

    // 项目主包路径
    private static final String PARENT_PACKAGE = "com.wfm.serviceactiv";
    private static final String COMMON_PARENT_PACKAGE = PARENT_PACKAGE + ".common";

    // 父类包路径
    private static final String SUPER_ENTITY = COMMON_PARENT_PACKAGE + ".base.BaseEntity";
    private static final String SUPER_CONTROLLER = COMMON_PARENT_PACKAGE + ".base.BaseController";
    private static final String SUPER_SERVICE = COMMON_PARENT_PACKAGE + ".base.BaseService";
    private static final String SUPER_SERVICE_IMPL = COMMON_PARENT_PACKAGE + ".base.BaseServiceImpl";
    private static final String[] SUPER_ENTITY_COMMON_COLUMNS = new String[]{"id", "creator","editor","cur_orgid","insert_dt","update_dt","record_ver"};
    private static final String[] TABLE_PREFIX=new String[]{"s_"};

    // 作者
    private static final String AUTHOR = "wfm";
    // 生成的表名称
    private static final String[] TABLE_NAME = new String[]{"s_dict","s_dict_data","s_menu","s_menu_button","s_organize","s_role","s_role_authorize","s_user","s_user_org"};


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/service-activ/src/main/java");//输出目录
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);                  // 是否打开输出目录
        gc.setSwagger2(true);               // 启用swagger注解
        gc.setIdType(IdType.ID_WORKER);     // 主键类型:ID_WORKER
        gc.setFileOverride(true);           // 是否覆盖已有文件
        gc.setDateType(DateType.ONLY_DATE); // 设置日期类型为Date
        //gc.setEnableCache(false);// XML 二级缓存
        //gc.setBaseResultMap(true);  //XML ResultMap
        gc.setBaseColumnList(true); //XML columList

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setXmlName("%sMapper");
        gc.setEntityName("%sEntity");
        gc.setMapperName("%sMapper");

        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");

        mpg.setGlobalConfig(gc);

        //2.数据源等配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        /*dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                //    return DbColumnType.BOOLEAN;
                // }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }

        });*/
        //mysql  数据库的信息配置
        // dsc.setSchemaName("public");
        dsc.setUrl(DRIVER_URL);

        //Mysql5 com.mysql.jdbc.Driver
        //Mysql6 com.mysql.cj.jdbc.Driver， 需要指定时区serverTimezone
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        // 设置自定义查询
        //dsc.setDbQuery(new SpringBootPlusMySqlQuery());
        mpg.setDataSource(dsc);


        //3.包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT_PACKAGE);
        //pc.setModuleName(rb.getString("productName"));
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        //4. 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();

        // 生成mapper xml

        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/service-activ/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });


        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 模版生成配置，设置为空，表示不生成
        TemplateConfig templateConfig = new TemplateConfig();

        // xml使用自定义输出
        templateConfig.setXml(null);
        //templateConfig.setEntity(null);
        //templateConfig.setMapper(null);
        //templateConfig.setService(null);
        //templateConfig.setServiceImpl(null);
        //templateConfig.setController(null);
        mpg.setTemplate(templateConfig);


        // 5. 策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setNaming(NamingStrategy.underline_to_camel);          // 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);    // 列生成策略

        strategy.setSuperEntityClass(SUPER_ENTITY);                     //自定义实体父类
        strategy.setEntityLombokModel(true);
        strategy.setSuperEntityColumns(SUPER_ENTITY_COMMON_COLUMNS);    //父类含有的字段
        //strategy.setTableFillList(tableFillList)  //添加额外字段
        strategy.setTablePrefix(TABLE_PREFIX);                          //此处可以修改为您的表前缀

        strategy.setSuperServiceClass(SUPER_SERVICE);
        strategy.setSuperServiceImplClass(SUPER_SERVICE_IMPL);

        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass(SUPER_CONTROLLER);

        strategy.setInclude(TABLE_NAME);

        strategy.setControllerMappingHyphenStyle(true);
        /**
         * 注意，根据实际情况，进行设置
         * 当表名称的前缀和模块名称一样时，会去掉表的前缀
         * 比如模块名称为user,表明为user_info,则生成的实体名称是Info.java,一定要注意
         */
        mpg.setStrategy(strategy);
        mpg.execute();

    }
}
