package com.project.pos_system_update.dining.repository;

import com.project.pos_system_update.dining.entity.DiningId;
import com.project.pos_system_update.dining.entity.Dining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiningRepository extends JpaRepository<Dining, DiningId> {
    /* 업체의 하나의 테이블을 가져오는 메서드 : businessId, tableNo */
    Dining findByIdBusinessIdAndIdTableNo(String businessId, Integer tableNo);

    /* 업체의 전체 테이블을 가져오는 메서드 : businessId */
    List<Dining> findByIdBusinessId(String businessId);

    /* 업체의 비어있는 테이블을 찾기위한 쿼리문 */
    @Query("SELECT d FROM Dining d where d.id.businessId = :businessId AND d.tableStatus='EMPTY'")
    List<Integer> findEmptyTableNumbers(@Param("businessId") String businessId);

}
