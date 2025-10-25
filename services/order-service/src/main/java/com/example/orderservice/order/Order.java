package com.example.orderservice.order;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="customer-order")
public class Order {
    @Generated
    @Id
    private Integer id ;
    private String customer_id;
    private String reference;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod payment_method;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;








}
