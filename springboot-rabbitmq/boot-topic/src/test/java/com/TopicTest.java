package com;

import com.sender.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TopicTest {
    @Autowired
    private TopicSender topicSender;

    @Test
    public void test() {
        //topicSender.send("xj.x","开播了");

        topicSender.send("x","开播了没有");
    }
}
