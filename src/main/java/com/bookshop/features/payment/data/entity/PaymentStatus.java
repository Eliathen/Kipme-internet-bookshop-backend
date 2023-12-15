package com.bookshop.features.payment.data.entity;


public enum PaymentStatus {
    UN_PAID {
        @Override
        public PaymentStatus changeStatus(PaymentStatus newStatus) {
            return switch (newStatus) {
                case SUCCESS -> SUCCESS;
                case FAILURE -> FAILURE;
                case UN_PAID -> UN_PAID;
            };
        }
    },
    SUCCESS {
        @Override
        public PaymentStatus changeStatus(PaymentStatus newStatus) {
            if (newStatus.equals(SUCCESS)) return SUCCESS;
            return super.changeStatus(newStatus);
        }
    },
    FAILURE {
        @Override
        public PaymentStatus changeStatus(PaymentStatus newStatus) {
            if (newStatus.equals(FAILURE)) return FAILURE;
            return super.changeStatus(newStatus);
        }
    };

    public PaymentStatus changeStatus(PaymentStatus newStatus) {
        throw new IllegalArgumentException("Cannot change payment status to: " + newStatus);
    }
}
