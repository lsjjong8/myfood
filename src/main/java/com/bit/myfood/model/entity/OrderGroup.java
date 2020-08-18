package com.bit.myfood.model.entity;

import com.bit.myfood.model.enumclass.OrderStatus;
import com.bit.myfood.model.enumclass.OrderType;
import com.bit.myfood.model.enumclass.PaymentType;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(exclude = {"user", "orderDetailList"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // COMPLETE / CONFIRM / ORDERING

    @Enumerated(EnumType.STRING)
    private OrderType orderType; // ALL(일괄) / EACH(개별)

    private String revAddress;

    private String revName;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType; // BANK_TRANSFER / CARD / CHECK_CARD / CASH

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;

}
