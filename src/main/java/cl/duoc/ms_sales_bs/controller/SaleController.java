package cl.duoc.ms_sales_bs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms_sales_bs.model.dto.SaleDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransacionDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionQueryResponseDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionResponseDTO;
import cl.duoc.ms_sales_bs.service.SaleService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/sales")
@Log4j2
public class SaleController {

    @Autowired
    SaleService saleService;

    @PostMapping("/sale")
    public WebPayTransactionResponseDTO createSale(@RequestBody SaleDTO saleDTO) {
        log.error("SaleDTO: {}", saleDTO);
        return saleService.createSale(saleDTO);
    }

    @PostMapping("/sale/transaction/confirm")
    public String confirmTransaction(@RequestBody WebPayTransacionDTO webPayTransacionDTO) {
        log.info("WebPayTransacionDTO: {}", webPayTransacionDTO);
        return saleService.confirmTransaction(webPayTransacionDTO);
    }

    @PostMapping("/sale/transaction/query")
    public WebPayTransactionQueryResponseDTO queryTransaction(@RequestBody WebPayTransacionDTO webPayTransacionDTO) {
        log.info("WebPayTransacionDTO: {}", webPayTransacionDTO);
        return saleService.queryTransaction(webPayTransacionDTO);
    }
    
}

