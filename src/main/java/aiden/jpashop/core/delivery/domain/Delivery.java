package aiden.jpashop.core.delivery.domain;

import aiden.jpashop.core.support.Address;
import aiden.jpashop.core.support.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "delivery")
public class Delivery extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    private Long orderId;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Embedded
    private Address address;

    @Builder
    public Delivery(Long orderId, DeliveryStatus deliveryStatus, Address address) {
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
    }

    public static Delivery create(long orderId, Address address) {

        return Delivery.builder()
                .orderId(orderId)
                .address(address)
                .deliveryStatus(DeliveryStatus.READY)
                .build();
    }

    public void cancel() {
        this.deliveryStatus = DeliveryStatus.READY;
    }

}
