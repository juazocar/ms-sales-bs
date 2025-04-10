package cl.duoc.ms_sales_bs.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionRequestDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionResponseDTO;

@FeignClient(name="api-webpay", url = "https://webpay3gint.transbank.cl/")
public interface WebPayFeignClient {

    @PostMapping("/rswebpaytransaction/api/webpay/v1.2/transactions")
    WebPayTransactionResponseDTO generateTransaction(@RequestHeader("Tbk-Api-Key-Id") String tbkApiKeyId, 
                                                     @RequestHeader("Tbk-Api-Key-Secret") String tbkApiKeySecret,
                                                     @RequestHeader("Content-Type") String contentType,
                                                     @RequestBody WebPayTransactionRequestDTO webPayTransactionRequestDTO);

}
