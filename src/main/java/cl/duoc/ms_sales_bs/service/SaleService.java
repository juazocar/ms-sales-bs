package cl.duoc.ms_sales_bs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public WebPayTransactionResponseDTO createSale(SaleDTO saleDTO) {
        //TODO: process POST request
        log.info("SaleDTO: {}", saleDTO);
        //Generamos el objeto request para la integracion con WebPay
        WebPayTransactionRequestDTO webPayTransactionRequestDTO = new WebPayTransactionRequestDTO("00001", saleDTO.getSessionId(), saleDTO.getAmount(), "https://urlretornoprueba.com");
        
        //Generamos el objeto de respuesta de WebPay y llamamos al método del feign client.
        WebPayTransactionResponseDTO webPayTransactionResponseDTO = webPayFeignClient.generateTransaction("597055555532", "579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C", webPayTransactionRequestDTO);
        return webPayTransactionResponseDTO;
    }
}
