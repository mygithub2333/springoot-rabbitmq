package com.example.springootRabbitmq.controller;

import com.example.springootRabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fwx
 * @date 2022/3/22
 */
@RestController
public class DemoController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/test")
    public String test(){
        for (int i=0;i<5;i++){
            String message = "恭喜您，注册成功！userId="+i;
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME,"topic.sms.email",message);
            System.out.println(" [x] Sent '"+message+"'");
        }
        return "success";
    }

}
