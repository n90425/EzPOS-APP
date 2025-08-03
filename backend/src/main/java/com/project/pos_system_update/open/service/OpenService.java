package com.project.pos_system_update.open.service;

import com.project.pos_system_update.open.entity.Open;
import com.project.pos_system_update.open.entity.OpenId;
import com.project.pos_system_update.open.repository.OpenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OpenService {
    private final OpenRepository openRepo;

    public OpenService(OpenRepository openRepo) {
        this.openRepo = openRepo;
    }

    /* 영업 시작 */
    @Transactional
    public void open(String businessId) {
        LocalDate today = LocalDate.now(); /* 오늘날짜 */
        LocalDateTime time = LocalDateTime.now(); /* 날짜와 시간 */

        OpenId id = new OpenId(businessId, today); /* 복합키 생성 */
        Optional<Open> findOpen = openRepo.findById(id); /* 오늘날짜의 영업정보 조회 */

        Open open;

        /* 오늘날짜의 영업정보가 없을경우 */
        if(findOpen.isEmpty()) {
            open = Open.of(id, time); /* open 객체 생성 */
        } else {
            /* 오늘날짜의 영업정보가 있을경우 */
            open = findOpen.get();

            /* 이미 영업중이면 예외 발생 */
            if(Boolean.TRUE.equals(open.getIsOpen())) {
                throw new IllegalArgumentException("이미 영업중 입니다.");
            }
            open.setIsOpen(true);
        }
        /* 데이터 저장 */
        openRepo.save(open);
    }

    /* 영업 마감 */
    @Transactional
    public void close(String businessId) {
        LocalDate today = LocalDate.now(); /* 오늘날짜 */
        OpenId id = new OpenId(businessId, today); /* 복합키 생성 */

        Open open = openRepo.findById(id) /* 생성된 키가 없을경우 */
                .orElseThrow(() -> new IllegalStateException("오늘 영업정보가 존재하지 않습니다."));

        /* open 상태가 TRUE가 아닐경우 */
        if(!Boolean.TRUE.equals(open.getIsOpen())) {
            throw new IllegalStateException("이미 영업이 종료되었습니다.");
        }

        /* open 상태가 TRUE 일 경우 */
        open.setIsOpen(false);
        open.setCloseTime(LocalDateTime.now());
        openRepo.save(open);
    }

    /* 하루의 매출과 주문수 계산 */
}
