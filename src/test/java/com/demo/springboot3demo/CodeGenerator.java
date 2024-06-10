package com.demo.springboot3demo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;

/**
 * @author Lives@gamevector
 * @since 2024/6/10
 */
public class CodeGenerator {

    private static final String url = "jdbc:mysql://localhost:3306/test_db_d?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai", username = "root", password = "123456";

    public static void main(String[] args) {

        FastAutoGenerator.create(
                        //  数据源配置
                        new DataSourceConfig.Builder(url, username, password)
                                .dbQuery(new MySqlQuery())
                                .schema("test_db_d")
                                .typeConvert(new MySqlTypeConvert())
                                .keyWordsHandler(new MySqlKeyWordsHandler())
                )
                // 全局配置
                .globalConfig(builder -> {
                    builder
                            .author("Lives@gamevector")// 设置作者
                            .disableOpenDir() // 生成后禁止打开所生成的系统目录
                            .enableSwagger() // 开启 swagger 模式
                            .dateType(DateType.TIME_PACK) // 时间策略
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                //  包配置
                .packageConfig(builder -> {
                    builder
                            .parent("com.demo.springboot3demo") // 设置父包名
                            .moduleName("web")  // 设置父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mapper"));
                })
                //  策略配置
                .strategyConfig(builder -> {
                    builder
                            // 过滤策略
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .addTableSuffix("_db") // 设置过滤表后缀
                            .addFieldPrefix("t_", "c_") // 设置过滤字段前缀
                            .addFieldSuffix("_field") // 设置过滤字段后缀
                            .addInclude("t_actor") // 设置需要生成的表名

                            //  Entity 策略配置
                            .entityBuilder()
                            .enableLombok() // 开启 lombok 模型
                            .enableChainModel() // 开启链式模型
                            .enableRemoveIsPrefix() // 开启 Boolean 类型字段移除 is 前缀
                            .enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                            .versionColumnName("version") // 设置乐观锁字段名
                            .versionPropertyName("version") // 乐观锁实体类名称
                            .logicDeleteColumnName("is_deleted") // 逻辑删除数据库中字段名
                            .logicDeletePropertyName("deleted") // 逻辑删除实体类中字段名
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的属性命名策略
                            .idType(IdType.AUTO) // 主键生成策略
                            .formatFileName("%s") // Entity 文件名称
                            .addTableFills(new Column("created_time", FieldFill.INSERT)) // 创建时间填充
                            .addTableFills(new Column("updated_time", FieldFill.INSERT_UPDATE)) // 更新时间填充
                            //.enableColumnConstant()
                            //.enableActiveRecord()

                            //  Controller 策略配置
                            .controllerBuilder()
                            //.superClass() // 父类
                            .enableRestStyle() // 开启生成 @RestController 控制器
                            .enableHyphenStyle() // 开启驼峰连字符

                            //  Service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService") // Service 文件名
                            .formatServiceImplFileName("%sServiceImpl") // ServiceImpl 文件名

                            //  Mapper 策略配置
                            .mapperBuilder()
                            .mapperAnnotation(Mapper.class)
                            .enableBaseColumnList()
                            .enableBaseResultMap()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");
                })
                .execute();
    }
}
