@SpringBootApplication
public class JsonRpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonRpcServerApplication.class, 
							args);
    }

    @Bean
    public JsonRpcServer jsonRpcServer(ObjectMapper mapper) {
        JsonRpcServer server = new JsonRpcServer(
				mapper, new SleepServiceImpl());
        return server;
    }
}