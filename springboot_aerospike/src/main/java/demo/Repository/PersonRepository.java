package demo.Repository;

import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface PersonRepository extends AerospikeRepository {
    // List<Person> findByFirstNameAndAge(String string, int i);
}
