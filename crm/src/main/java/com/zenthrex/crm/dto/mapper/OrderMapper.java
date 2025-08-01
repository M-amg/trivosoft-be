package com.zenthrex.crm.dto.mapper;

import com.zenthrex.core.entites.crm.Order;
import com.zenthrex.core.entites.crm.OrderItem;
import com.zenthrex.crm.dto.AddressDto;
import com.zenthrex.crm.dto.CustomerDto;
import com.zenthrex.crm.dto.order.OrderDto;
import com.zenthrex.crm.dto.order.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "customer", target = "customer", qualifiedByName = "mapCustomer")
    @Mapping(source = "orderType", target = "orderType")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "currency", target = "currency")
    @Mapping(target = "billingAddress", qualifiedByName = "mapBillingAddress")
    @Mapping(target = "shippingAddress", qualifiedByName = "mapShippingAddress")
    OrderDto toDto(Order order);

    @Mapping(source = "order.id", target = "orderId", ignore = true)
    OrderItemDto toItemDto(OrderItem orderItem);

    @Named("mapCustomer")
    default CustomerDto mapCustomer(com.zenthrex.core.entites.user.User user) {
        if (user == null) return null;
        return new CustomerDto(
                user.getId().longValue(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhone()
        );
    }

    @Named("mapBillingAddress")
    default AddressDto mapBillingAddress(Order order) {
        if (order == null) return null;
        return new AddressDto(
                order.getBillingAddressLine1(),
                order.getBillingAddressLine2(),
                order.getBillingCity(),
                order.getBillingState(),
                order.getBillingPostalCode(),
                order.getBillingCountry()
        );
    }

    @Named("mapShippingAddress")
    default AddressDto mapShippingAddress(Order order) {
        if (order == null) return null;
        return new AddressDto(
                order.getShippingAddressLine1(),
                order.getShippingAddressLine2(),
                order.getShippingCity(),
                order.getShippingState(),
                order.getShippingPostalCode(),
                order.getShippingCountry()
        );
    }

    default String mapEnumToString(Enum<?> enumValue) {
        return enumValue != null ? enumValue.name() : null;
    }
}
