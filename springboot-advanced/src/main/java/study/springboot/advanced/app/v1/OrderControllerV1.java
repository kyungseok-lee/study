package study.springboot.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.springboot.advanced.trace.TraceStatus;
import study.springboot.advanced.trace.hellotrace.HelloTraceV1;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus status = trace.begin("OrderController.request()");

        try {
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
