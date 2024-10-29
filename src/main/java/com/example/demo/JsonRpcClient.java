public class JsonRpcClient {
    public static void main(String[] args) throws Throwable {
        JsonRpcHttpClient client = new JsonRpcHttpClient(
			new URL("http://localhost:8080/"));
        Map<String, Object> params = new HashMap<>();
        params.put("seconds", 10);
        int result = client.invoke("sleep", params, 
		             Integer.class);
        System.out.println("Result: " + result);
    }
}