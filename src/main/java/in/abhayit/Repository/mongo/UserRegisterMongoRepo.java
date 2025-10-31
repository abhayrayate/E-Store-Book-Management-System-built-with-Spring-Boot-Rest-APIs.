package in.abhayit.Repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.abhayit.Entity.mongo.UserRegisterMongo;

public interface UserRegisterMongoRepo extends MongoRepository<UserRegisterMongo,String>{

	
	
}