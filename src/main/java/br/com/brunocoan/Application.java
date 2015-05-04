package br.com.brunocoan;

import java.io.File;
import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	@Configuration
	@EnableNeo4jRepositories(basePackages = "br.com.brunocoan")
	@EnableTransactionManagement
	static class ApplicationConfig {
		private static  String dbName = "accessingdataneo4j.db";
		private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

		public ApplicationConfig() {

		}
	    @Configuration
	    static class Neo4jMoreConfig extends Neo4jConfiguration {

	        Neo4jMoreConfig() {
				setBasePackage("br.com.brunocoan");
				try {
		            FileUtils.deleteRecursively(new File(dbName));
		        } catch (IOException ex) {
		        	LOGGER.warn(String.format("failed to delete file %s", dbName));
		        }
	        }

	    }

		@Bean
		GraphDatabaseService graphDatabaseService() {
			return new GraphDatabaseFactory().newEmbeddedDatabase(dbName);
		}
		
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
