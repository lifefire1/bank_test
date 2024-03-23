package bank.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Enumeration representing types of banking operations")
public enum OperationType {
    @ApiModelProperty(value = "The operation type for product", example = "PRODUCT")
    PRODUCT,

    @ApiModelProperty(value = "The operation type for service", example = "SERVICE")
    SERVICE
}
