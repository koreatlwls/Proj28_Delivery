package com.example.proj28_delivery.presentation

interface BaseView<PresenterT : BasePresenter> {

    val presenter: PresenterT
}