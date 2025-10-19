package za.co.creche_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.creche_server.model.Child;
import za.co.creche_server.model.User;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> findByParent(User parent); // ✅ This is the missing method
}
