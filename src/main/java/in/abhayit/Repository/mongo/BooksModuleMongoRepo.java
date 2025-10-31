package in.abhayit.Repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.abhayit.Entity.mongo.BooksModuleMongo;

public interface BooksModuleMongoRepo extends MongoRepository<BooksModuleMongo,String>{

}
