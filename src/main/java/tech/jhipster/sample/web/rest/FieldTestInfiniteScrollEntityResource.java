package tech.jhipster.sample.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.sample.domain.FieldTestInfiniteScrollEntity;
import tech.jhipster.sample.repository.FieldTestInfiniteScrollEntityRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.FieldTestInfiniteScrollEntity}.
 */
@RestController
@RequestMapping("/api")
public class FieldTestInfiniteScrollEntityResource {

    private final Logger log = LoggerFactory.getLogger(FieldTestInfiniteScrollEntityResource.class);

    private static final String ENTITY_NAME = "fieldTestInfiniteScrollEntity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FieldTestInfiniteScrollEntityRepository fieldTestInfiniteScrollEntityRepository;

    public FieldTestInfiniteScrollEntityResource(FieldTestInfiniteScrollEntityRepository fieldTestInfiniteScrollEntityRepository) {
        this.fieldTestInfiniteScrollEntityRepository = fieldTestInfiniteScrollEntityRepository;
    }

    /**
     * {@code POST  /field-test-infinite-scroll-entities} : Create a new fieldTestInfiniteScrollEntity.
     *
     * @param fieldTestInfiniteScrollEntity the fieldTestInfiniteScrollEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fieldTestInfiniteScrollEntity, or with status {@code 400 (Bad Request)} if the fieldTestInfiniteScrollEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/field-test-infinite-scroll-entities")
    public ResponseEntity<FieldTestInfiniteScrollEntity> createFieldTestInfiniteScrollEntity(
        @Valid @RequestBody FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity
    ) throws URISyntaxException {
        log.debug("REST request to save FieldTestInfiniteScrollEntity : {}", fieldTestInfiniteScrollEntity);
        if (fieldTestInfiniteScrollEntity.getId() != null) {
            throw new BadRequestAlertException("A new fieldTestInfiniteScrollEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FieldTestInfiniteScrollEntity result = fieldTestInfiniteScrollEntityRepository.save(fieldTestInfiniteScrollEntity);
        return ResponseEntity
            .created(new URI("/api/field-test-infinite-scroll-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /field-test-infinite-scroll-entities} : Updates an existing fieldTestInfiniteScrollEntity.
     *
     * @param fieldTestInfiniteScrollEntity the fieldTestInfiniteScrollEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldTestInfiniteScrollEntity,
     * or with status {@code 400 (Bad Request)} if the fieldTestInfiniteScrollEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fieldTestInfiniteScrollEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/field-test-infinite-scroll-entities")
    public ResponseEntity<FieldTestInfiniteScrollEntity> updateFieldTestInfiniteScrollEntity(
        @Valid @RequestBody FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity
    ) throws URISyntaxException {
        log.debug("REST request to update FieldTestInfiniteScrollEntity : {}", fieldTestInfiniteScrollEntity);
        if (fieldTestInfiniteScrollEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FieldTestInfiniteScrollEntity result = fieldTestInfiniteScrollEntityRepository.save(fieldTestInfiniteScrollEntity);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldTestInfiniteScrollEntity.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /field-test-infinite-scroll-entities} : Updates given fields of an existing fieldTestInfiniteScrollEntity.
     *
     * @param fieldTestInfiniteScrollEntity the fieldTestInfiniteScrollEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldTestInfiniteScrollEntity,
     * or with status {@code 400 (Bad Request)} if the fieldTestInfiniteScrollEntity is not valid,
     * or with status {@code 404 (Not Found)} if the fieldTestInfiniteScrollEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the fieldTestInfiniteScrollEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/field-test-infinite-scroll-entities", consumes = "application/merge-patch+json")
    public ResponseEntity<FieldTestInfiniteScrollEntity> partialUpdateFieldTestInfiniteScrollEntity(
        @NotNull @RequestBody FieldTestInfiniteScrollEntity fieldTestInfiniteScrollEntity
    ) throws URISyntaxException {
        log.debug("REST request to update FieldTestInfiniteScrollEntity partially : {}", fieldTestInfiniteScrollEntity);
        if (fieldTestInfiniteScrollEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Optional<FieldTestInfiniteScrollEntity> result = fieldTestInfiniteScrollEntityRepository
            .findById(fieldTestInfiniteScrollEntity.getId())
            .map(
                existingFieldTestInfiniteScrollEntity -> {
                    if (fieldTestInfiniteScrollEntity.getStringHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setStringHugo(fieldTestInfiniteScrollEntity.getStringHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getStringRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setStringRequiredHugo(fieldTestInfiniteScrollEntity.getStringRequiredHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getStringMinlengthHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setStringMinlengthHugo(
                            fieldTestInfiniteScrollEntity.getStringMinlengthHugo()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getStringMaxlengthHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setStringMaxlengthHugo(
                            fieldTestInfiniteScrollEntity.getStringMaxlengthHugo()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getStringPatternHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setStringPatternHugo(fieldTestInfiniteScrollEntity.getStringPatternHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getIntegerHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setIntegerHugo(fieldTestInfiniteScrollEntity.getIntegerHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getIntegerRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setIntegerRequiredHugo(
                            fieldTestInfiniteScrollEntity.getIntegerRequiredHugo()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getIntegerMinHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setIntegerMinHugo(fieldTestInfiniteScrollEntity.getIntegerMinHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getIntegerMaxHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setIntegerMaxHugo(fieldTestInfiniteScrollEntity.getIntegerMaxHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getLongHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setLongHugo(fieldTestInfiniteScrollEntity.getLongHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getLongRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setLongRequiredHugo(fieldTestInfiniteScrollEntity.getLongRequiredHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getLongMinHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setLongMinHugo(fieldTestInfiniteScrollEntity.getLongMinHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getLongMaxHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setLongMaxHugo(fieldTestInfiniteScrollEntity.getLongMaxHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getFloatHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setFloatHugo(fieldTestInfiniteScrollEntity.getFloatHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getFloatRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setFloatRequiredHugo(fieldTestInfiniteScrollEntity.getFloatRequiredHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getFloatMinHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setFloatMinHugo(fieldTestInfiniteScrollEntity.getFloatMinHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getFloatMaxHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setFloatMaxHugo(fieldTestInfiniteScrollEntity.getFloatMaxHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getDoubleRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setDoubleRequiredHugo(fieldTestInfiniteScrollEntity.getDoubleRequiredHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getDoubleMinHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setDoubleMinHugo(fieldTestInfiniteScrollEntity.getDoubleMinHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getDoubleMaxHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setDoubleMaxHugo(fieldTestInfiniteScrollEntity.getDoubleMaxHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getBigDecimalRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setBigDecimalRequiredHugo(
                            fieldTestInfiniteScrollEntity.getBigDecimalRequiredHugo()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getBigDecimalMinHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setBigDecimalMinHugo(fieldTestInfiniteScrollEntity.getBigDecimalMinHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getBigDecimalMaxHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setBigDecimalMaxHugo(fieldTestInfiniteScrollEntity.getBigDecimalMaxHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getLocalDateHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setLocalDateHugo(fieldTestInfiniteScrollEntity.getLocalDateHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getLocalDateRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setLocalDateRequiredHugo(
                            fieldTestInfiniteScrollEntity.getLocalDateRequiredHugo()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getInstantHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setInstantHugo(fieldTestInfiniteScrollEntity.getInstantHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getInstanteRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setInstanteRequiredHugo(
                            fieldTestInfiniteScrollEntity.getInstanteRequiredHugo()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getZonedDateTimeHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setZonedDateTimeHugo(fieldTestInfiniteScrollEntity.getZonedDateTimeHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getZonedDateTimeRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setZonedDateTimeRequiredHugo(
                            fieldTestInfiniteScrollEntity.getZonedDateTimeRequiredHugo()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getDurationHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setDurationHugo(fieldTestInfiniteScrollEntity.getDurationHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getDurationRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setDurationRequiredHugo(
                            fieldTestInfiniteScrollEntity.getDurationRequiredHugo()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getBooleanHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setBooleanHugo(fieldTestInfiniteScrollEntity.getBooleanHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getBooleanRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setBooleanRequiredHugo(
                            fieldTestInfiniteScrollEntity.getBooleanRequiredHugo()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getEnumHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setEnumHugo(fieldTestInfiniteScrollEntity.getEnumHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getEnumRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setEnumRequiredHugo(fieldTestInfiniteScrollEntity.getEnumRequiredHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getUuidHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setUuidHugo(fieldTestInfiniteScrollEntity.getUuidHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getUuidRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setUuidRequiredHugo(fieldTestInfiniteScrollEntity.getUuidRequiredHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getByteImageHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteImageHugo(fieldTestInfiniteScrollEntity.getByteImageHugo());
                    }
                    if (fieldTestInfiniteScrollEntity.getByteImageHugoContentType() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteImageHugoContentType(
                            fieldTestInfiniteScrollEntity.getByteImageHugoContentType()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getByteImageRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteImageRequiredHugo(
                            fieldTestInfiniteScrollEntity.getByteImageRequiredHugo()
                        );
                    }
                    if (fieldTestInfiniteScrollEntity.getByteImageRequiredHugoContentType() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteImageRequiredHugoContentType(
                            fieldTestInfiniteScrollEntity.getByteImageRequiredHugoContentType()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getByteImageMinbytesHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteImageMinbytesHugo(
                            fieldTestInfiniteScrollEntity.getByteImageMinbytesHugo()
                        );
                    }
                    if (fieldTestInfiniteScrollEntity.getByteImageMinbytesHugoContentType() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteImageMinbytesHugoContentType(
                            fieldTestInfiniteScrollEntity.getByteImageMinbytesHugoContentType()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getByteImageMaxbytesHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteImageMaxbytesHugo(
                            fieldTestInfiniteScrollEntity.getByteImageMaxbytesHugo()
                        );
                    }
                    if (fieldTestInfiniteScrollEntity.getByteImageMaxbytesHugoContentType() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteImageMaxbytesHugoContentType(
                            fieldTestInfiniteScrollEntity.getByteImageMaxbytesHugoContentType()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getByteAnyHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteAnyHugo(fieldTestInfiniteScrollEntity.getByteAnyHugo());
                    }
                    if (fieldTestInfiniteScrollEntity.getByteAnyHugoContentType() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteAnyHugoContentType(
                            fieldTestInfiniteScrollEntity.getByteAnyHugoContentType()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getByteAnyRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteAnyRequiredHugo(
                            fieldTestInfiniteScrollEntity.getByteAnyRequiredHugo()
                        );
                    }
                    if (fieldTestInfiniteScrollEntity.getByteAnyRequiredHugoContentType() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteAnyRequiredHugoContentType(
                            fieldTestInfiniteScrollEntity.getByteAnyRequiredHugoContentType()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getByteAnyMinbytesHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteAnyMinbytesHugo(
                            fieldTestInfiniteScrollEntity.getByteAnyMinbytesHugo()
                        );
                    }
                    if (fieldTestInfiniteScrollEntity.getByteAnyMinbytesHugoContentType() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteAnyMinbytesHugoContentType(
                            fieldTestInfiniteScrollEntity.getByteAnyMinbytesHugoContentType()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getByteAnyMaxbytesHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteAnyMaxbytesHugo(
                            fieldTestInfiniteScrollEntity.getByteAnyMaxbytesHugo()
                        );
                    }
                    if (fieldTestInfiniteScrollEntity.getByteAnyMaxbytesHugoContentType() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteAnyMaxbytesHugoContentType(
                            fieldTestInfiniteScrollEntity.getByteAnyMaxbytesHugoContentType()
                        );
                    }

                    if (fieldTestInfiniteScrollEntity.getByteTextHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteTextHugo(fieldTestInfiniteScrollEntity.getByteTextHugo());
                    }

                    if (fieldTestInfiniteScrollEntity.getByteTextRequiredHugo() != null) {
                        existingFieldTestInfiniteScrollEntity.setByteTextRequiredHugo(
                            fieldTestInfiniteScrollEntity.getByteTextRequiredHugo()
                        );
                    }

                    return existingFieldTestInfiniteScrollEntity;
                }
            )
            .map(fieldTestInfiniteScrollEntityRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldTestInfiniteScrollEntity.getId())
        );
    }

    /**
     * {@code GET  /field-test-infinite-scroll-entities} : get all the fieldTestInfiniteScrollEntities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fieldTestInfiniteScrollEntities in body.
     */
    @GetMapping("/field-test-infinite-scroll-entities")
    public ResponseEntity<List<FieldTestInfiniteScrollEntity>> getAllFieldTestInfiniteScrollEntities(Pageable pageable) {
        log.debug("REST request to get a page of FieldTestInfiniteScrollEntities");
        Page<FieldTestInfiniteScrollEntity> page = fieldTestInfiniteScrollEntityRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /field-test-infinite-scroll-entities/:id} : get the "id" fieldTestInfiniteScrollEntity.
     *
     * @param id the id of the fieldTestInfiniteScrollEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fieldTestInfiniteScrollEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/field-test-infinite-scroll-entities/{id}")
    public ResponseEntity<FieldTestInfiniteScrollEntity> getFieldTestInfiniteScrollEntity(@PathVariable String id) {
        log.debug("REST request to get FieldTestInfiniteScrollEntity : {}", id);
        Optional<FieldTestInfiniteScrollEntity> fieldTestInfiniteScrollEntity = fieldTestInfiniteScrollEntityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fieldTestInfiniteScrollEntity);
    }

    /**
     * {@code DELETE  /field-test-infinite-scroll-entities/:id} : delete the "id" fieldTestInfiniteScrollEntity.
     *
     * @param id the id of the fieldTestInfiniteScrollEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/field-test-infinite-scroll-entities/{id}")
    public ResponseEntity<Void> deleteFieldTestInfiniteScrollEntity(@PathVariable String id) {
        log.debug("REST request to delete FieldTestInfiniteScrollEntity : {}", id);
        fieldTestInfiniteScrollEntityRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
