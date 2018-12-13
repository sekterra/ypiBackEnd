package com.tommy.ypi.repository;

import com.tommy.ypi.model.ApiContents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApiContentsRepository extends JpaRepository<ApiContents, Long> {
	List<ApiContents> findByMenuId(Long menuId);
}
