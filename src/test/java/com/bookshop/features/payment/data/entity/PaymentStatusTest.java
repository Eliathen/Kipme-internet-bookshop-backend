package com.bookshop.features.payment.data.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentStatusTest {


    @Test
    void givenStatusUnPaidShouldChangeToSuccess() {
        var current = PaymentStatus.UN_PAID;

        var newStatus = current.changeStatus(PaymentStatus.SUCCESS);

        assertThat(newStatus).isEqualTo(PaymentStatus.SUCCESS);
    }

    @Test
    void givenStatusUnPaidShouldChangeToFailure() {
        var current = PaymentStatus.UN_PAID;

        var newStatus = current.changeStatus(PaymentStatus.FAILURE);

        assertThat(newStatus).isEqualTo(PaymentStatus.FAILURE);
    }

    @Test
    void givenStatusUnPaidShouldStayNotChangedWhenUnPaid() {
        var current = PaymentStatus.UN_PAID;

        var newStatus = current.changeStatus(PaymentStatus.UN_PAID);

        assertThat(newStatus).isEqualTo(PaymentStatus.UN_PAID);
    }

    @Test
    void givenStatusSuccessShouldThrowExceptionWhenChangedToAnyOtherStatus() {
        var current = PaymentStatus.SUCCESS;

        assertThatThrownBy(() -> current.changeStatus(PaymentStatus.UN_PAID))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> current.changeStatus(PaymentStatus.FAILURE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void givenStatusFailureShouldThrowExceptionWhenChangedToAnyOtherStatus() {
        var current = PaymentStatus.FAILURE;

        assertThatThrownBy(() -> current.changeStatus(PaymentStatus.UN_PAID))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> current.changeStatus(PaymentStatus.SUCCESS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void givenStatusWhenChangedToThisSameShouldReturnGivenStatus() {
        var success = PaymentStatus.SUCCESS;
        var unPaid = PaymentStatus.UN_PAID;
        var failure = PaymentStatus.FAILURE;

        assertThat(success.changeStatus(PaymentStatus.SUCCESS)).isEqualTo(success);
        assertThat(unPaid.changeStatus(PaymentStatus.UN_PAID)).isEqualTo(unPaid);
        assertThat(failure.changeStatus(PaymentStatus.FAILURE)).isEqualTo(failure);
    }
}