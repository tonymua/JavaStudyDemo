package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;

/**
 * @author:
 * @date: created in 12:50 2020/8/9
 * @version:
 */
@Configuration
@EnableAerospikeRepositories
public class TestConfigPerson {
    public @Bean(destroyMethod = "close") AerospikeClient aerospikeClient() {
        ClientPolicy policy = new ClientPolicy();
        policy.failIfNotConnected = true;
        policy.timeout = 2000;

        return new AerospikeClient(policy, "xxxxx", 3000);
    }
    public @Bean
    AerospikeTemplate aerospikeTemplate(){
        return new AerospikeTemplate(aerospikeClient(),"testMemory");
    }
}