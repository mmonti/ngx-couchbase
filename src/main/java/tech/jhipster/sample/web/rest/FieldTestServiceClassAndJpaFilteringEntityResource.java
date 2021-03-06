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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.sample.domain.FieldTestServiceClassAndJpaFilteringEntity;
import tech.jhipster.sample.service.FieldTestServiceClassAndJpaFilteringEntityService;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.FieldTestServiceClassAndJpaFilteringEntity}.
 */
@RestController
@RequestMapping("/api")
public class FieldTestServiceClassAndJpaFilteringEntityResource {

    private final Logger log = LoggerFactory.getLogger(FieldTestServiceClassAndJpaFilteringEntityResource.class);

    private static final String ENTITY_NAME = "fieldTestServiceClassAndJpaFilteringEntity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FieldTestServiceClassAndJpaFilteringEntityService fieldTestServiceClassAndJpaFilteringEntityService;

    public FieldTestServiceClassAndJpaFilteringEntityResource(
        FieldTestServiceClassAndJpaFilteringEntityService fieldTestServiceClassAndJpaFilteringEntityService
    ) {
        this.fieldTestServiceClassAndJpaFilteringEntityService = fieldTestServiceClassAndJpaFilteringEntityService;
    }

    /**
     * {@code POST  /field-test-service-class-and-jpa-filtering-entities} : Create a new fieldTestServiceClassAndJpaFilteringEntity.
     *
     * @param fieldTestServiceClassAndJpaFilteringEntity the fieldTestServiceClassAndJpaFilteringEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fieldTestServiceClassAndJpaFilteringEntity, or with status {@code 400 (Bad Request)} if the fieldTestServiceClassAndJpaFilteringEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/field-test-service-class-and-jpa-filtering-entities")
    public ResponseEntity<FieldTestServiceClassAndJpaFilteringEntity> createFieldTestServiceClassAndJpaFilteringEntity(
        @Valid @RequestBody FieldTestServiceClassAndJpaFilteringEntity fieldTestServiceClassAndJpaFilteringEntity
    ) throws URISyntaxException {
        log.debug("REST request to save FieldTestServiceClassAndJpaFilteringEntity : {}", fieldTestServiceClassAndJpaFilteringEntity);
        if (fieldTestServiceClassAndJpaFilteringEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new fieldTestServiceClassAndJpaFilteringEntity cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        FieldTestServiceClassAndJpaFilteringEntity result = fieldTestServiceClassAndJpaFilteringEntityService.save(
            fieldTestServiceClassAndJpaFilteringEntity
        );
        return ResponseEntity
            .created(new URI("/api/field-test-service-class-and-jpa-filtering-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /field-test-service-class-and-jpa-filtering-entities} : Updates an existing fieldTestServiceClassAndJpaFilteringEntity.
     *
     * @param fieldTestServiceClassAndJpaFilteringEntity the fieldTestServiceClassAndJpaFilteringEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldTestServiceClassAndJpaFilteringEntity,
     * or with status {@code 400 (Bad Request)} if the fieldTestServiceClassAndJpaFilteringEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fieldTestServiceClassAndJpaFilteringEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/field-test-service-class-and-jpa-filtering-entities")
    public ResponseEntity<FieldTestServiceClassAndJpaFilteringEntity> updateFieldTestServiceClassAndJpaFilteringEntity(
        @Valid @RequestBody FieldTestServiceClassAndJpaFilteringEntity fieldTestServiceClassAndJpaFilteringEntity
    ) throws URISyntaxException {
        log.debug("REST request to update FieldTestServiceClassAndJpaFilteringEntity : {}", fieldTestServiceClassAndJpaFilteringEntity);
        if (fieldTestServiceClassAndJpaFilteringEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FieldTestServiceClassAndJpaFilteringEntity result = fieldTestServiceClassAndJpaFilteringEntityService.save(
            fieldTestServiceClassAndJpaFilteringEntity
        );
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldTestServiceClassAndJpaFilteringEntity.getId())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /field-test-service-class-and-jpa-filtering-entities} : Updates given fields of an existing fieldTestServiceClassAndJpaFilteringEntity.
     *
     * @param fieldTestServiceClassAndJpaFilteringEntity the fieldTestServiceClassAndJpaFilteringEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldTestServiceClassAndJpaFilteringEntity,
     * or with status {@code 400 (Bad Request)} if the fieldTestServiceClassAndJpaFilteringEntity is not valid,
     * or with status {@code 404 (Not Found)} if the fieldTestServiceClassAndJpaFilteringEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the fieldTestServiceClassAndJpaFilteringEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/field-test-service-class-and-jpa-filtering-entities", consumes = "application/merge-patch+json")
    public ResponseEntity<FieldTestServiceClassAndJpaFilteringEntity> partialUpdateFieldTestServiceClassAndJpaFilteringEntity(
        @NotNull @RequestBody FieldTestServiceClassAndJpaFilteringEntity fieldTestServiceClassAndJpaFilteringEntity
    ) throws URISyntaxException {
        log.debug(
            "REST request to update FieldTestServiceClassAndJpaFilteringEntity partially : {}",
            fieldTestServiceClassAndJpaFilteringEntity
        );
        if (fieldTestServiceClassAndJpaFilteringEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Optional<FieldTestServiceClassAndJpaFilteringEntity> result = fieldTestServiceClassAndJpaFilteringEntityService.partialUpdate(
            fieldTestServiceClassAndJpaFilteringEntity
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldTestServiceClassAndJpaFilteringEntity.getId())
        );
    }

    /**
     * {@code GET  /field-test-service-class-and-jpa-filtering-entities} : get all the fieldTestServiceClassAndJpaFilteringEntities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fieldTestServiceClassAndJpaFilteringEntities in body.
     */
    @GetMapping("/field-test-service-class-and-jpa-filtering-entities")
    public List<FieldTestServiceClassAndJpaFilteringEntity> getAllFieldTestServiceClassAndJpaFilteringEntities() {
        log.debug("REST request to get all FieldTestServiceClassAndJpaFilteringEntities");
        return fieldTestServiceClassAndJpaFilteringEntityService.findAll();
    }

    /**
     * {@code GET  /field-test-service-class-and-jpa-filtering-entities/:id} : get the "id" fieldTestServiceClassAndJpaFilteringEntity.
     *
     * @param id the id of the fieldTestServiceClassAndJpaFilteringEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fieldTestServiceClassAndJpaFilteringEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/field-test-service-class-and-jpa-filtering-entities/{id}")
    public ResponseEntity<FieldTestServiceClassAndJpaFilteringEntity> getFieldTestServiceClassAndJpaFilteringEntity(
        @PathVariable String id
    ) {
        log.debug("REST request to get FieldTestServiceClassAndJpaFilteringEntity : {}", id);
        Optional<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntity = fieldTestServiceClassAndJpaFilteringEntityService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(fieldTestServiceClassAndJpaFilteringEntity);
    }

    /**
     * {@code DELETE  /field-test-service-class-and-jpa-filtering-entities/:id} : delete the "id" fieldTestServiceClassAndJpaFilteringEntity.
     *
     * @param id the id of the fieldTestServiceClassAndJpaFilteringEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/field-test-service-class-and-jpa-filtering-entities/{id}")
    public ResponseEntity<Void> deleteFieldTestServiceClassAndJpaFilteringEntity(@PathVariable String id) {
        log.debug("REST request to delete FieldTestServiceClassAndJpaFilteringEntity : {}", id);
        fieldTestServiceClassAndJpaFilteringEntityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
