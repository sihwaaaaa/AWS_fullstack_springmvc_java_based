package co.poetrypainting.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan({"co.poetrypainting.service", "co.poetrypainting.domain", "co.poetrypainting.task"})
@MapperScan("co.poetrypainting.mapper")
//@PropertySource("classpath:/jdbc.properties")
public class RootConfig {
//	@Autowired
//	private Properties properties;
//	
	@Bean
	public DataSource dataSource(){
		HikariConfig config = new HikariConfig();
	//	config.setDataSourceProperties(properties);
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:mariadb://np.poetrypainting.co.kr:3306/spring_db");
		config.setUsername("SAMPLE");
		config.setPassword("1234");
		
		
		return new HikariDataSource(config);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setTypeAliasesPackage("co.poetrypainting.domain");
		return bean.getObject();
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager(){
		return new DataSourceTransactionManager(dataSource());
	}
}
