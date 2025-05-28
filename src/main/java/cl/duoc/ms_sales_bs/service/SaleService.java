package cl.duoc.ms_sales_bs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ms_sales_bs.clients.*;
import cl.duoc.ms_sales_bs.model.dto.SaleDTO;
import cl.duoc.ms_sales_bs.model.dto.SalesDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransacionDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionQueryResponseDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionRequestDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionResponseDTO;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SaleService {

    @Autowired
    WebPayFeignClient webPayFeignClient;

    @Autowired
    SalesDbFeignClient salesDbFeignClient;

    public WebPayTransactionResponseDTO createSale(SaleDTO saleDTO) {
        //TODO: process POST request
        log.info("SaleDTO: {}", saleDTO);
        //Generamos el objeto request para la integracion con WebPay
        WebPayTransactionRequestDTO webPayTransactionRequestDTO = new WebPayTransactionRequestDTO("00001", saleDTO.getSessionId(), saleDTO.getAmount(), "http://localhost/fe-ecommerce/resultado.html");
        
        //Generamos el objeto de respuesta de WebPay y llamamos al método del feign client.
        WebPayTransactionResponseDTO webPayTransactionResponseDTO = webPayFeignClient.generateTransaction("597055555532", "579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C", webPayTransactionRequestDTO);
        return webPayTransactionResponseDTO;
    }

    public String confirmTransaction(WebPayTransacionDTO webPayTransacionDTO) {
        String response = webPayFeignClient.confirmTransaction("597055555532", "579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C", webPayTransacionDTO.getToken());
        log.info("confirmTransaction: {}", response);
        return response;
      }

     public WebPayTransactionQueryResponseDTO queryTransaction(WebPayTransacionDTO webPayTransacionDTO) {
       WebPayTransactionQueryResponseDTO response =  webPayFeignClient.queryTransaction("597055555532", "579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C", webPayTransacionDTO.getToken());
       log.info("queryTransaction: {}", response);
       return response;
      }

     public SalesDTO findSalesById(Long id){
        return salesDbFeignClient.findSalesById(id).getBody();
     }

}
