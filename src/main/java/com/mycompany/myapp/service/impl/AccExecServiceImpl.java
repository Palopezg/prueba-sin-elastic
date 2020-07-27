package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.AccExecService;
import com.mycompany.myapp.domain.AccExec;
import com.mycompany.myapp.repository.AccExecRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AccExec}.
 */
@Service
@Transactional
public class AccExecServiceImpl implements AccExecService {

    private final Logger log = LoggerFactory.getLogger(AccExecServiceImpl.class);

    private final AccExecRepository accExecRepository;

    public AccExecServiceImpl(AccExecRepository accExecRepository) {
        this.accExecRepository = accExecRepository;
    }

    @Override
    public AccExec save(AccExec accExec) {
        log.debug("Request to save AccExec : {}", accExec);
        return accExecRepository.save(accExec);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccExec> findAll(Pageable pageable) {
        log.debug("Request to get all AccExecs");
        return accExecRepository.findAll(pageable);
    }


    public Page<AccExec> findAllWithEagerRelationships(Pageable pageable) {
        return accExecRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccExec> findOne(Long id) {
        log.debug("Request to get AccExec : {}", id);
        return accExecRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccExec : {}", id);
        accExecRepository.deleteById(id);
    }
}
