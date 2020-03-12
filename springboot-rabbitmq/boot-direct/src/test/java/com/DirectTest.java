package com;

import com.sender.DirectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DirectTest {
    @Autowired
    private DirectSender directSender;

    @Test
    public void test() {
        directSender.send(10);
        directSender.send(1);
    }
}
