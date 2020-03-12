package com.confirm;

import com.ConnectionUtils;
        import com.rabbitmq.client.Channel;
        import com.rabbitmq.client.Connection;

        import java.io.IOException;
        import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Send {
    private static final String QUEUE_NAME = "queue_confirm";

    public static void main(String[] args) {
        try(Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //生产者调用confirmSelect 将channel设置成confirm模式 注，事务机制不能与confirm模式共用
            channel.confirmSelect();
            String msg = "hello confirm";
            //注批量就是循环发送后再确认
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            if(!channel.waitForConfirms()){
                System.out.println("发送失败");
            } else {
                System.out.println("发送成功");
            }
        }catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
