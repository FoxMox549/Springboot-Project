package com.wms.common;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {
    /**
     * <p>
     * read the console content
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("please input" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("please input correct" + tip + "！");
    }

    /**
     * Operating steps:
     * 1. Modify the data source to include address and password information,
     * and the corresponding code marks are: 1 the same below.
     * 2. Module configuration, you can modify the package name
     * 3. Modify the template (this step can be ignored)
     *
     * @param args
     */
    public static void main(String[] args) {
        // Code generator
        AutoGenerator mpg = new AutoGenerator();

        // Global configuration
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/wms";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("wms");
        gc.setOpen(false);
        gc.setSwagger2(true); //Entity attribute Swagger2 annotate.
        gc.setBaseResultMap(true);// XML ResultMap.
        gc.setBaseColumnList(true);// XML columList.
        // Remove I of the initial of the service interface.
        // If DO is User, it is called UserService.
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // Data source configuration
        DataSourceConfig dsc = new DataSourceConfig();
        // 1. Modify data source
        dsc.setUrl("jdbc:mysql://localhost:3306/wms?useUnicode=true&characterEncoding=UTF8&useSSL=false");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Yinzeyu180331!");
        mpg.setDataSource(dsc);

        // Package configuration
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("module name"));

        // 2. Module configuration
        pc.setParent("com.wms")
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller");
        mpg.setPackageInfo(pc);

        // Custom configuration
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // If the template engine is: freemarker
        String templatePath = "templates/mapper.xml.ftl";
        // If the template engine is: velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // Custom output configuration
        List<FileOutConfig> focList = new ArrayList<>();
        // Custom configuration will be output first.
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // Custom output file name ， If you set the prefix and suffix for your Entity,
                // please note that the name of xml will change accordingly.
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" +
                        StringPool.DOT_XML;
            }
        });
    /*
        cfg.setFileCreate(new IFileCreate() {
          @Override
          public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
               // Determine whether a custom folder needs to be created.
               checkDir("Call the default method to create the directory,
               and customize the directory with");
               if (fileType == FileType.MAPPER) {
               // If Mapper has been generated already, return false.
               return !new File(filePath).exists();
               }
               // Allow template parts to be generated.
               return true;
          }
         });
    */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // Configuration template
        TemplateConfig templateConfig = new TemplateConfig();

        // Configure a custom output template
        // Specify the custom template path, be careful not to bring. ftl/.vm,
        // which will be automatically recognized by the template engine based on.
        // 3. modify the template
        /*templateConfig.setEntity("templates/entity2.java");
        templateConfig.setService("templates/service2.java");
        templateConfig.setController("templates/controller2.java");
        templateConfig.setMapper("templates/mapper2.java");
        templateConfig.setServiceImpl("templates/serviceimpl2.java");*/

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // Strategy configuration
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("Your own parent entity,
        // you don't need to set it if you don't have it!");
        //strategy.setSuperEntityClass("BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // Common parent class
        //strategy.setSuperControllerClass("BaseController");
        // strategy.setSuperControllerClass("Your own parent controller,
        // you don't need to set it if you don't have it!");
        // Public fields written in the parent class
        // strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("table name，separate with commas if more than one").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        // Ignore the table prefix tb_, such as tb_user,
        // and map it directly to the user object.
        // 4. pay attention to whether to remove the table prefix.
        //strategy.setTablePrefix("tb_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}