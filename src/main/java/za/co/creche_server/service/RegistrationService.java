package za.co.creche_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.creche_server.dao.ChildDao;
import za.co.creche_server.dao.ParentDao;
import za.co.creche_server.dto.ChildRegistrationRequest;
import za.co.creche_server.dto.ChildResponse;
import za.co.creche_server.dto.ParentRegistrationRequest;
import za.co.creche_server.dto.ParentResponse;
import za.co.creche_server.dto.ParentSummary;
import za.co.creche_server.dto.ChildSummary;
import za.co.creche_server.model.Child;
import za.co.creche_server.model.Parent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final ParentDao parentDao;
    private final ChildDao childDao;

    /** Create a parent with one or more children and return the saved representation. */
    @Transactional
    public ParentResponse registerParentAndChildren(ParentRegistrationRequest req) {
        // Optional: basic duplicate check (requires ParentDao.existsByEmail)
        if (req.getEmail() != null && parentDaoExistsByEmail()) {
            if (parentDao.existsByEmail(req.getEmail())) {
                throw new IllegalArgumentException("A parent with this email already exists: " + req.getEmail());
            }
        }

        Parent p = new Parent();
        p.setFirstName(req.getFirstName());
        p.setLastName(req.getLastName());
        p.setIdNumber(req.getIdNumber());
        p.setCellNumber(req.getCellNumber());
        p.setEmail(req.getEmail());
        p.setAddress(req.getAddress());

        Long parentId = parentDao.insert(p);

        List<ChildResponse> childResponses = new ArrayList<>();
        if (req.getChildren() != null) {
            for (ChildRegistrationRequest cReq : req.getChildren()) {
                Child c = new Child();
                c.setFirstName(cReq.getFirstName());
                c.setLastName(cReq.getLastName());
                c.setDateOfBirth(cReq.getDateOfBirth()); // "YYYY-MM-DD"
                c.setGender(cReq.getGender());
                c.setClassGroup(cReq.getClassGroup());
                c.setAllergies(cReq.getAllergies());
                c.setMedicalNotes(cReq.getMedicalNotes());
                c.setParentId(parentId);

                Long id = childDao.insert(c);
                childResponses.add(new ChildResponse(
                        id, c.getFirstName(), c.getLastName(), c.getDateOfBirth(),
                        c.getGender(), c.getClassGroup(), c.getAllergies(), c.getMedicalNotes()
                ));
            }
        }

        return new ParentResponse(
                parentId,
                p.getFirstName(),
                p.getLastName(),
                p.getIdNumber(),
                p.getCellNumber(),
                p.getEmail(),
                p.getAddress(),
                childResponses
        );
    }

    /** Fetch a parent and all their children. */
    @Transactional(readOnly = true)
    public ParentResponse getParentWithChildren(Long parentId) {
        Parent p = parentDao.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("Parent not found: " + parentId));

        List<ChildResponse> kids = childDao.findByParentId(parentId).stream()
                .map(ch -> new ChildResponse(
                        ch.getId(), ch.getFirstName(), ch.getLastName(),
                        ch.getDateOfBirth(), ch.getGender(), ch.getClassGroup(),
                        ch.getAllergies(), ch.getMedicalNotes()
                ))
                .collect(Collectors.toList());

        return new ParentResponse(
                p.getId(), p.getFirstName(), p.getLastName(),
                p.getIdNumber(), p.getCellNumber(), p.getEmail(), p.getAddress(),
                kids
        );
    }

    /** Add a child to an existing parent. */
    @Transactional
    public ChildResponse addChild(Long parentId, ChildRegistrationRequest req) {
        // Ensure parent exists
        parentDao.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("Parent not found: " + parentId));

        Child c = new Child();
        c.setFirstName(req.getFirstName());
        c.setLastName(req.getLastName());
        c.setDateOfBirth(req.getDateOfBirth());
        c.setGender(req.getGender());
        c.setClassGroup(req.getClassGroup());
        c.setAllergies(req.getAllergies());
        c.setMedicalNotes(req.getMedicalNotes());
        c.setParentId(parentId);

        Long id = childDao.insert(c);
        return new ChildResponse(
                id, c.getFirstName(), c.getLastName(), c.getDateOfBirth(),
                c.getGender(), c.getClassGroup(), c.getAllergies(), c.getMedicalNotes()
        );
    }

    /** Delete a parent (children will be removed by FK cascade if configured). */
    @Transactional
    public void deleteParent(Long parentId) {
        parentDao.deleteById(parentId);
    }

    /** List all parents (summary view). */
    @Transactional(readOnly = true)
    public List<ParentSummary> listParents() {
        return parentDao.findAll().stream()
                .map(p -> new ParentSummary(
                        p.getId(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getEmail(),
                        p.getCellNumber()
                ))
                .collect(Collectors.toList());
    }

    /** List all children (summary view). */
    @Transactional(readOnly = true)
    public List<ChildSummary> listChildren() {
        return childDao.findAll().stream()
                .map(c -> new ChildSummary(
                        c.getId(),
                        c.getFirstName(),
                        c.getLastName(),
                        c.getDateOfBirth(),
                        c.getGender(),
                        c.getClassGroup(),
                        c.getParentId()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Helper to avoid compile errors if you haven’t added existsByEmail yet.
     * Remove this method and direct-call parentDao.existsByEmail(...) once that method definitely exists.
     */
    private boolean parentDaoExistsByEmail() {
        try {
            parentDao.getClass().getMethod("existsByEmail", String.class);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
