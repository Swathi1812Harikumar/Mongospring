package springcom.mongo.mongospring.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import springcom.mongo.mongospring.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
