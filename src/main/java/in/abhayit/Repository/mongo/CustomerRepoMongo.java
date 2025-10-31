package in.abhayit.Repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.abhayit.Entity.mongo.CustomerMongo;

public interface CustomerRepoMongo extends MongoRepository<CustomerMongo, String>{

}
