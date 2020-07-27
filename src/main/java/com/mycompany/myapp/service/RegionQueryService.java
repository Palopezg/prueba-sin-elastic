package com.mycompany.myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.mycompany.myapp.domain.Region;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.RegionRepository;
import com.mycompany.myapp.service.dto.RegionCriteria;

/**
 * Service for executing complex queries for {@link Region} entities in the database.
 * The main input is a {@link RegionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Region} or a {@link Page} of {@link Region} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RegionQueryService extends QueryService<Region> {

    private final Logger log = LoggerFactory.getLogger(RegionQueryService.class);

    private final RegionRepository regionRepository;

    public RegionQueryService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    /**
     * Return a {@link List} of {@link Region} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Region> findByCriteria(RegionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Region> specification = createSpecification(criteria);
        return regionRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Region} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Region> findByCriteria(RegionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Region> specification = createSpecification(criteria);
        return regionRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RegionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Region> specification = createSpecification(criteria);
        return regionRepository.count(specification);
    }

    /**
     * Function to convert {@link RegionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Region> createSpecification(RegionCriteria criteria) {
        Specification<Region> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Region_.id));
            }
            if (criteria.getDescripcion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescripcion(), Region_.descripcion));
            }
            if (criteria.getValor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValor(), Region_.valor));
            }
            if (criteria.getAccExecId() != null) {
                specification = specification.and(buildSpecification(criteria.getAccExecId(),
                    root -> root.join(Region_.accExecs, JoinType.LEFT).get(AccExec_.id)));
            }
        }
        return specification;
    }
}
