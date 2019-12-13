package singispace.service.CRUD;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import singispace.domain.Tutor;
import singispace.repository.users.Tutor_Repository;


@Service
public class Tutor_CRUD_Service {

	@Autowired
	private Tutor_Repository tutorRepository;

	public Tutor_CRUD_Service() {
	}

	public Iterable<Tutor> getTutors() {
		return tutorRepository.findAll();
	}

	public Optional<Tutor> getTutorById(Long id) {
		return tutorRepository.findById(id);
	}

	public void addTutor(Tutor tutor) {
		tutorRepository.save(tutor);
	}

	public void removeTutor(Long id) {
		Optional<Tutor> tutor = tutorRepository.findById(id);
		tutorRepository.delete(tutor.get());
	}

	public void updateTutor(Long id, Tutor tutor) {
		Optional<Tutor> old_tutor = tutorRepository.findById(id);
		if (old_tutor.isPresent()) {
			tutor.setId(old_tutor.get().getId());
			tutorRepository.save(tutor);
		}
	}
	
	
}
