package com.daniel.server.dal;

import com.daniel.server.beans.Category;
import com.daniel.server.entities.CategoryEntity;
import com.daniel.server.entities.CompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


//@Query("SELECT d FROM DocumentEntity d WHERE d.textContent= :content")
//    List<DocumentEntity> fetchDocumentEntitiesByTextContent(@Param("content") String x);
//
//@Query("from DocumentEntity t where date BETWEEN :startDate AND :endDate")
//public List<DocumentEntity> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);

public interface ICategoryDal extends CrudRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c")
    List<Category> getAllCategories(int offset);

    Optional<CompanyEntity> findByName(String name);

    @Query("SELECT c FROM CategoryEntity c ORDER BY name")
    List<Category> getAllCategoriesByNameOrder(int offset);
}
