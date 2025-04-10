package cl.duoc.ms_sales_bs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import cl.duoc.ms_sales_bs.config.WebPayFeignClient;
import cl.duoc.ms_sales_bs.model.dto.SaleDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionRequestDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionResponseDTO;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SaleService {

    @Autowired
    WebPayFeignClient webPayFeignClient;

    public WebPayTransactionResponseDTO createSale(@RequestBody SaleDTO saleDTO) {
        //TODO: process POST request
        log.info("SaleDTO: {}", saleDTO);
        WebPayTransactionRequestDTO webPayTransactionRequestDTO = new WebPayTransactionRequestDTO("00001", saleDTO.getSessionId(), saleDTO.getAmount(), "http://urlretorno.cl");
        WebPayTransactionResponseDTO webPayTransactionResponseDTO = webPayFeignClient.generateTransaction("597055555532", "579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C", "application/json", webPayTransactionRequestDTO);
        return webPayTransactionResponseDTO;
    }
}
