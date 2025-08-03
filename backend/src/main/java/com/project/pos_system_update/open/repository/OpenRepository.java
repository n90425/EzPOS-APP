package com.project.pos_system_update.open.repository;

import com.project.pos_system_update.open.entity.Open;
import com.project.pos_system_update.open.entity.OpenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenRepository extends JpaRepository<Open, OpenId> {

    /* 키 일치하는 Id 찾기 : businessId, openDate */

    /* 영업 시작 */

    /* 영업 마감 */
    /* 하루의 매출과 주문수 계산 */
    /* */
}
