# 什么是Server-Sent Events (SSE)

Сlients subscribe to aт events source on a server. If the server has new data, it sends a response to all subscribed clients. It’s possible for the server to close connection and stop events sending as well as for a client to stop events listening. SSE is a one-directional protocol where data can be sent only from server to client.

# 本案例演示内容
+ 客户端向后台(SseController->push)发送异步请求，客户端处于监听等待状态;
+ 支付成功后回调后台(SseController->payCallback模拟);
+ payCallback方法通过applicationContext.publishEvent向系统内部发送支付完成事件;
+ push方法通过payCompletedListener监听事件并通过SseEmitter发送给客户端
