package in.abhayit.Repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.abhayit.Entity.mongo.FilesEntityMongo;

public interface FileRepoMongo extends MongoRepository<FilesEntityMongo, String>{

}
