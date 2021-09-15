package com.communication.schedulingorder;

import com.communication.config.PostgresqlContainerCustom;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchedulingOrderResourceTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainerCustom.getInstance();

    @Test
    public void testCreateSchedulingOrder(){
        assertThat(2).isEqualTo(2);
    }

}
