package com.pharma.demo.Repository;

import com.pharma.demo.Entity.MedicinalProduct;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

@Repository
public class MedicinalProductFullTextSearchRepository {

    private EntityManager entityManager;

    @Autowired
    MedicinalProductFullTextSearchRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Page<MedicinalProduct> profileFullTextSearch(String searchText, Pageable pageable) {
        searchText = searchText.toLowerCase(Locale.ROOT);

        List<String> searchWords = Arrays
                .stream(searchText.split("[ ]"))
                .collect(Collectors.toList());

        FullTextEntityManager fullTextEntityManager = getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(MedicinalProduct.class)
                .get();

        BooleanQuery.Builder booleanClauses = new BooleanQuery.Builder();

        for (String word : searchWords) {
            buildLuceneQueries(qb, word, booleanClauses);
        }

        BooleanQuery build = booleanClauses.build();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(build, MedicinalProduct.class);
        int countResults = jpaQuery.getResultSize();
        if (pageable.getSort().isSorted()) {
            SortField[] sortFields = pageable.getSort().stream().map(o -> new SortField(o.getProperty(), SortField.Type.STRING, o.getDirection().isDescending())).toArray(SortField[]::new);
            jpaQuery.setSort(new Sort(sortFields));
        }
        jpaQuery.setFirstResult((int) pageable.getOffset());
        jpaQuery.setMaxResults(pageable.getPageSize());
        @SuppressWarnings("unchecked") List<MedicinalProduct> result = jpaQuery.getResultList();

        return new PageImpl<>(result, pageable, countResults);
    }

    private void buildLuceneQueries(QueryBuilder qb, String phrase, BooleanQuery.Builder booleanClauses) {
        Query luceneQuery = qb
                .keyword()
                .wildcard()
                .onFields(
                        "packages.unit",
                        "packages.availabilityCategory"
                )
                .andField("name").boostedTo(20)
                .andField("commonName").boostedTo(20)
                .andField("substances.name").boostedTo(20)
                .andField("packages.eanCode").boostedTo(10)
                .andField("producer.name").boostedTo(10)
                .andField("atcCode").boostedTo(5)
                .matching(phrase + "*")
                .createQuery();

        booleanClauses.add(luceneQuery, BooleanClause.Occur.MUST);
    }

}
