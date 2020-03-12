package com.tx;

import com.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Send2 {
    private static final String QUEUE_NAME = "queue_confirm1";

    public static void main(String[] args) {
        try (Connection connection = ConnectionUtils.getConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.confirmSelect();
            //未确认的标识都在这里面
            SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());
            //通道添加监听
            channel.addConfirmListener(new ConfirmListener() {
                //没有问题的
                @Override
                public void handleAck(long l, boolean b) throws IOException {
                    if(b) {
                        confirmSet.headSet(l+1).clear();
                    }else {
                        confirmSet.remove(l);
                    }
                }
                //回执有问题的
                @Override
                public void handleNack(long l, boolean b) throws IOException {
                    if(b) {
                        confirmSet.headSet(l+1).clear();
                    }else {
                        confirmSet.remove(l);
                    }
                }
            });
            String msg = "hello yiBu";
            while (true) {
                long seqNo = channel.getNextPublishSeqNo();
                channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
                confirmSet.add(seqNo);
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
