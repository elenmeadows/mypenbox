package com.mypenbox.mpb.repo;

import com.mypenbox.mpb.models.Catalog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends CrudRepository<Catalog, Long> {


}
