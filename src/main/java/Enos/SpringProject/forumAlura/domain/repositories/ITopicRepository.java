package Enos.SpringProject.forumAlura.domain.repositories;

import Enos.SpringProject.forumAlura.domain.models.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<Topic,Long> {

    Topic findByTitleAndMessageAndActive(String title, String message, Integer active);

    Topic findByIdAndActive(Long id, Integer active);

    Page<Topic> findByActive(Integer active, Pageable pageable);
}
