package by.patsei.springproject1.repository;

import by.patsei.springproject1.entity.Project;
import by.patsei.springproject1.entity.Task;

import by.patsei.springproject1.entity.User;
import by.patsei.springproject1.entity.nontable.ProjectTaskType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
	
	List<Task> findAllByProject(Project project, Pageable pageable);
	
	@Query(value = "select t.type, count(*)\n" +
			"from project p\n" +
			"         join task t on p.id = t.project_id\n" +
			"where p.id = :id\n" +
			"group by t.type\n" +
			"order by t.type", nativeQuery = true)
	List<ProjectTaskType> findTaskTypesByProject(@Param("id") Long projectId);
	
	List<Task> findAllByCreator(User user, Pageable pageable);
	
}
