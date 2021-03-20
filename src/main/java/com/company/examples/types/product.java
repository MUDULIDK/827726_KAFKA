
package com.company.examples.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "custID",
    "txnAmount",
    "totalSaleAmount"
})
public class total_Sales {

    @JsonProperty("custID")
    private String custID;
    @JsonProperty("txnAmount")
    private Double txnAmount;
    @JsonProperty("totalSaleAmount")
    private Double totalSaleAmount;

    @JsonProperty("custID")
    public String getCustID() {
        return custID;
    }

    @JsonProperty("custID")
    public void setCustID(String custID) {
        this.custID = custID;
    }

    public total_Sales withCustID(String custID) {
        this.custID = custID;
        return this;
    }

    @JsonProperty("txnAmount")
    public Double getTxnAmount() {
        return txnAmount;
    }

    @JsonProperty("txnAmount")
    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public total_Sales withTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
        return this;
    }

    @JsonProperty("totalSaleAmount")
    public Double getTotalSaleAmount() {
        return totalSaleAmount;
    }

    @JsonProperty("totalSaleAmount")
    public void setTotalSaleAmount(Double totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
    }

    public total_Sales withTotalSaleAmount(Double totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("custID", custID).append("txnAmount", txnAmount).append("totalSaleAmount", totalSaleAmount).toString();
    }

}
