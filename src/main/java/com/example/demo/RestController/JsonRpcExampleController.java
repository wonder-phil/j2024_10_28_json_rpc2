package com.example.demo.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.*;


@RestController
public class JsonRpcExampleController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/jsonrpc")
    public ResponseEntity<JsonNode> handleJsonRpcRequest(@RequestBody JsonNode request) {
        String method = request.get("method").asText();
        JsonNode params = request.get("params");
        int id = request.get("id").asInt();

        JsonNode result;
        try {
            // Handle different methods
            switch (method) {
                case "getUser":
                    result = getUser(params);
                    break;
                case "add":
                    System.out.println("add");
                    result = objectMapper.createObjectNode().put("name", "John Doe").put("id", 99);
                    break;
                // Add more methods here
                default:
                    return createErrorResponse(id, "Method not found", -32601);
            }
            return createSuccessResponse(id, result);

        } catch (Exception e) {
            return createErrorResponse(id, "Internal error", -32603);
        }
    }

    private JsonNode getUser(JsonNode params) {
        // Example of processing parameters and creating a response
        String userId = params.get("userId").asText();
        // ... retrieve user information
        return objectMapper.createObjectNode().put("name", "John Doe").put("id", userId);
    }

    private ResponseEntity<JsonNode> createSuccessResponse(int id, JsonNode result) {
        JsonNode response = objectMapper.createObjectNode()
                .put("jsonrpc", "2.0")
                .put("id", id)
                .put("result", result);
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<JsonNode> createErrorResponse(int id, String message, int code) {
        JsonNode error = objectMapper.createObjectNode()
                .put("code", code)
                .put("message", message);
        JsonNode response = objectMapper.createObjectNode()
                .put("jsonrpc", "2.0")
                .put("id", id)
                .put("error", error);
        return ResponseEntity.ok(response);
    }
}
