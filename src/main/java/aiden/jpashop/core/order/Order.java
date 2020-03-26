package aiden.jpashop.core.order;

import aiden.jpashop.core.delivery.domain.Delivery;
import aiden.jpashop.core.delivery.domain.DeliveryStatus;
import aiden.jpashop.core.support.Address;
import aiden.jpashop.core.support.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private Long memberId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItem = new ArrayList<>();

    public Order(Long memberId, OrderStatus orderStatus) {
        this.memberId = memberId;
        this.orderStatus = orderStatus;
    }

    public void cancel() {
        this.orderStatus = OrderStatus.CANCEL;
    }
}
