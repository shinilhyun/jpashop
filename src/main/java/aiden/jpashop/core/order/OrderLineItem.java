package aiden.jpashop.core.order;

import aiden.jpashop.core.item.domain.Item;
import aiden.jpashop.core.support.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "order_item")
public class OrderLineItem extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    private Long itemId;

    private int orderPrice;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderLineItem(Long itemId, int orderPrice, int count, Order order) {
        this.itemId = itemId;
        this.orderPrice = orderPrice;
        this.count = count;
        updateOrder(order);
    }

    public void updateOrder(Order order) {
        if (this.order != null) {
            this.order.getOrderLineItem().remove(this);
        }
        this.order = order;
        order.getOrderLineItem().add(this);
    }

    public static OrderLineItem create(Item item, int count, Order order) {

        int orderPrice = item.getPrice() * count;

        item.removeStock(count);

        return new OrderLineItem(item.getId(), orderPrice, count, order);

    }


}
