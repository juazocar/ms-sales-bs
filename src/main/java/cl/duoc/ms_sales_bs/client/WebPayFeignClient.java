package cl.duoc.ms_sales_bs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionRequestDTO;
import cl.duoc.ms_sales_bs.model.dto.WebPayTransactionResponseDTO;

@FeignClient(name="api-webpay", url = "https://webpay3gint.transbank.cl/")
public interface WebPayFeignClient {

    @PostMapping(
        value = "/rswebpaytransaction/api/webpay/v1.2/transactions",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    WebPayTransactionResponseDTO generateTransaction(
        @RequestHeader("Tbk-Api-Key-Id") String tbkApiKeyId, 
        @RequestHeader("Tbk-Api-Key-Secret") String tbkApiKeySecret,
        @RequestBody WebPayTransactionRequestDTO webPayTransactionRequestDTO
    );
}