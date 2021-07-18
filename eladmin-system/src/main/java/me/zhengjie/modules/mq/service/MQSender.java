package me.zhengjie.modules.mq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void test(){
        rabbitTemplate.convertAndSend("queue","hello world");
    }

    public void delayTest(){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("x-delay",40000);//延迟5秒被删除
        String msgId = UUID.randomUUID().toString();

        Message message = MessageBuilder.withBody("delay msg".getBytes())
                .setHeader("x-delay",40000)
        .setContentType(MessageProperties.CONTENT_TYPE_BYTES).setCorrelationId(msgId)
                .build();
        /*将 msgId和 CorrelationData绑定*/
        CorrelationData correlationData = new CorrelationData(msgId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("【插件延迟队列开始】【" + sdf.format(new Date()) + "】收到消息：");
        rabbitTemplate.convertAndSend("PLUGIN_DELAY_EXCHANGE","delay",message,correlationData);
    }
}
