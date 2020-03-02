package singispace.service.CRUD;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import singispace.domain.Learner;
import singispace.repository.users.LearnerRepository;


@Service
public class LearnerCRUDService {

	@Autowired
	private LearnerRepository learnerRepository;

	public LearnerCRUDService() {
	}

	public Iterable<Learner> getLearners() {
		return learnerRepository.findAll();
	}

	public Optional<Learner> getLearnerById(Long id) {
		return learnerRepository.findById(id);
	}

	public void addLearner(Learner learner) {
		learnerRepository.save(learner);
	}

	public void removeLearner(Long id) {
		Optional<Learner> learner = learnerRepository.findById(id);
		learnerRepository.delete(learner.get());
	}

	public void updateLearner(Long id, Learner learner) {
		Optional<Learner> old_learner = learnerRepository.findById(id);
		if (old_learner.isPresent()) {
			learner.setId(old_learner.get().getId());
			learnerRepository.save(learner);
		}
	}
	
	
}
