package Enos.SpringProject.forumAlura.domain.repositories;

import Enos.SpringProject.forumAlura.domain.models.response.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResponseRepository extends JpaRepository<Response,Long> {
}
