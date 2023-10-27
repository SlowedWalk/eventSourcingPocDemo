package tech.hidetora.coreAPI.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import java.time.Instant
import java.time.LocalDateTime

data class CustomerRequestDTO(
    var name: String = "",
    var email: String = ""
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponseDTO(
    var message: String = "",
    var data: Any? = null,
    var error: String? = null,
    var status: Int = HttpStatus.OK.value(),
    var timestamp: LocalDateTime = LocalDateTime.now(),
    var path: String = "",
    var method: String = HttpMethod.GET.name,
    var exception: String? = null,
    var errors: List<String>? = null,
)