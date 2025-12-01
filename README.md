"# j2024_10_28_json_rpc2" 


curl -X POST http://localhost:8081/jsonrpc -H "Content-Type: application/json" -d '{ "jsonrpc" : 2.0, "method": "add", "params": { "a": 5, "b": 10 }, "id": 99 }'