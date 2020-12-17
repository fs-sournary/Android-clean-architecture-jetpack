package com.andrdoidlifelang.data.model

sealed class ReservationRequestAction {

    class RequestAction : ReservationRequestAction() {

        override fun equals(other: Any?): Boolean = other is RequestAction

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    class CancelAction : ReservationRequestAction() {

        override fun equals(other: Any?): Boolean = other is CancelAction

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    class SwapAction : ReservationRequestAction()
}
