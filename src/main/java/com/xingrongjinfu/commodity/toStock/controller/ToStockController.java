package com.xingrongjinfu.commodity.toStock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xingrongjinfu.commodity.register.common.RegisterConstant;
import com.xingrongjinfu.commodity.toStock.service.IToStockService;

@Controller
@RequestMapping(RegisterConstant.COMMODITY_URL)
public class ToStockController {
    @Autowired
    private IToStockService toStockService;
}
