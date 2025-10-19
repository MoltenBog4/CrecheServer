package za.co.creche_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.creche_server.model.Child;
import za.co.creche_server.model.User;
import za.co.creche_server.repository.ChildRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;

    public Child addChild(Child child) {
        return childRepository.save(child);
    }

    public List<Child> getChildrenForUser(User parent) {
        return childRepository.findByParent(parent);
    }
}
