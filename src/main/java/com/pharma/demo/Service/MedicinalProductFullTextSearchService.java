package com.pharma.demo.Service;

import com.pharma.demo.Entity.MedicinalProduct;
import com.pharma.demo.Repository.MedicinalProductFullTextSearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.CacheMode;
import org.hibernate.search.batchindexing.MassIndexerProgressMonitor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MedicinalProductFullTextSearchService {
    private MedicinalProductFullTextSearchRepository medicinalProductFullTextSearchRepository;
    private EntityManager entityManager;

    @Autowired
    public MedicinalProductFullTextSearchService(MedicinalProductFullTextSearchRepository medicinalProductFullTextSearchRepository,
                                                 EntityManager entityManager) {
        this.medicinalProductFullTextSearchRepository = medicinalProductFullTextSearchRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public void createIndexerStartAndAwait() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        MassIndexerProgressMonitor monitor = new LoggingProgressMonitor();

        long startTime = System.nanoTime();
        fullTextEntityManager
                .createIndexer(MedicinalProduct.class)
                .batchSizeToLoadObjects(25)
                .cacheMode(CacheMode.NORMAL)
                .threadsToLoadObjects(12)
                .idFetchSize(150)
                .transactionTimeout(10)
                .progressMonitor(monitor)
                .startAndWait();
        long importTimeInSeconds = (System.nanoTime() - startTime) / 1000000;
        log.info("Hibernate Search indexing finished, Hibernate Search indexing lasted: {} ms", importTimeInSeconds);

        log.info("Reindexing lasted: {} ms", monitor);
    }

    public Page<?> search(String searchText, Pageable pageable) {
        return medicinalProductFullTextSearchRepository.profileFullTextSearch(searchText, pageable);
    }


    static class LoggingProgressMonitor implements MassIndexerProgressMonitor {
        private int count = 0;
        private int documents = 0;
        private int indexedEntities = 0;

        @Override
        public void documentsBuilt(int increment) {
            documents += increment;
            log.info("Built {} indexing documents", documents);
        }

        @Override
        public void entitiesLoaded(int increment) {
            count += increment;
            log.info("Loaded {} entities from the database so far", count);
        }

        @Override
        public void addToTotalCount(long increment) {
            indexedEntities += increment;
            log.info("Indexed {} entities so far", indexedEntities);
        }

        @Override
        public void indexingCompleted() {
            log.info("Completed indexing");
        }

        @Override
        public void documentsAdded(long increment) {
            documents += increment;
            log.info("Added {} documents to the index", increment);
        }
    }


}
