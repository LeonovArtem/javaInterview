package al.spring.controller;

import al.spring.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
@RequiredArgsConstructor
public class DemoController {
    private final TelegramService telegramMessage;

    @GetMapping
    public void empty() {

    }
}
