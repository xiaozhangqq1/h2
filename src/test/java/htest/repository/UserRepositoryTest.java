package htest.repository;

import htest.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByName() throws Exception {
        this.entityManager.persist(new User("zhang",1));
        User user = this.userRepository.findByName("zhang");
        assertThat(user.getName()).isEqualTo("zhang");
        assertThat(user.getAge()).isEqualTo(1);

    }
}