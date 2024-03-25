package com.example.rosatom.repository;

import com.example.rosatom.entity.Topic;
import com.example.rosatom.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
    public boolean existsTopicByTitle(String title);
    public  boolean existsTopicByUsersIdAndId(Long user,Long id);
}
