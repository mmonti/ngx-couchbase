package tech.jhipster.sample.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.jhipster.sample.domain.EntityWithServiceImplAndDTO;
import tech.jhipster.sample.repository.EntityWithServiceImplAndDTORepository;
import tech.jhipster.sample.service.EntityWithServiceImplAndDTOService;
import tech.jhipster.sample.service.dto.EntityWithServiceImplAndDTODTO;
import tech.jhipster.sample.service.mapper.EntityWithServiceImplAndDTOMapper;

/**
 * Service Implementation for managing {@link EntityWithServiceImplAndDTO}.
 */
@Service
public class EntityWithServiceImplAndDTOServiceImpl implements EntityWithServiceImplAndDTOService {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplAndDTOServiceImpl.class);

    private final EntityWithServiceImplAndDTORepository entityWithServiceImplAndDTORepository;

    private final EntityWithServiceImplAndDTOMapper entityWithServiceImplAndDTOMapper;

    public EntityWithServiceImplAndDTOServiceImpl(
        EntityWithServiceImplAndDTORepository entityWithServiceImplAndDTORepository,
        EntityWithServiceImplAndDTOMapper entityWithServiceImplAndDTOMapper
    ) {
        this.entityWithServiceImplAndDTORepository = entityWithServiceImplAndDTORepository;
        this.entityWithServiceImplAndDTOMapper = entityWithServiceImplAndDTOMapper;
    }

    @Override
    public EntityWithServiceImplAndDTODTO save(EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO) {
        log.debug("Request to save EntityWithServiceImplAndDTO : {}", entityWithServiceImplAndDTODTO);
        EntityWithServiceImplAndDTO entityWithServiceImplAndDTO = entityWithServiceImplAndDTOMapper.toEntity(
            entityWithServiceImplAndDTODTO
        );
        entityWithServiceImplAndDTO = entityWithServiceImplAndDTORepository.save(entityWithServiceImplAndDTO);
        return entityWithServiceImplAndDTOMapper.toDto(entityWithServiceImplAndDTO);
    }

    @Override
    public Optional<EntityWithServiceImplAndDTODTO> partialUpdate(EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO) {
        log.debug("Request to partially update EntityWithServiceImplAndDTO : {}", entityWithServiceImplAndDTODTO);

        return entityWithServiceImplAndDTORepository
            .findById(entityWithServiceImplAndDTODTO.getId())
            .map(
                existingEntityWithServiceImplAndDTO -> {
                    if (entityWithServiceImplAndDTODTO.getLouis() != null) {
                        existingEntityWithServiceImplAndDTO.setLouis(entityWithServiceImplAndDTODTO.getLouis());
                    }

                    return existingEntityWithServiceImplAndDTO;
                }
            )
            .map(entityWithServiceImplAndDTORepository::save)
            .map(entityWithServiceImplAndDTOMapper::toDto);
    }

    @Override
    public List<EntityWithServiceImplAndDTODTO> findAll() {
        log.debug("Request to get all EntityWithServiceImplAndDTOS");
        return entityWithServiceImplAndDTORepository
            .findAll()
            .stream()
            .map(entityWithServiceImplAndDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<EntityWithServiceImplAndDTODTO> findOne(String id) {
        log.debug("Request to get EntityWithServiceImplAndDTO : {}", id);
        return entityWithServiceImplAndDTORepository.findById(id).map(entityWithServiceImplAndDTOMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete EntityWithServiceImplAndDTO : {}", id);
        entityWithServiceImplAndDTORepository.deleteById(id);
    }
}
