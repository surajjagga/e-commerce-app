package com.example.orderservice.order;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OrderLine {
    @Generated
    @Id
    private Integer id ;
    private Integer productId;
    private double quantity;
    @ManyToOne
    @JoinColumn(name= "order_id")
    private Order order;

}
