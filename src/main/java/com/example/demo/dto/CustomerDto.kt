package com.example.demo.dto

import com.example.demo.model.Account

data class CustomerDto(
        val id:String,
        val name:String,
        val surname:String,
        val account: Set<CustomerAccountDto>
){

}
