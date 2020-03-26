package aiden.jpashop.core.order;

import aiden.jpashop.core.delivery.domain.Delivery;
import aiden.jpashop.core.delivery.domain.DeliveryRepository;
import aiden.jpashop.core.delivery.domain.DeliveryStatus;
import aiden.jpashop.core.item.domain.Item;
import aiden.jpashop.core.item.domain.ItemRepository;
import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.core.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ItemRepository itemRepository;

    private final OrderLineItemRepository orderLineItemRepository;

    private final MemberRepository memberRepository;

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public Long order(Long memberId, List<Long> itemIds, int count) {

        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        Order order = orderRepository.save(new Order(memberId, OrderStatus.ORDER));

        itemIds.forEach(itemId -> {
            Item item = itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
            orderLineItemRepository.save(OrderLineItem.create(item, count, order));
        });

        Delivery delivery = Delivery.create(order.getId(), member.getAddress());
        deliveryRepository.save(delivery);

        return order.getId();

    }

    @Transactional
    public void orderCancel(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
        Delivery delivery = deliveryRepository.findByOrderId(orderId).orElseThrow(IllegalArgumentException::new);

        if (delivery.getDeliveryStatus() == DeliveryStatus.COMPLETE) {
            throw new IllegalStateException("이미 완료된 주문은 취소할 수 없습니다.");
        }

        order.cancel();

        order.getOrderLineItem().forEach(orderLineItem -> {
            Item item = itemRepository.findById(orderLineItem.getItemId()).orElseThrow(IllegalArgumentException::new);
            item.addStock(orderLineItem.getCount());
            orderLineItemRepository.delete(orderLineItem);
        });

        deliveryRepository.delete(delivery);
    }

}
