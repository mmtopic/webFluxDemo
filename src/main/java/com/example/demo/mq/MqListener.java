package com.example.demo.mq;

import cn.hutool.Hutool;
import cn.hutool.json.JSONUtil;
import com.example.demo.bean.DataStruct;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MqListener {
    @RabbitListener(queues = "info_queue")
    public void msg(String msg){
        System.out.println("消费者消费消息了："+msg);
        DataStruct ds = JSONUtil.toBean(msg,DataStruct.class);
        System.out.println(ds);
    }
}
