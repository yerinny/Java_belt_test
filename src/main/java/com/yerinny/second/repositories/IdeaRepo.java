package com.yerinny.second.repositories;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yerinny.second.models.Idea;


@Repository
public interface IdeaRepo extends CrudRepository<Idea, Long> {
	 
}
