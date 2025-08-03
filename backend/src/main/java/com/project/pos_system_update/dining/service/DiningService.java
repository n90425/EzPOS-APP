package com.project.pos_system_update.dining.service;

import com.project.pos_system_update.dining.entity.DiningId;
import com.project.pos_system_update.dining.repository.DiningRepository;
import com.project.pos_system_update.dining.entity.Dining;
import com.project.pos_system_update.order.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiningService {
    private final DiningRepository diningRepo;

    public DiningService(DiningRepository diningRepo) {
        this.diningRepo = diningRepo;
    }

    /* 업체의 전체 테이블을 가져오는 메서드 : businessId */
    public List<Dining> getAllTableByBusinessId(String businessId) {
        return diningRepo.findByIdBusinessId(businessId);
    }

    /* 업체의 하나의 테이블을 가져오는 메서드 : businessId, tableNo */
    public Dining getTableByBusinessId(String businessId, Integer tableNo) {
        Dining dining = diningRepo.findByIdBusinessIdAndIdTableNo(businessId, tableNo);
        if(dining == null) {
            throw new IllegalArgumentException("해당 테이블번호를 찾을수 없습니다. "+ tableNo);
        }
        return dining;
    }

    /* 테이블 생성시 가장 작은 테이블 번호부터 배정 */
    public Integer findNextTableNo(String businessId) {
        List<Integer> tableNum = diningRepo.findEmptyTableNumbers(businessId)
                .stream().sorted().toList();

        int nextno = 1;
        for(Integer tableNo:tableNum) {
            if(!tableNo.equals(nextno)) break;
            nextno++;
        }
        return nextno;
    }

    /* 업체의 테이블을 생성하는 메서드: dining */
    public List<Dining> createTable(List<Dining> diningList) {
        for(Dining dining : diningList) {
            int nextTableNo = findNextTableNo(dining.getId().getBusinessId());
            dining.setId(new DiningId(dining.getId().getBusinessId(), nextTableNo));
            diningRepo.save(dining);
        }
        return diningRepo.findAll();
    }


    /* 업체의 테이블을 수정하는 메서드: dining */
    public List<Dining> updateTable(List<Dining> diningList) {
        for(Dining dining : diningList) {
            DiningId id = dining.getId();
            Dining existDining = diningRepo.findById(id)
                            .orElseThrow(()-> new IllegalArgumentException("해당 테이블이 존재하지 않습니다."));

            existDining.updateFrom(dining);
        }
        return diningRepo.findAll();
    }

    /* 업체의 하나의 테이블을 삭제하는 메서드: businessId, tableNo */
    @Transactional
    public void deleteTable(Dining dining) {
        /* dining 삭제 */
        diningRepo.deleteById(dining.getId());
    }


    /* 업체 하나의 주문의 결제상태에따라 테이블의 사용여부를 EMPTY(비어있음) 으로 바꾸고 저장: businessId, tableNo, order, isPaid */
    public void updatePayStatus(Dining dining, Order order, boolean isPaid){
        DiningId id = dining.getId();
        Dining table = diningRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 테이블이 존재하지 않습니다."));

        if (isPaid) {
            table.clearTable(); /* 결제가 완료되어 테이블을 비울때 */

        } else {
            table.assignOrder(order); /* 주문이 들어왔을때 FULL로 변경 */
        }
        diningRepo.save(table);
    }

    /* 업체의 테이블 자이리동 구현: businessId, sourceTableNo, targetTableNo */

    /* 업체의 테이블 합석 구현: businessId, sourceTableNo, targetTableNo */


}
